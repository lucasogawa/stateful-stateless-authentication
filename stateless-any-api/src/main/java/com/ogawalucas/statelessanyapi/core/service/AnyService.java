package com.ogawalucas.statelessanyapi.core.service;

import com.ogawalucas.statelessanyapi.core.dto.AnyResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AnyService {

    private final JwtService jwtService;

    public AnyResponse getData(String accessToken) {
        jwtService.validateAccessToken(accessToken);

        var authUser = jwtService.getAuthenticatedUser(accessToken);
        var ok = HttpStatus.OK;

        return new AnyResponse(ok.name(), ok.value(), authUser);
    }
}
