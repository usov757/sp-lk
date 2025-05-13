package ru.bgpu.splk.controllers;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.web.bind.annotation.*;
import ru.bgpu.splk.dto.login.LoginRequestDto;
import ru.bgpu.splk.dto.login.LoginUserDto;
import ru.bgpu.splk.services.UserService;

@RestController
@RequestMapping("/auth")
public class LoginController {

    @Autowired private AuthenticationManager authenticationManager;
    @Autowired private UserService userService;

    @PostMapping("/login")
    public LoginUserDto login(
            @RequestBody LoginRequestDto loginRequest,
            HttpServletRequest request, HttpServletResponse response
    ) {
        Authentication authenticationRequest = UsernamePasswordAuthenticationToken.unauthenticated(
                loginRequest.getLogin(), loginRequest.getPassword()
        );
        Authentication authentication = this.authenticationManager.authenticate(
                authenticationRequest
        );
        SecurityContext context = SecurityContextHolder.createEmptyContext();
        context.setAuthentication(authentication);
        HttpSessionSecurityContextRepository secRepo = new HttpSessionSecurityContextRepository();
        secRepo.saveContext(context, request, response);
        return userService.getByLogin(
                SecurityContextHolder.getContext().getAuthentication().getName()
        ).toLoginUserDto();
    }

    @GetMapping("/current-user")
    public LoginUserDto currentUser() {
        return userService.sessionUser().toLoginUserDto();
    }

}
