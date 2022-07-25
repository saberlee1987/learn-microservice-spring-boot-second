package com.saber.multiplication_service.services.impl;

import com.saber.multiplication_service.dto.*;
import com.saber.multiplication_service.repositories.ChallengeAttemptRepository;
import com.saber.multiplication_service.repositories.UserRepository;
import com.saber.multiplication_service.services.ChallengeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class ChallengeServiceImpl implements ChallengeService {

    private final UserRepository userRepository;
    private final ChallengeAttemptRepository challengeAttemptRepository;

    public ChallengeServiceImpl(UserRepository userRepository, ChallengeAttemptRepository challengeAttemptRepository) {
        this.userRepository = userRepository;
        this.challengeAttemptRepository = challengeAttemptRepository;
    }

    @Override
    @Transactional
    public ChallengeAttempt verifyAttempt(ChallengeAttemptDto challengeAttemptDto) {

        String userAlias = challengeAttemptDto.getUserAlias();
        Optional<UserDto> optionalUser = this.userRepository.findByAlias(userAlias);
        UserDto userDto;
        if (optionalUser.isPresent())
            userDto = optionalUser.get();
        else {
            log.info("Creating new user with alias {}", userAlias);
            userDto = userRepository.save(new UserDto(userAlias));
        }
        boolean isCorrect = challengeAttemptDto.getGuess() == (challengeAttemptDto.getFactorA() * challengeAttemptDto.getFactorB());
        //UserDto userDto = new UserDto( challengeAttemptDto.getUserAlias());

        ChallengeDto challenge = new ChallengeDto(challengeAttemptDto.getFactorA(), challengeAttemptDto.getFactorB());
        ChallengeAttempt challengeAttempt = new ChallengeAttempt(null, userDto,
                challenge, challengeAttemptDto.getGuess(), isCorrect);

        return challengeAttemptRepository.save(challengeAttempt);
    }

    @Override
    @Transactional(readOnly = true)
    public ChallengeAttemptListResponse getStatsForUser(String userAlias) {
        return new ChallengeAttemptListResponse(challengeAttemptRepository.lastAttempts(userAlias));
    }
}