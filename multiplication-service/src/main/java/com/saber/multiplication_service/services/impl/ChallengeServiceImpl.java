package com.saber.multiplication_service.services.impl;

import com.saber.multiplication_service.dto.ChallengeAttempt;
import com.saber.multiplication_service.dto.ChallengeAttemptDto;
import com.saber.multiplication_service.dto.UserDto;
import com.saber.multiplication_service.services.ChallengeService;
import org.springframework.stereotype.Service;

@Service
public class ChallengeServiceImpl implements ChallengeService {
    @Override
    public ChallengeAttempt verifyAttempt(ChallengeAttemptDto challengeAttemptDto) {

        boolean isCorrect = challengeAttemptDto.getGuess() == (challengeAttemptDto.getFactorA() * challengeAttemptDto.getFactorB());
        UserDto userDto = new UserDto(null, challengeAttemptDto.getUserAlias());

        ChallengeAttempt checkedAttempt = new ChallengeAttempt(null, userDto,
                challengeAttemptDto.getFactorA(), challengeAttemptDto.getFactorB(), challengeAttemptDto.getGuess(), isCorrect);
        return checkedAttempt;
    }
}
