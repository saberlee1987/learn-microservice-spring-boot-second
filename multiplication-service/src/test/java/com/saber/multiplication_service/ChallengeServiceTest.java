package com.saber.multiplication_service;

import com.saber.multiplication_service.dto.ChallengeAttempt;
import com.saber.multiplication_service.dto.ChallengeAttemptDto;
import com.saber.multiplication_service.dto.UserDto;
import com.saber.multiplication_service.repositories.ChallengeAttemptRepository;
import com.saber.multiplication_service.repositories.UserRepository;
import com.saber.multiplication_service.services.ChallengeService;
import com.saber.multiplication_service.services.impl.ChallengeServiceImpl;
import org.assertj.core.api.BDDAssertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.AdditionalAnswers;
import org.mockito.BDDMockito;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class ChallengeServiceTest {

    private ChallengeService challengeService;
    @Mock
    private ChallengeAttemptRepository challengeAttemptRepository;
    @Mock
    private UserRepository userRepository;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
        challengeService = new ChallengeServiceImpl(userRepository, challengeAttemptRepository);
        BDDMockito.given(challengeAttemptRepository.save(BDDMockito.any())).will(AdditionalAnswers.returnsFirstArg());
    }

    @Test
    void checkCorrectAttemptTest() {

        ChallengeAttemptDto attemptDto = new ChallengeAttemptDto(50, 60, "saber", 3000);

        ChallengeAttempt resultAttempt = challengeService.verifyAttempt(attemptDto);
        BDDAssertions.then(resultAttempt.isCorrect()).isTrue();

        BDDMockito.verify(userRepository).save(new UserDto("saber"));
        BDDMockito.verify(challengeAttemptRepository).save(resultAttempt);
    }
}
