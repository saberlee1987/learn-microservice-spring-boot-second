package com.saber.multiplication_service.controllers;

import com.saber.multiplication_service.dto.ChallengeDto;
import com.saber.multiplication_service.services.ChallengeGeneratorService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequestMapping(value = "${service.api.base-path}/challenges", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "challenge", description = "challenge service")
@RequiredArgsConstructor
public class ChallengeController {

    private final ChallengeGeneratorService challengeGeneratorService;


    @GetMapping
    public ResponseEntity<ChallengeDto> randomChallenge() {
        ChallengeDto challengeDto = challengeGeneratorService.randomChallenge();
        log.info("Generating a random challenge : {} ", challengeDto);
        return ResponseEntity.ok(challengeDto);
    }

}
