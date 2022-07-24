package com.saber.multiplication_service;

import com.saber.multiplication_service.dto.ChallengeDto;
import com.saber.multiplication_service.services.ChallengeGeneratorService;
import com.saber.multiplication_service.services.impl.ChallengeGeneratorServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Random;

//@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class ChallengeGenerationTest {
    //@Autowired
    private ChallengeGeneratorService challengeGeneratorService;
    @Spy
    private Random random;

    @BeforeEach
    void setup(){
        challengeGeneratorService = new ChallengeGeneratorServiceImpl(random);
    }
    @Test
    void generateRandomFactorIsBetweenExpectedLimit(){
        BDDMockito.given(random.nextInt(89)).willReturn(20,30);

        ChallengeDto challengeDto = challengeGeneratorService.randomChallenge();

        Assertions.assertEquals(challengeDto,new ChallengeDto(31,41));
    }
}
