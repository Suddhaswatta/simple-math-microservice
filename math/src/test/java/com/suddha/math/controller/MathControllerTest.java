package com.suddha.math.controller;

import com.suddha.math.dto.MathDTO;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.reactive.server.WebTestClient;

import static org.assertj.core.api.Assertions.assertThat;

@WebFluxTest(controllers = MathController.class)
@AutoConfigureWebTestClient
@ActiveProfiles("test")
class MathControllerTest {

    @Autowired
    WebTestClient client;

    @Value("/${api.uri.base}")
    String baseUri;

    @Value("/${api.uri.square}/{number}")
    String squareUri;

    MathDTO.MathDTOBuilder dtoBuilder;

    @BeforeEach
    void setUp() {
        dtoBuilder = MathDTO.builder();
    }

    @Test
    void when_square_endpoint_gets_called_then_return_squaredNo() {
        String number = "4";

        client.get()
                .uri(baseUri + squareUri, number)
                .exchange()
                .expectStatus()
                .isOk()
                .expectBody(MathDTO.class)
                .consumeWith(mathDTOEntityExchangeResult ->
                        assertThat(mathDTOEntityExchangeResult.getResponseBody())
                        .isEqualTo(dtoBuilder.withResult(16L).build())
                );
    }

    @AfterEach
    void tearDown() {
    }
}