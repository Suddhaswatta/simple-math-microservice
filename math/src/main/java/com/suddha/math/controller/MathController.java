package com.suddha.math.controller;

import com.suddha.math.dto.MathDTO;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import static org.springframework.http.MediaType.*;

@RequestMapping("/${api.uri.base}")
@RestController
public class MathController {

    @GetMapping(value = "/${api.uri.square}/{number}")
    public Mono<MathDTO> results(@PathVariable Long number) {
        return Mono
                .fromSupplier(() -> number * number)
                .map(MathDTO::new)
                .log();

    }
}
