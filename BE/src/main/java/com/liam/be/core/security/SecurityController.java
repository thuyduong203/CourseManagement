package com.liam.be.core.security;

import com.liam.be.core.teacher.model.TeacherRequest;
import com.liam.be.entity.User;
import com.liam.be.infrastructure.config.security.CustomUserDetails;
import com.liam.be.infrastructure.config.security.JwtTokenProvider;
import com.liam.be.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class SecurityController {
    @Autowired
    private JwtTokenProvider jwtTokenProvider;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public String login(@RequestBody AccountLogin accountLogin) {
        User user = userRepository.findByCode(accountLogin.getUserName().toLowerCase()).orElse(null);
        if (user != null && passwordEncoder.matches(accountLogin.getPassword(), user.getAccount().getPassword())) {
            return jwtTokenProvider.generateToken(new CustomUserDetails(user.getAccount(), user));
        }
        return null;
    }

    @PostMapping("/register")
    public Map<String, String> registerTeacher(@RequestBody TeacherRequest request) {
        Map<String, String> map = new HashMap<>();
        TeacherRequest res = userService.addAdmin(request);
        map.put("user", res.getCode());
        map.put("password", res.getPassword());
        return map;
    }

    @GetMapping("/get-user-login")
    public UserLoginResponse getUserLogin() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return userRepository.getUserLogin(principal.toString().toLowerCase()).orElse(null);
    }

}
