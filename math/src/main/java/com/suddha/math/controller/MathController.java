package com.suddha.math.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RequestMapping("/${api.version}/${api.name}")
@RestController
public class MathController {

    @GetMapping(value = "/${api.endpoint}" , produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Mono<Object> results(){

        return Mono.empty();
    }
}
