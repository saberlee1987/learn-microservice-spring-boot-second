package com.saber.multiplication_service.controllers;

import com.saber.multiplication_service.dto.ChallengeAttempt;
import com.saber.multiplication_service.dto.ChallengeAttemptDto;
import com.saber.multiplication_service.services.ChallengeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "${service.api.base-path}/attempts", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class ChallengeAttemptController {
    private final ChallengeService challengeService;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ChallengeAttempt> postResult(@RequestBody @Valid ChallengeAttemptDto attemptDto){
        return ResponseEntity.ok(challengeService.verifyAttempt(attemptDto));
    }
}
