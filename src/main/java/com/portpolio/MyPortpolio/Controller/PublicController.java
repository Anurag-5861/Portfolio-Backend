package com.portpolio.MyPortpolio.Controller;


import com.portpolio.MyPortpolio.DTO.*;
import com.portpolio.MyPortpolio.Entity.Users;
import com.portpolio.MyPortpolio.Repo.UsersRepo;
import com.portpolio.MyPortpolio.Service.Impl.StorageServiceImpl;
import com.portpolio.MyPortpolio.Service.Impl.UsersServiceImpl;
import com.portpolio.MyPortpolio.Util.JWTUtil;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.time.Duration;

@RestController
@RequestMapping("/my-portfolio")
public class PublicController {
    @Autowired
    private UsersServiceImpl usersService;
    @Autowired
    private UsersRepo usersRepo;
    @Autowired
    private StorageServiceImpl storageService;
    @Autowired
    private final AuthenticationManager authenticationManager;
    @Autowired
    private JWTUtil jwtUtil;
    @Autowired
    private final PasswordEncoder passwordEncoder;
    @Autowired
    private StringRedisTemplate redisTemplate;

    public PublicController(StringRedisTemplate redisTemplate, UsersServiceImpl usersService, UsersRepo usersRepo, StorageServiceImpl storageService, AuthenticationManager authenticationManager, JWTUtil jwtUtil, PasswordEncoder passwordEncoder) {
        this.redisTemplate = redisTemplate;
        this.usersService = usersService;
        this.usersRepo = usersRepo;
        this.storageService = storageService;
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
        this.passwordEncoder = passwordEncoder;
    }

    //Create user in the signup
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @PostMapping("/signup")
    public ResponseEntity<String> createUser(@Valid @RequestBody UsersSignUpRequest usersSignUpRequest){
        boolean emailExist = usersRepo.existsByEmail(usersSignUpRequest.getEmail());
        if(emailExist){
            return new ResponseEntity<String>("Email " + usersSignUpRequest.getEmail() + " Already exists" , HttpStatus.CONFLICT);
        }
            Users users = new Users();
            users.setEmail(usersSignUpRequest.getEmail());
            users.setPassword(passwordEncoder.encode(usersSignUpRequest.getPassword()));
            users.setFirstName(usersSignUpRequest.getFirstName());
            users.setLastName(usersSignUpRequest.getLastName());
            users.setRoles(usersSignUpRequest.getRoles());
            usersService.saveUser(users);
            return ResponseEntity.ok("User Registered");
    }

    @PostMapping("/login")
    public ResponseEntity<?> userLogin(@RequestBody UserLoginRequest userLoginRequest, HttpServletResponse response) {
        try {
            boolean emailExist = usersRepo.existsByEmail(userLoginRequest.getEmail());
            if (!emailExist) {
                return new ResponseEntity<>("Please enter a registered email", HttpStatus.BAD_REQUEST);
            }

            try {
                authenticationManager.authenticate(
                        new UsernamePasswordAuthenticationToken(userLoginRequest.getEmail(), userLoginRequest.getPassword())
                );
            } catch (AuthenticationException e) {
                throw new RuntimeException(e);
            }

            String jwtAccessToken = jwtUtil.generateToken(userLoginRequest.getEmail());
            String refreshToken = jwtUtil.generateRefreshToken(userLoginRequest.getEmail());

            redisTemplate.opsForValue().set(refreshToken, userLoginRequest.getEmail(), Duration.ofDays(7));

            ResponseCookie cookie = ResponseCookie.from("refreshToken", refreshToken)
                    .httpOnly(true)
                    .secure(false)
                    .path("/")
                    .maxAge(Duration.ofDays(7))
                    .sameSite("Strict")
                    .build();
            response.addHeader(HttpHeaders.SET_COOKIE, cookie.toString());

            return ResponseEntity.ok(new AuthResponse(jwtAccessToken, null, true));

        } catch (AuthenticationException e) {
            return new ResponseEntity<>("Please enter correct password.", HttpStatus.NOT_FOUND);
        }
    }
@PostMapping("/refreshtoken")
public ResponseEntity<AuthResponse> refreshToken(@RequestBody AuthResponse authResponse){
    String email = redisTemplate.opsForValue().get(authResponse.getRefreshToken());

    if(email == null || !jwtUtil.validateToken(authResponse.getRefreshToken(), email)){
        return new ResponseEntity<>(HttpStatus.UNAUTHORIZED); // 401 instead of 404
    }

    String newAccessToken = jwtUtil.generateToken(email);
    AuthResponse response = new AuthResponse();
    response.setAccessJWT(newAccessToken);
    response.setRefreshToken(authResponse.getRefreshToken());
    response.setSuccess(true);

    return ResponseEntity.ok(response);
}

    @GetMapping("/storage")
    public ResponseEntity<StorageRequest> storageUrls(){
        StorageRequest storageRequest = storageService.getStorageDetails();
        return ResponseEntity.ok(storageRequest);
    }

    @GetMapping("/health")
    public ResponseEntity<String> health() {
        return ResponseEntity.ok("ok");
    }
}
