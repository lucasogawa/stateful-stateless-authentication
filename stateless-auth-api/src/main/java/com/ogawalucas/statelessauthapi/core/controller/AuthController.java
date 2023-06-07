package com.ogawalucas.statelessauthapi.core.controller;

import com.ogawalucas.statelessauthapi.core.dto.AuthRequest;
import com.ogawalucas.statelessauthapi.core.dto.TokenDto;
import com.ogawalucas.statelessauthapi.core.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

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
}
