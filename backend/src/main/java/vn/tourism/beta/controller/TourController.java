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
import org.springframework.web.multipart.MultipartFile;
import vn.tourism.beta.entity.Tour;
import vn.tourism.beta.entity.User;
import vn.tourism.beta.repository.TourRepository;
import vn.tourism.beta.repository.TourTypeRepository;
import vn.tourism.beta.repository.TourRepository2;
import vn.tourism.beta.repository.UserRepository;
import vn.tourism.beta.security.JwtTokenUtil;
import vn.tourism.beta.service.TourService;
import vn.tourism.beta.specification.TourSpecification;
import vn.tourism.beta.storage.StorageService;
import vn.tourism.beta.utils.JSONUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.Map;

@RestController
public class TourController {
    private final Log logger = LogFactory.getLog(this.getClass());
    private final StorageService storageService;

    @Autowired
    public TourController(StorageService storageService) {
        this.storageService = storageService;
    }

    @Autowired
    private TourRepository tourRepository;

    @Autowired
    private TourTypeRepository tourTypeRepository;

    @Autowired
    private TourRepository2 tourRepository2;

    @Autowired
    private TourSpecification tourSpecification;


    @Autowired
    private UserRepository userRepository;

    @Value("${jwt.header}")
    private String tokenHeader;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;


    @Autowired
    private TourService tourService;


//    @Secured("ROLE_ADMIN")
//    @ResponseBody
//    @GetMapping(value = "custom-api/tours")
//    public ResponseEntity<?> getAllTour(
//            HttpServletRequest request,
//            @RequestParam(name = "page", defaultValue = "1") int pageNumber,
//            @RequestParam(name = "sort", defaultValue = "asc") String sort,
//            @RequestParam(name = "column", defaultValue = "id") String column,
//            @RequestParam(name = "paging", defaultValue = "5") int maxResult
//    ) {
//        HttpHeaders responseHeaders = new HttpHeaders();
//        Sort.Direction sortDirection = Sort.Direction.ASC;
//        if("desc".equals(sort)) sortDirection = Sort.Direction.DESC;
//
//        try {
//            PageRequest pageRequest = new PageRequest(pageNumber - 1, maxResult, sortDirection, column);
//            Iterable<Tour> tours = tourRepository.findAllByEnableEquals(pageRequest,true);
//            responseHeaders.set("Content-Type", "application/json");
//            return new ResponseEntity<>(JSONUtils.mapper.writeValueAsString(tours), responseHeaders, HttpStatus.OK);
//        }
//        catch(Exception e){
//            e.printStackTrace();
//            return new ResponseEntity<>(e.toString(), responseHeaders, HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }

    @Secured("ROLE_ADMIN")
    @ResponseBody
    @GetMapping(value = "custom-api/tours-count")
    public ResponseEntity<?> countAllTour() {
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("Content-Type", "application/json");
        Long amount = tourRepository.countAllByEnableEquals(true);
        try {
            return new ResponseEntity<>(JSONUtils.mapper.writeValueAsString(amount), responseHeaders, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(e.toString(), responseHeaders, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Secured("ROLE_ADMIN")
    @ResponseBody
    @GetMapping(value = "custom-api/tours/{id}")
    public ResponseEntity<?> getTourById(
            @PathVariable("id") Long id
    ) {
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("Content-Type", "application/json");
        Tour tour = tourRepository.findByIdAndEnableEquals(id, true);
        try {
            return new ResponseEntity<>(JSONUtils.mapper.writeValueAsString(tour), responseHeaders, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(e.toString(), responseHeaders, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @ResponseBody
    @PostMapping(value = "custom-api/tours")
    public ResponseEntity<?> addNewTour(
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
            Tour tour =  JSONUtils.mapper.readValue(content, Tour.class);
            tour.setUpdatedOn(new Date());
            tour.setUpdatedBy(updateBy);

            Tour addTour = tourRepository.save(tour);
            Long id = addTour.getId();
            if(file !=null && !file.isEmpty()) {
                storageService.store(file, "tour_" + id + ".png");
                tour.setImage("tour_"+ id +".png");
                tourRepository.save(tour);
            }

            responseHeaders.set("Content-Type", "application/json");
            return new ResponseEntity<>(JSONUtils.mapper.writeValueAsString(addTour), responseHeaders, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(e.toString(), responseHeaders, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @ResponseBody
    @PatchMapping(value = "custom-api/tours")
    public ResponseEntity<?> updateTourById(
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

            Tour tour =  JSONUtils.mapper.readValue(content, Tour.class);
            if(file !=null &&!file.isEmpty()) {
                storageService.store(file, "tour_" + tour.getId() + ".png");
                tour.setImage("tour_"+ tour.getId() +".png");
            }
            tour.setUpdatedOn(new Date());
            tour.setUpdatedBy(updateBy);
            Tour updatedTour = tourRepository.save(tour);
            responseHeaders.set("Content-Type", "application/json");
            return new ResponseEntity<>(JSONUtils.mapper.writeValueAsString(updatedTour), responseHeaders, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(e.toString(), responseHeaders, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @ResponseBody
    @DeleteMapping(value = "custom-api/tours/{id}")
    public ResponseEntity<?> deleteTourById(
            @PathVariable("id") Long id
    ) {
        HttpHeaders responseHeaders = new HttpHeaders();
        try {
            Tour deletedTour = tourService.deleteTourById(id);
            responseHeaders.set("Content-Type", "application/json");
            return new ResponseEntity<>(JSONUtils.mapper.writeValueAsString(deletedTour), responseHeaders, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            responseHeaders.set("MyResponseHeader", "MyValue");
            return new ResponseEntity<>("Cannot delete", responseHeaders, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Secured("ROLE_ADMIN")
    @ResponseBody
    @GetMapping(value = "custom-api/tours")
    public ResponseEntity<?> findTour(
            HttpServletRequest request,
            @RequestParam Map<String, String> filter,
            @RequestParam(name = "page", defaultValue = "1") int pageNumber,
            @RequestParam(name = "sort", defaultValue = "asc") String sort,
            @RequestParam(name = "column", defaultValue = "id") String column,
            @RequestParam(name = "paging", defaultValue = "5") int maxResult
    ) {

        Sort.Direction sortDirection = Sort.Direction.ASC;
        if("desc".equals(sort)) sortDirection = Sort.Direction.DESC;

        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("Content-Type", "application/json");
        try {
            PageRequest pageRequest = new PageRequest(pageNumber - 1, maxResult, sortDirection, column);
            Iterable<Tour> tours = tourSpecification.findAllQueryId(filter, pageRequest);
            return new ResponseEntity<>(JSONUtils.mapper.writeValueAsString(tours), responseHeaders, HttpStatus.OK);
        }
        catch(Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(e.toString(), responseHeaders, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

//    @Secured("ROLE_ADMIN")
//    @ResponseBody
//    @GetMapping(value = "custom-api/tours/findTourByDepPointAndDes")
//    public ResponseEntity<?> findTourByDepPointAndDes(
//            HttpServletRequest request,
//            @RequestParam(name = "from", defaultValue = "") String from,
//            @RequestParam(name = "to", defaultValue = "") String to,
//            @RequestParam(name = "page", defaultValue = "1") int pageNumber,
//            @RequestParam(name = "sort", defaultValue = "asc") String sort,
//            @RequestParam(name = "column", defaultValue = "id") String column,
//            @RequestParam(name = "paging", defaultValue = "5") int maxResult
//    ) {
//
//        Sort.Direction sortDirection = Sort.Direction.ASC;
//        if("desc".equals(sort)) sortDirection = Sort.Direction.DESC;
//
//        PageRequest pageRequest = new PageRequest(pageNumber - 1, maxResult, sortDirection, column);
//        Iterable<Tour> tours = tourRepository.findByDepartmentPointIgnoreCaseContainingAndDestinationIgnoreCaseContainingAndEnableEquals(pageRequest, from, to, true);
//
//        HttpHeaders responseHeaders = new HttpHeaders();
//        responseHeaders.set("Content-Type", "application/json");
//        try {
//            return new ResponseEntity<>(JSONUtils.mapper.writeValueAsString(tours), responseHeaders, HttpStatus.OK);
//        }
//        catch(Exception e){
//            e.printStackTrace();
//            return new ResponseEntity<>(e.toString(), responseHeaders, HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }
//
//    @Secured("ROLE_ADMIN")
//    @ResponseBody
//    @GetMapping(value = "custom-api/tours/findTourByTourType")
//    public ResponseEntity<?> findTourByTourType(
//            HttpServletRequest request,
//            @RequestParam(name = "tourTypeId", defaultValue = "") Long tourTypeId,
//            @RequestParam(name = "page", defaultValue = "1") int pageNumber,
//            @RequestParam(name = "sort", defaultValue = "asc") String sort,
//            @RequestParam(name = "column", defaultValue = "id") String column,
//            @RequestParam(name = "paging", defaultValue = "5") int maxResult
//    ) {
//
//        Sort.Direction sortDirection = Sort.Direction.ASC;
//        if("desc".equals(sort)) sortDirection = Sort.Direction.DESC;
//
//        PageRequest pageRequest = new PageRequest(pageNumber - 1, maxResult, sortDirection, column);
//        Iterable<Tour> tours = tourRepository.findByTourType_IdAndEnableEquals(pageRequest, tourTypeId, true);
//        HttpHeaders responseHeaders = new HttpHeaders();
//        responseHeaders.set("Content-Type", "application/json");
//        try {
//            return new ResponseEntity<>(JSONUtils.mapper.writeValueAsString(tours), responseHeaders, HttpStatus.OK);
//        }
//        catch(Exception e){
//            e.printStackTrace();
//            return new ResponseEntity<>(e.toString(), responseHeaders, HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }

}
