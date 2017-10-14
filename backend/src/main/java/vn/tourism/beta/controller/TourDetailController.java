package vn.tourism.beta.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;
import vn.tourism.beta.entity.TourDetail;
import vn.tourism.beta.entity.User;
import vn.tourism.beta.repository.TourDetailRepository;
import vn.tourism.beta.repository.TourRepository;
import vn.tourism.beta.repository.UserRepository;
import vn.tourism.beta.repository.VoucherRepository;
import vn.tourism.beta.security.JwtTokenUtil;
import vn.tourism.beta.service.TourDetailService;
import vn.tourism.beta.storage.StorageService;
import vn.tourism.beta.utils.JSONUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
public class TourDetailController {
    private final Log logger = LogFactory.getLog(this.getClass());
    private final StorageService storageService;

    @Autowired
    public TourDetailController(StorageService storageService) {
        this.storageService = storageService;
    }

    @Autowired
    private TourDetailRepository tourDetailRepository;

    @Autowired
    private UserRepository userRepository;

    @Value("${jwt.header}")
    private String tokenHeader;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;


    @Autowired
    private TourDetailService tourDetailService;


    @Secured("ROLE_ADMIN")
    @ResponseBody
    @GetMapping(value = "custom-api/tourDetails")
    public ResponseEntity<?> getAllTourDetail(
            HttpServletRequest request,
            @RequestParam(name = "page", defaultValue = "1") int pageNumber,
            @RequestParam(name = "sort", defaultValue = "asc") String sort,
            @RequestParam(name = "column", defaultValue = "id") String column,
            @RequestParam(name = "paging", defaultValue = "5") int maxResult
    ) {

        Sort.Direction sortDirection = Sort.Direction.ASC;
        if ("desc".equals(sort)) sortDirection = Sort.Direction.DESC;

        Iterable<TourDetail> tourDetails = tourDetailService.getPage(pageNumber, sortDirection, column, maxResult);
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("Content-Type", "application/json");
        try {
            return new ResponseEntity<>(JSONUtils.mapper.writeValueAsString(tourDetails), responseHeaders, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(e.toString(), responseHeaders, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @ResponseBody
    @GetMapping(value = "custom-api/tourDetails-by-tours")
    public ResponseEntity<?> getAllUserByRoleId(
            @RequestParam(name = "tour_id") List<Long> tourIds,
            @RequestParam(name = "page", defaultValue = "1") int pageNumber,
            @RequestParam(name = "sort", defaultValue = "asc") String sort,
            @RequestParam(name = "column", defaultValue = "id") String column,
            @RequestParam(name = "paging", defaultValue = "5") int maxResult
    ) {

        Sort.Direction sortDirection = Sort.Direction.ASC;
        if("desc".equals(sort)) sortDirection = Sort.Direction.DESC;

        PageRequest pageRequest = new PageRequest(pageNumber - 1, maxResult, sortDirection, column);
        Iterable<TourDetail> tourDetails = tourDetailRepository.findAllByTour_idInAndEnableEquals(pageRequest, tourIds, true);

        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("Content-Type", "application/json");
        try {
            return new ResponseEntity<>(JSONUtils.mapper.writeValueAsString(tourDetails), responseHeaders, HttpStatus.OK);
        }
        catch(Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(e.toString(), responseHeaders, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @ResponseBody
    @GetMapping(value = "custom-api/tourDetails-by-tourRegisters")
    public ResponseEntity<?> getAllTourDetailsByTourRegisters(
            HttpServletRequest request,
            @RequestParam(name = "page", defaultValue = "1") int pageNumber,
            @RequestParam(name = "sort", defaultValue = "asc") String sort,
            @RequestParam(name = "column", defaultValue = "id") String column,
            @RequestParam(name = "paging", defaultValue = "5") int maxResult
    ) {

        String header = request.getHeader("Authorization");
        String authToken = header.substring(7);
        String username = jwtTokenUtil.getUsernameFromToken(authToken);
        User representUser = userRepository.findByUsername(username);

        Sort.Direction sortDirection = Sort.Direction.ASC;
        if("desc".equals(sort)) sortDirection = Sort.Direction.DESC;

        PageRequest pageRequest = new PageRequest(pageNumber - 1, maxResult, sortDirection, column);
        List<Long> ids = new ArrayList<>();
        ids.add(representUser.getId());
        ids.add(new Long("3"));
        Iterable<TourDetail> tourDetails = tourDetailRepository.findAllByTourRegisters_Representative_idEqualsAndEnableEquals(pageRequest, representUser.getId(), true);

        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("Content-Type", "application/json");
        try {
            return new ResponseEntity<>(JSONUtils.mapper.writeValueAsString(tourDetails), responseHeaders, HttpStatus.OK);
        }
        catch(Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(e.toString(), responseHeaders, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Secured("ROLE_ADMIN")
    @ResponseBody
    @GetMapping(value = "custom-api/tourDetails-count")
    public ResponseEntity<?> countAllTourDetail() {
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("Content-Type", "application/json");
        Long amount = tourDetailRepository.countAllByEnableEquals(true);
        try {
            return new ResponseEntity<>(JSONUtils.mapper.writeValueAsString(amount), responseHeaders, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(e.toString(), responseHeaders, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Secured("ROLE_ADMIN")
    @ResponseBody
    @GetMapping(value = "custom-api/tourDetails/{id}")
    public ResponseEntity<?> getTourDetailById(
            @PathVariable("id") Long id
    ) {
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("Content-Type", "application/json");
        TourDetail tourDetail = tourDetailRepository.findByIdAndEnableEquals(id, true);
        try {
            return new ResponseEntity<>(JSONUtils.mapper.writeValueAsString(tourDetail), responseHeaders, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(e.toString(), responseHeaders, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @ResponseBody
    @PostMapping(value = "custom-api/tourDetails")
    public ResponseEntity<?> addNewTourDetail(
            @RequestBody TourDetail tourDetail,
            HttpServletRequest request
    ) {
        String header = request.getHeader("Authorization");
        String authToken = header.substring(7);
        String username = jwtTokenUtil.getUsernameFromToken(authToken);
        User updateBy = userRepository.findByUsername(username);
        HttpHeaders responseHeaders = new HttpHeaders();
        try {
            tourDetail.setUpdatedOn(new Date());
//            tourDetail.setUpdatedBy(updateBy);
            TourDetail addTourDetail = tourDetailRepository.save(tourDetail);
            responseHeaders.set("Content-Type", "application/json");
            return new ResponseEntity<>(JSONUtils.mapper.writeValueAsString(addTourDetail), responseHeaders, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(e.toString(), responseHeaders, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @ResponseBody
    @PatchMapping(value = "custom-api/tourDetails")
    public ResponseEntity<?> updateTourDetailById(
            @RequestBody TourDetail tourDetail,
            HttpServletRequest request
    ) {
        String header = request.getHeader("Authorization");
        String authToken = header.substring(7);
        String username = jwtTokenUtil.getUsernameFromToken(authToken);
        User updateBy = userRepository.findByUsername(username);
        HttpHeaders responseHeaders = new HttpHeaders();
        try {
            tourDetail.setUpdatedOn(new Date());
//            tourDetail.setUpdatedBy(updateBy);
            TourDetail updatedTourDetail = tourDetailRepository.save(tourDetail);
            responseHeaders.set("Content-Type", "application/json");
            return new ResponseEntity<>(JSONUtils.mapper.writeValueAsString(updatedTourDetail), responseHeaders, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(e.toString(), responseHeaders, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @ResponseBody
    @DeleteMapping(value = "custom-api/tourDetails/{id}")
    public ResponseEntity<?> deleteTourDetailById(
            @PathVariable("id") Long id
    ) {
        HttpHeaders responseHeaders = new HttpHeaders();
        try {
            TourDetail deletedTourDetail = tourDetailService.deleteTourDetailById(id);
            responseHeaders.set("Content-Type", "application/json");
            return new ResponseEntity<>(JSONUtils.mapper.writeValueAsString(deletedTourDetail), responseHeaders, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            responseHeaders.set("MyResponseHeader", "MyValue");
            return new ResponseEntity<>("Cannot delete", responseHeaders, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
