package com.saber.multiplication_service;

import com.saber.multiplication_service.dto.ChallengeAttempt;
import com.saber.multiplication_service.dto.ChallengeAttemptDto;
import com.saber.multiplication_service.services.ChallengeService;
import com.saber.multiplication_service.services.impl.ChallengeServiceImpl;
import org.assertj.core.api.BDDAssertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ChallengeServiceTest {

    private ChallengeService challengeService;

    @BeforeEach
    void setup() {
        challengeService = new ChallengeServiceImpl();
    }

    @Test
    void checkCorrectAttemptTest() {

        ChallengeAttemptDto attemptDto = new ChallengeAttemptDto(50, 60, "", 3000);

        ChallengeAttempt resultAttempt = challengeService.verifyAttempt(attemptDto);
        BDDAssertions.then(resultAttempt.isCorrect()).isTrue();
    }
}
