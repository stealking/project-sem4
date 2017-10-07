package vn.tourism.beta.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import vn.tourism.beta.entity.Recommend;
import vn.tourism.beta.entity.User;
import vn.tourism.beta.repository.RecommendRepository;
import vn.tourism.beta.repository.UserRepository;
import vn.tourism.beta.security.JwtTokenUtil;
import vn.tourism.beta.service.RecommendService;
import vn.tourism.beta.storage.StorageService;
import vn.tourism.beta.utils.JSONUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@RestController
public class RecommendController {
    private final Log logger = LogFactory.getLog(this.getClass());
    private final StorageService storageService;

    @Autowired
    public RecommendController(StorageService storageService) {
        this.storageService = storageService;
    }

    @Autowired
    private RecommendRepository recommendRepository;

    @Autowired
    private UserRepository userRepository;

    @Value("${jwt.header}")
    private String tokenHeader;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;


    @Autowired
    private RecommendService recommendService;


    @Secured("ROLE_ADMIN")
    @ResponseBody
    @GetMapping(value = "custom-api/recommends")
    public ResponseEntity<?> getAllRecommend(
            HttpServletRequest request,
            @RequestParam(name = "page", defaultValue = "1") int pageNumber,
            @RequestParam(name = "sort", defaultValue = "asc") String sort,
            @RequestParam(name = "column", defaultValue = "id") String column,
            @RequestParam(name = "paging", defaultValue = "5") int maxResult
    ) {

        Sort.Direction sortDirection = Sort.Direction.ASC;
        if("desc".equals(sort)) sortDirection = Sort.Direction.DESC;

        Iterable<Recommend> recommends = recommendService.getPage(pageNumber, sortDirection, column, maxResult);
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("Content-Type", "application/json");
        try {
            return new ResponseEntity<>(JSONUtils.mapper.writeValueAsString(recommends), responseHeaders, HttpStatus.OK);
        }
        catch(Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(e.toString(), responseHeaders, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Secured("ROLE_ADMIN")
    @ResponseBody
    @GetMapping(value = "custom-api/recommends-count")
    public ResponseEntity<?> countAllRecommend() {
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("Content-Type", "application/json");
        Long amount = recommendRepository.countAllByEnableEquals(true);
        try {
            return new ResponseEntity<>(JSONUtils.mapper.writeValueAsString(amount), responseHeaders, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(e.toString(), responseHeaders, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Secured("ROLE_ADMIN")
    @ResponseBody
    @GetMapping(value = "custom-api/recommends/{id}")
    public ResponseEntity<?> getRecommendById(
            @PathVariable("id") Long id
    ) {
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("Content-Type", "application/json");
        Recommend recommend = recommendRepository.findByIdAndEnableEquals(id, true);
        try {
            return new ResponseEntity<>(JSONUtils.mapper.writeValueAsString(recommend), responseHeaders, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(e.toString(), responseHeaders, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @ResponseBody
    @PostMapping(value = "custom-api/recommends")
    public ResponseEntity<?> addNewRecommend(
            @RequestParam(value="file", required = false) MultipartFile file,
            @RequestParam("content") String content,
            HttpServletRequest request
    ) {
        String header = request.getHeader("Authorization");
        String authToken = header.substring(7);
        String username = jwtTokenUtil.getUsernameFromToken(authToken);
        User updateBy = userRepository.findByUsername(username);
        HttpHeaders responseHeaders = new HttpHeaders();
        try {
            Recommend recommend =  JSONUtils.mapper.readValue(content, Recommend.class);
            recommend.setUpdatedOn(new Date());
            recommend.setUpdatedBy(updateBy);
            Recommend addRecommend = recommendRepository.save(recommend);
            Long id = addRecommend.getId();
            if(file !=null && !file.isEmpty()) {
                storageService.store(file, "recommend_" + id + ".png");
                recommend.setImage("recommend_"+ id +".png");
                recommendRepository.save(recommend);
            }

            responseHeaders.set("Content-Type", "application/json");
            return new ResponseEntity<>(JSONUtils.mapper.writeValueAsString(addRecommend), responseHeaders, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(e.toString(), responseHeaders, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @ResponseBody
    @PatchMapping(value = "custom-api/recommends")
    public ResponseEntity<?> updateRecommendById(
            @RequestParam(value="file", required = false) MultipartFile file,
            @RequestParam("content") String content,
            HttpServletRequest request
    ) {
        String header = request.getHeader("Authorization");
        String authToken = header.substring(7);
        String username = jwtTokenUtil.getUsernameFromToken(authToken);
        User updateBy = userRepository.findByUsername(username);
        HttpHeaders responseHeaders = new HttpHeaders();
        try {
            Recommend recommend =  JSONUtils.mapper.readValue(content, Recommend.class);
            if(file !=null &&!file.isEmpty()) {
                storageService.store(file, "recommend_" + recommend.getId() + ".png");
                recommend.setImage("recommend_"+ recommend.getId() +".png");
            }

            recommend.setUpdatedOn(new Date());
            recommend.setUpdatedBy(updateBy);
            Recommend updatedRecommend = recommendRepository.save(recommend);
            responseHeaders.set("Content-Type", "application/json");
            return new ResponseEntity<>(JSONUtils.mapper.writeValueAsString(updatedRecommend), responseHeaders, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(e.toString(), responseHeaders, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }



    @ResponseBody
    @DeleteMapping(value = "custom-api/recommends/{id}")
    public ResponseEntity<?> deleteRecommendById(
            @PathVariable("id") Long id
    ) {
        HttpHeaders responseHeaders = new HttpHeaders();
        try {
            Recommend deletedRecommend = recommendService.deleteRecommendById(id);
            responseHeaders.set("Content-Type", "application/json");
            return new ResponseEntity<>(JSONUtils.mapper.writeValueAsString(deletedRecommend), responseHeaders, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            responseHeaders.set("MyResponseHeader", "MyValue");
            return new ResponseEntity<>("Cannot delete", responseHeaders, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
