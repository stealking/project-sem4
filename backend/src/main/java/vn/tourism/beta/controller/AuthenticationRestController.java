package vn.tourism.beta.controller;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import vn.tourism.beta.entity.User;
import vn.tourism.beta.repository.UserRepository;
import vn.tourism.beta.security.JwtAuthenticationRequest;
import vn.tourism.beta.security.JwtTokenUtil;
import vn.tourism.beta.security.JwtUser;
import vn.tourism.beta.service.JwtAuthenticationResponse;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import vn.tourism.beta.service.SmtpMailSender;
import vn.tourism.beta.utils.JSONUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestController
public class AuthenticationRestController {
    private final Log logger = LogFactory.getLog(this.getClass());

    @Value("${jwt.header}")
    private String tokenHeader;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private SmtpMailSender mailSender;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @RequestMapping(value = "auth", method = RequestMethod.POST)
    public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtAuthenticationRequest authenticationRequest) throws AuthenticationException {
        logger.info(authenticationRequest.getUsername());
        logger.info(authenticationRequest.getPassword());
        // Perform the security
        final Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        authenticationRequest.getUsername(),
                        authenticationRequest.getPassword()
                )
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);

        // Reload password post-security so we can generate token
        final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());
        final String token = jwtTokenUtil.generateToken(userDetails);

        // Return the token
        return ResponseEntity.ok(new JwtAuthenticationResponse(token));
    }

    @RequestMapping(value = "${jwt.route.authentication.refresh}", method = RequestMethod.GET)
    public ResponseEntity<?> refreshAndGetAuthenticationToken(HttpServletRequest request) {
        String token = request.getHeader(tokenHeader);
        String username = jwtTokenUtil.getUsernameFromToken(token);
        JwtUser user = (JwtUser) userDetailsService.loadUserByUsername(username);

        if (jwtTokenUtil.canTokenBeRefreshed(token, user.getLastPasswordResetDate())) {
            String refreshedToken = jwtTokenUtil.refreshToken(token);
            return ResponseEntity.ok(new JwtAuthenticationResponse(refreshedToken));
        } else {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @RequestMapping(value = "registration", method = RequestMethod.POST)
    public ResponseEntity<?> registration(
            HttpServletResponse response,
            @RequestParam("content") String content
    ) {
        HttpHeaders responseHeaders = new HttpHeaders();
        try {
            User user = JSONUtils.mapper.readValue(content, User.class);
            user.setUpdatedOn(new Date());
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            userRepository.save(user);

            String token = jwtTokenUtil.generateToken2(user);

            String recipientAddress = user.getEmail();
            String subject = "Registration Confirmation";
            String confirmationUrl
                    =  "/registration-confirm?token=" + token;
            String message = "Click để đăng nhập";

            SimpleMailMessage email = new SimpleMailMessage();
            email.setTo(recipientAddress);
            email.setSubject(subject);
            email.setText(message + " rn" + "http://localhost:8080" + confirmationUrl);
//            mailSender.send(email);
            Map<String, String> params =  new HashMap<String, String>();
            params.put("message", message + " rn" + "http://localhost:8080" + confirmationUrl);
            mailSender.send(recipientAddress, subject, "<a href='http://localhost:8080" + confirmationUrl + "'> Click here</a> ", params);

            responseHeaders.set("Content-Type", "application/json");
            return new ResponseEntity<>(responseHeaders, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(e.toString(), responseHeaders, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @RequestMapping(value = "registration-confirm", method = RequestMethod.GET)
    public String confirmRegistration(
            HttpServletResponse response,
            @RequestParam("token") String token) {
        try {
            String username = jwtTokenUtil.getUsernameFromToken(token);
            User user = userRepository.findByUsername(username);
            user.setEnable(true);
            userRepository.save(user);
            response.sendRedirect("http://localhost:8081/pages/login");
            return null;
        } catch (Exception e){
            return e.toString();
        }

    }

    @RequestMapping(value = "forget-password", method = RequestMethod.POST)
    public ResponseEntity<?> forgetPassword(
            HttpServletResponse response,
            @RequestParam("email") String email
    ) {
        HttpHeaders responseHeaders = new HttpHeaders();
        try {
            User user = userRepository.findByEmail(email);
            if(user == null) {return new ResponseEntity<>(responseHeaders, HttpStatus.NOT_FOUND);}
            String token = jwtTokenUtil.generateToken2(user);

            String recipientAddress = user.getEmail();
            String subject = "Reset password confirmation";
            String confirmationUrl
                    =  "/reset-password-confirm?token=" + token;
            String message = "Click để đăng nhập";

            SimpleMailMessage emailSend = new SimpleMailMessage();
            emailSend.setTo(recipientAddress);
            emailSend.setSubject(subject);
            emailSend.setText(message + " rn" + "http://localhost:8080" + confirmationUrl);
//            mailSender.send(email);
            Map<String, String> params =  new HashMap<String, String>();
            params.put("message", message + " rn" + "http://localhost:8080" + confirmationUrl);
            mailSender.send(recipientAddress, subject, "<a href='http://localhost:8080" + confirmationUrl + "'> Bấm vào để reset password</a> ", params);

            responseHeaders.set("Content-Type", "application/json");
            return new ResponseEntity<>(responseHeaders, HttpStatus.OK);
            } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(e.toString(), responseHeaders, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "reset-password-confirm", method = RequestMethod.GET)
    public ResponseEntity<?> resetPasswordConfirm(
            HttpServletResponse response,
            @RequestParam("token") String token) {
        try {
            String username = jwtTokenUtil.getUsernameFromToken(token);
            if(username == null || username.isEmpty()){
                response.sendRedirect("http://localhost:8081/pages/reset-password-confirm?token=");
            }
            response.sendRedirect("http://localhost:8081/pages/reset-password-confirm?token=" + token);
            return null;
        } catch (Exception e){
            return new ResponseEntity<>(e.toString(), HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

}
