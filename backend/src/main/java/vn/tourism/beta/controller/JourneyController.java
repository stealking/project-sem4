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
import vn.tourism.beta.entity.Journey;
import vn.tourism.beta.entity.User;
import vn.tourism.beta.repository.JourneyRepository;
import vn.tourism.beta.repository.UserRepository;
import vn.tourism.beta.security.JwtTokenUtil;
import vn.tourism.beta.storage.StorageService;
import vn.tourism.beta.utils.JSONUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@RestController
public class JourneyController {
    private final Log logger = LogFactory.getLog(this.getClass());
    private final StorageService storageService;

    @Autowired
    public JourneyController(StorageService storageService) {
        this.storageService = storageService;
    }

    @Autowired
    private JourneyRepository journeyRepository;

    @Autowired
    private UserRepository userRepository;

    @Value("${jwt.header}")
    private String tokenHeader;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;



    @Secured("ROLE_ADMIN")
    @ResponseBody
    @GetMapping(value = "custom-api/journeys")
    public ResponseEntity<?> getAllJourney(
            HttpServletRequest request,
            @RequestParam(name = "page", defaultValue = "1") int pageNumber,
            @RequestParam(name = "sort", defaultValue = "asc") String sort,
            @RequestParam(name = "column", defaultValue = "id") String column,
            @RequestParam(name = "paging", defaultValue = "5") int maxResult
    ) {

        Sort.Direction sortDirection = Sort.Direction.ASC;
        if("desc".equals(sort)) sortDirection = Sort.Direction.DESC;

        PageRequest pageRequest = new PageRequest(pageNumber - 1, maxResult, sortDirection, column);
        Iterable<Journey> journeys = journeyRepository.findAllByEnableEquals(pageRequest, true);

        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("Content-Type", "application/json");
        try {
            return new ResponseEntity<>(JSONUtils.mapper.writeValueAsString(journeys), responseHeaders, HttpStatus.OK);
        }
        catch(Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(e.toString(), responseHeaders, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Secured("ROLE_ADMIN")
    @ResponseBody
    @GetMapping(value = "custom-api/journeys-count")
    public ResponseEntity<?> getAllJourney() {
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("Content-Type", "application/json");
        Long amount = journeyRepository.countAllByEnableEquals(true);
        try {
            return new ResponseEntity<>(JSONUtils.mapper.writeValueAsString(amount), responseHeaders, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(e.toString(), responseHeaders, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Secured("ROLE_ADMIN")
    @ResponseBody
    @GetMapping(value = "custom-api/journeys/{id}")
    public ResponseEntity<?> getJourneyById(
            @PathVariable("id") Long id
    ) {
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("Content-Type", "application/json");
        Journey journey = journeyRepository.findByIdAndEnableEquals(id, true);
        try {
            return new ResponseEntity<>(JSONUtils.mapper.writeValueAsString(journey), responseHeaders, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(e.toString(), responseHeaders, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @ResponseBody
    @PostMapping(value = "custom-api/journeys")
    public ResponseEntity<?> addNewJourney(
            @RequestBody Journey journey,
            HttpServletRequest request
    ) {
        String header = request.getHeader("Authorization");
        String authToken = header.substring(7);
        String username = jwtTokenUtil.getUsernameFromToken(authToken);
        User updateBy = userRepository.findByUsername(username);
        HttpHeaders responseHeaders = new HttpHeaders();
        try {
            journey.setUpdatedOn(new Date());
            journey.setUpdatedBy(updateBy);
            Journey addJourney = journeyRepository.save(journey);
            responseHeaders.set("Content-Type", "application/json");
            return new ResponseEntity<>(JSONUtils.mapper.writeValueAsString(addJourney), responseHeaders, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(e.toString(), responseHeaders, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @ResponseBody
    @PatchMapping(value = "custom-api/journeys")
    public ResponseEntity<?> updateJourneyById(
            @RequestBody Journey journey,
            HttpServletRequest request
    ) {
        String header = request.getHeader("Authorization");
        String authToken = header.substring(7);
        String username = jwtTokenUtil.getUsernameFromToken(authToken);
        User updateBy = userRepository.findByUsername(username);
        HttpHeaders responseHeaders = new HttpHeaders();
        try {
            journey.setUpdatedOn(new Date());
            journey.setUpdatedBy(updateBy);
            Journey updatedJourney = journeyRepository.save(journey);
            responseHeaders.set("Content-Type", "application/json");
            return new ResponseEntity<>(JSONUtils.mapper.writeValueAsString(updatedJourney), responseHeaders, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(e.toString(), responseHeaders, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }



    @ResponseBody
    @DeleteMapping(value = "custom-api/journeys/{id}")
    public ResponseEntity<?> deleteJourneyById(
            @PathVariable("id") Long id
    ) {
        HttpHeaders responseHeaders = new HttpHeaders();
        try {
            Journey journey = journeyRepository.findByIdAndEnableEquals(id, true);
            journey.setEnable(false);
            journeyRepository.save(journey);
            responseHeaders.set("Content-Type", "application/json");
            return new ResponseEntity<>(JSONUtils.mapper.writeValueAsString(journey), responseHeaders, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            responseHeaders.set("MyResponseHeader", "MyValue");
            return new ResponseEntity<>("Cannot delete", responseHeaders, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
