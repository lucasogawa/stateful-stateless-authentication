package com.ogawalucas.statelessanyapi.core.controller;

import com.ogawalucas.statelessanyapi.core.dto.AnyResponse;
import com.ogawalucas.statelessanyapi.core.service.AnyService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/resource")
public class AnyController {

    private final AnyService service;

    @GetMapping
    public AnyResponse getResource(@RequestHeader String accessToken) {
        return service.getData(accessToken);
    }
}