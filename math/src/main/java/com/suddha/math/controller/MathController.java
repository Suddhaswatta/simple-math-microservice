package com.suddha.math.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Sinks;

@RequestMapping("/${api.uri.base}")
@RestController
public class MathController {

    Sinks.Many<Long> sinks = Sinks.many().multicast().onBackpressureBuffer();

    @GetMapping(value = "/${api.uri.square}/{number}")
    public void square(@PathVariable Long number) {
        sinks.tryEmitNext(number);
    }

    @GetMapping(value = "/${api.uri.square}/result", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<Object> results() {
        return Flux.create(objectFluxSink -> sinks.asFlux()
                .doOnNext(objectFluxSink::next)
                .subscribe());
    }
}
