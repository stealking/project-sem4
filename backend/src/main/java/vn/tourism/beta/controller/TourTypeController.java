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
import vn.tourism.beta.entity.TourType;
import vn.tourism.beta.entity.User;
import vn.tourism.beta.repository.TourTypeRepository;
import vn.tourism.beta.repository.UserRepository;
import vn.tourism.beta.security.JwtTokenUtil;
import vn.tourism.beta.service.TourTypeService;
import vn.tourism.beta.storage.StorageService;
import vn.tourism.beta.utils.JSONUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@RestController
public class TourTypeController {
    private final Log logger = LogFactory.getLog(this.getClass());
    private final StorageService storageService;

    @Autowired
    public TourTypeController(StorageService storageService) {
        this.storageService = storageService;
    }

    @Autowired
    private TourTypeRepository tourTypeRepository;

    @Autowired
    private TourTypeRepository tourTypeTypeRepository;

    @Autowired
    private UserRepository userRepository;

    @Value("${jwt.header}")
    private String tokenHeader;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;


    @Autowired
    private TourTypeService tourTypeService;


    @Secured("ROLE_ADMIN")
    @ResponseBody
    @GetMapping(value = "custom-api/tourTypes")
    public ResponseEntity<?> getAllTourType(
            HttpServletRequest request,
            @RequestParam(name = "page", defaultValue = "1") int pageNumber,
            @RequestParam(name = "sort", defaultValue = "asc") String sort,
            @RequestParam(name = "column", defaultValue = "id") String column,
            @RequestParam(name = "paging", defaultValue = "5") int maxResult
    ) {

        Sort.Direction sortDirection = Sort.Direction.ASC;
        if("desc".equals(sort)) sortDirection = Sort.Direction.DESC;

        Iterable<TourType> tourTypes = tourTypeService.getPage(pageNumber, sortDirection, column, maxResult);
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("Content-Type", "application/json");
        try {
            return new ResponseEntity<>(JSONUtils.mapper.writeValueAsString(tourTypes), responseHeaders, HttpStatus.OK);
        }
        catch(Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(e.toString(), responseHeaders, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Secured("ROLE_ADMIN")
    @ResponseBody
    @GetMapping(value = "custom-api/tourTypes-count")
    public ResponseEntity<?> getAllTourType() {
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("Content-Type", "application/json");
        Long amount = tourTypeRepository.countAllByEnableEquals(true);
        try {
            return new ResponseEntity<>(JSONUtils.mapper.writeValueAsString(amount), responseHeaders, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(e.toString(), responseHeaders, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Secured("ROLE_ADMIN")
    @ResponseBody
    @GetMapping(value = "custom-api/tourTypes/{id}")
    public ResponseEntity<?> getTourTypeById(
            @PathVariable("id") Long id
    ) {
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("Content-Type", "application/json");
        TourType tourType = tourTypeRepository.findByIdAndEnableEquals(id, true);
        try {
            return new ResponseEntity<>(JSONUtils.mapper.writeValueAsString(tourType), responseHeaders, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(e.toString(), responseHeaders, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @ResponseBody
    @PostMapping(value = "custom-api/tourTypes")
    public ResponseEntity<?> addNewTourType(
            @RequestBody TourType tourType,
            HttpServletRequest request
    ) {
        String header = request.getHeader("Authorization");
        String authToken = header.substring(7);
        String username = jwtTokenUtil.getUsernameFromToken(authToken);
        User updateBy = userRepository.findByUsername(username);
        HttpHeaders responseHeaders = new HttpHeaders();
        try {
            tourType.setUpdatedOn(new Date());
            tourType.setUpdatedBy(updateBy);
            TourType addTourType = tourTypeRepository.save(tourType);
            responseHeaders.set("Content-Type", "application/json");
            return new ResponseEntity<>(JSONUtils.mapper.writeValueAsString(addTourType), responseHeaders, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(e.toString(), responseHeaders, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @ResponseBody
    @PatchMapping(value = "custom-api/tourTypes")
    public ResponseEntity<?> updateTourTypeById(
            @RequestBody TourType tourType,
            HttpServletRequest request
    ) {
        String header = request.getHeader("Authorization");
        String authToken = header.substring(7);
        String username = jwtTokenUtil.getUsernameFromToken(authToken);
        User updateBy = userRepository.findByUsername(username);
        HttpHeaders responseHeaders = new HttpHeaders();
        try {
            tourType.setUpdatedOn(new Date());
            tourType.setUpdatedBy(updateBy);
            TourType updatedTourType = tourTypeRepository.save(tourType);
            responseHeaders.set("Content-Type", "application/json");
            return new ResponseEntity<>(JSONUtils.mapper.writeValueAsString(updatedTourType), responseHeaders, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(e.toString(), responseHeaders, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }



    @ResponseBody
    @DeleteMapping(value = "custom-api/tourTypes/{id}")
    public ResponseEntity<?> deleteTourTypeById(
            @PathVariable("id") Long id
    ) {
        HttpHeaders responseHeaders = new HttpHeaders();
        try {
            TourType deletedTourType = tourTypeService.deleteTourTypeById(id);
            responseHeaders.set("Content-Type", "application/json");
            return new ResponseEntity<>(JSONUtils.mapper.writeValueAsString(deletedTourType), responseHeaders, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            responseHeaders.set("MyResponseHeader", "MyValue");
            return new ResponseEntity<>("Cannot delete", responseHeaders, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
