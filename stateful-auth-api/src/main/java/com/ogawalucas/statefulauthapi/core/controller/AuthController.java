package com.ogawalucas.statefulauthapi.core.controller;

import com.ogawalucas.statefulauthapi.core.dto.AuthRequest;
import com.ogawalucas.statefulauthapi.core.dto.AuthUserResponse;
import com.ogawalucas.statefulauthapi.core.dto.TokenDto;
import com.ogawalucas.statefulauthapi.core.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/auth")
public class AuthController {

    private final AuthService service;

    @PostMapping("login")
    public TokenDto login(@RequestBody AuthRequest request) {
        return service.login(request);
    }

    @PostMapping("token/validate")
    public TokenDto validateToken(@RequestHeader String accessToken) {
        return service.validateToken(accessToken);
    }

    @PostMapping("logout")
    public HashMap<String, Object> logout(@RequestHeader String accessToken) {
        service.logout(accessToken);

        var response = new HashMap<String, Object>();

        response.put("status", "OK");
        response.put("code", 200);

        return response;
    }

    @GetMapping("user")
    public AuthUserResponse getAuthenticatedUser(@RequestHeader String accessToken) {
        return service.getAuthenticatedUser(accessToken);
    }
}
