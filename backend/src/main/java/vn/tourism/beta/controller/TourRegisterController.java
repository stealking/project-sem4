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
import vn.tourism.beta.entity.TourDetail;
import vn.tourism.beta.entity.TourRegister;
import vn.tourism.beta.entity.User;
import vn.tourism.beta.repository.TourDetailRepository;
import vn.tourism.beta.repository.TourRegisterRepository;
import vn.tourism.beta.repository.UserRepository;
import vn.tourism.beta.security.JwtTokenUtil;
import vn.tourism.beta.service.TourRegisterService;
import vn.tourism.beta.storage.StorageService;
import vn.tourism.beta.utils.JSONUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@RestController
public class TourRegisterController {
    private final Log logger = LogFactory.getLog(this.getClass());
    private final StorageService storageService;

    @Autowired
    public TourRegisterController(StorageService storageService) {
        this.storageService = storageService;
    }

    @Autowired
    private TourRegisterRepository tourRegisterRepository;

    @Autowired
    private TourDetailRepository tourDetailRepository;

    @Autowired
    private UserRepository userRepository;

    @Value("${jwt.header}")
    private String tokenHeader;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;


    @Autowired
    private TourRegisterService tourRegisterService;


    @Secured("ROLE_ADMIN")
    @ResponseBody
    @GetMapping(value = "custom-api/tourRegisters")
    public ResponseEntity<?> getAllTourRegister(
            HttpServletRequest request,
            @RequestParam(name = "page", defaultValue = "1") int pageNumber,
            @RequestParam(name = "sort", defaultValue = "asc") String sort,
            @RequestParam(name = "column", defaultValue = "id") String column,
            @RequestParam(name = "paging", defaultValue = "5") int maxResult
    ) {

        Sort.Direction sortDirection = Sort.Direction.ASC;
        if("desc".equals(sort)) sortDirection = Sort.Direction.DESC;

        Iterable<TourRegister> tourRegisters = tourRegisterService.getPage(pageNumber, sortDirection, column, maxResult);
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("Content-Register", "application/json");
        try {
            return new ResponseEntity<>(JSONUtils.mapper.writeValueAsString(tourRegisters), responseHeaders, HttpStatus.OK);
        }
        catch(Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(e.toString(), responseHeaders, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @Secured("ROLE_ADMIN")
    @ResponseBody
    @GetMapping(value = "custom-api/tourRegisters-count")
    public ResponseEntity<?> countAllTourRegister() {
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("Content-Type", "application/json");
        Long amount = tourRegisterRepository.countAllByEnableEquals(true);
        try {
            return new ResponseEntity<>(JSONUtils.mapper.writeValueAsString(amount), responseHeaders, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(e.toString(), responseHeaders, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Secured("ROLE_ADMIN")
    @ResponseBody
    @GetMapping(value = "custom-api/tourRegisters/{id}")
    public ResponseEntity<?> getTourRegisterById(
            @PathVariable("id") Long id
    ) {
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("Content-Type", "application/json");
        TourRegister tourRegister = tourRegisterRepository.findByIdAndEnableEquals(id, true);
        try {
            return new ResponseEntity<>(JSONUtils.mapper.writeValueAsString(tourRegister), responseHeaders, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(e.toString(), responseHeaders, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @ResponseBody
    @PostMapping(value = "custom-api/tourRegisters")
    public ResponseEntity<?> addNewTourRegister(
            @RequestBody TourRegister tourRegister,
            HttpServletRequest request
    ) {
        String header = request.getHeader("Authorization");
        String authToken = header.substring(7);
        String username = jwtTokenUtil.getUsernameFromToken(authToken);
        User updateBy = userRepository.findByUsername(username);
        HttpHeaders responseHeaders = new HttpHeaders();
        try {
            tourRegister.setRegisteredOn(new Date());
            tourRegister.setRepresentative(updateBy);
            TourRegister addTourRegister = tourRegisterRepository.save(tourRegister);
            responseHeaders.set("Content-Register", "application/json");
            return new ResponseEntity<>(JSONUtils.mapper.writeValueAsString(addTourRegister), responseHeaders, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(e.toString(), responseHeaders, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @ResponseBody
    @PatchMapping(value = "custom-api/tourRegisters/{id}/{tourDetailId}")
    public ResponseEntity<?> updateTourRegisterById(
            @PathVariable("id") Long id,
            @PathVariable("tourDetailId") Long tourDetailId,
            @RequestBody TourRegister tourRegister,
            HttpServletRequest request
    ) {
        String header = request.getHeader("Authorization");
        String authToken = header.substring(7);
        String username = jwtTokenUtil.getUsernameFromToken(authToken);
        User updateBy = userRepository.findByUsername(username);
        HttpHeaders responseHeaders = new HttpHeaders();
        try {
            TourDetail tourDetail = tourDetailRepository.findOne(tourDetailId);
            tourRegister.setTourDetail(tourDetail);
            tourRegister.setRegisteredOn(new Date());
            tourRegister.setRepresentative(updateBy);
            TourRegister updatedTourRegister = tourRegisterService.updateTourRegisterById(tourRegister, id);
            responseHeaders.set("Content-Register", "application/json");
            return new ResponseEntity<>(JSONUtils.mapper.writeValueAsString(updatedTourRegister), responseHeaders, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(e.toString(), responseHeaders, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }



    @ResponseBody
    @DeleteMapping(value = "custom-api/tourRegisters/{id}")
    public ResponseEntity<?> deleteTourRegisterById(
            @PathVariable("id") Long id
    ) {
        HttpHeaders responseHeaders = new HttpHeaders();
        try {
            TourRegister deletedTourRegister = tourRegisterService.deleteTourRegisterById(id);
            responseHeaders.set("Content-Register", "application/json");
            return new ResponseEntity<>(JSONUtils.mapper.writeValueAsString(deletedTourRegister), responseHeaders, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            responseHeaders.set("MyResponseHeader", "MyValue");
            return new ResponseEntity<>("Cannot delete", responseHeaders, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
