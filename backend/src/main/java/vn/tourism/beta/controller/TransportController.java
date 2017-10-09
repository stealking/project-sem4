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
import vn.tourism.beta.entity.Transport;
import vn.tourism.beta.entity.User;
import vn.tourism.beta.repository.TransportRepository;
import vn.tourism.beta.repository.UserRepository;
import vn.tourism.beta.security.JwtTokenUtil;
import vn.tourism.beta.storage.StorageService;
import vn.tourism.beta.utils.JSONUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

@RestController
public class TransportController {
    private final Log logger = LogFactory.getLog(this.getClass());
    private final StorageService storageService;

    @Autowired
    public TransportController(StorageService storageService) {
        this.storageService = storageService;
    }

    @Autowired
    private TransportRepository transportRepository;

    @Autowired
    private UserRepository userRepository;

    @Value("${jwt.header}")
    private String tokenHeader;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;



    @Secured("ROLE_ADMIN")
    @ResponseBody
    @GetMapping(value = "custom-api/transports")
    public ResponseEntity<?> getAllTransport(
            HttpServletRequest request,
            @RequestParam(name = "page", defaultValue = "1") int pageNumber,
            @RequestParam(name = "sort", defaultValue = "asc") String sort,
            @RequestParam(name = "column", defaultValue = "id") String column,
            @RequestParam(name = "paging", defaultValue = "5") int maxResult
    ) {

        Sort.Direction sortDirection = Sort.Direction.ASC;
        if("desc".equals(sort)) sortDirection = Sort.Direction.DESC;

        PageRequest pageRequest = new PageRequest(pageNumber - 1, maxResult, sortDirection, column);
        Iterable<Transport> transports = transportRepository.findAllByEnableEquals(pageRequest, true);

        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("Content-Type", "application/json");
        try {
            return new ResponseEntity<>(JSONUtils.mapper.writeValueAsString(transports), responseHeaders, HttpStatus.OK);
        }
        catch(Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(e.toString(), responseHeaders, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Secured("ROLE_ADMIN")
    @ResponseBody
    @GetMapping(value = "custom-api/get-all-transports")
    public ResponseEntity<?> getAllTransport() {
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("Content-Type", "application/json");
        List<Transport> transports = transportRepository.findAllByEnableTrue();
        try {
            return new ResponseEntity<>(JSONUtils.mapper.writeValueAsString(transports), responseHeaders, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(e.toString(), responseHeaders, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Secured("ROLE_ADMIN")
    @ResponseBody
    @GetMapping(value = "custom-api/transports/{id}")
    public ResponseEntity<?> getTransportById(
            @PathVariable("id") Long id
    ) {
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("Content-Type", "application/json");
        Transport transport = transportRepository.findByIdAndEnableEquals(id, true);
        try {
            return new ResponseEntity<>(JSONUtils.mapper.writeValueAsString(transport), responseHeaders, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(e.toString(), responseHeaders, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @ResponseBody
    @PostMapping(value = "custom-api/transports")
    public ResponseEntity<?> addNewTransport(
            @RequestBody Transport transport,
            HttpServletRequest request
    ) {
        String header = request.getHeader("Authorization");
        String authToken = header.substring(7);
        String username = jwtTokenUtil.getUsernameFromToken(authToken);
        User updateBy = userRepository.findByUsername(username);
        HttpHeaders responseHeaders = new HttpHeaders();
        try {
            transport.setUpdatedOn(new Date());
            transport.setUpdatedBy(updateBy);
            Transport addTransport = transportRepository.save(transport);
            responseHeaders.set("Content-Type", "application/json");
            return new ResponseEntity<>(JSONUtils.mapper.writeValueAsString(addTransport), responseHeaders, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(e.toString(), responseHeaders, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @ResponseBody
    @PatchMapping(value = "custom-api/transports")
    public ResponseEntity<?> updateTransportById(
            @RequestBody Transport transport,
            HttpServletRequest request
    ) {
        String header = request.getHeader("Authorization");
        String authToken = header.substring(7);
        String username = jwtTokenUtil.getUsernameFromToken(authToken);
        User updateBy = userRepository.findByUsername(username);
        HttpHeaders responseHeaders = new HttpHeaders();
        try {
            transport.setUpdatedOn(new Date());
            transport.setUpdatedBy(updateBy);
            Transport updatedTransport = transportRepository.save(transport);
            responseHeaders.set("Content-Type", "application/json");
            return new ResponseEntity<>(JSONUtils.mapper.writeValueAsString(updatedTransport), responseHeaders, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(e.toString(), responseHeaders, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }



    @ResponseBody
    @DeleteMapping(value = "custom-api/transports/{id}")
    public ResponseEntity<?> deleteTransportById(
            @PathVariable("id") Long id
    ) {
        HttpHeaders responseHeaders = new HttpHeaders();
        try {
            Transport transport = transportRepository.findByIdAndEnableEquals(id, true);
            transport.setEnable(false);
            transportRepository.save(transport);
            responseHeaders.set("Content-Type", "application/json");
            return new ResponseEntity<>(JSONUtils.mapper.writeValueAsString(transport), responseHeaders, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            responseHeaders.set("MyResponseHeader", "MyValue");
            return new ResponseEntity<>("Cannot delete", responseHeaders, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
