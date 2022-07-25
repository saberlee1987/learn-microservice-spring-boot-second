package com.saber.multiplication_service.services;

import com.saber.multiplication_service.dto.ChallengeAttempt;
import com.saber.multiplication_service.dto.ChallengeAttemptDto;
import com.saber.multiplication_service.dto.ChallengeAttemptListResponse;

import java.util.List;

public interface ChallengeService {
    ChallengeAttempt verifyAttempt(ChallengeAttemptDto challengeAttemptDto);
    ChallengeAttemptListResponse getStatsForUser(String userAlias);
}
