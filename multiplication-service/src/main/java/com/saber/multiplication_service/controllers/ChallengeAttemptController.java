package com.saber.multiplication_service.controllers;

import com.saber.multiplication_service.dto.ChallengeAttempt;
import com.saber.multiplication_service.dto.ChallengeAttemptDto;
import com.saber.multiplication_service.dto.ErrorDto;
import com.saber.multiplication_service.dto.HelloDto;
import com.saber.multiplication_service.services.ChallengeService;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "challenge attempt",description = "challenge attempt")
public class ChallengeAttemptController {
    private final ChallengeService challengeService;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Success", content = {
                            @Content(schema = @Schema(implementation = ChallengeAttempt.class), mediaType = "application/json")
                    }),
                    @ApiResponse(responseCode = "400", description = "BAD_REQUEST", content = {
                            @Content(schema = @Schema(implementation = ErrorDto.class), mediaType = "application/json")
                    })
                    ,
                    @ApiResponse(responseCode = "401", description = "UNAUTHORIZED", content = {
                            @Content(schema = @Schema(implementation = ErrorDto.class), mediaType = "application/json")
                    })
                    ,
                    @ApiResponse(responseCode = "403", description = "FORBIDDEN", content = {
                            @Content(schema = @Schema(implementation = ErrorDto.class), mediaType = "application/json")
                    })
                    ,
                    @ApiResponse(responseCode = "404", description = "NOT_FOUND", content = {
                            @Content(schema = @Schema(implementation = ErrorDto.class), mediaType = "application/json")
                    })
                    ,
                    @ApiResponse(responseCode = "406", description = "NOT_ACCEPTABLE", content = {
                            @Content(schema = @Schema(implementation = ErrorDto.class), mediaType = "application/json")
                    })

                    ,
                    @ApiResponse(responseCode = "500", description = "INTERNAL_SERVER_ERROR", content = {
                            @Content(schema = @Schema(implementation = ErrorDto.class), mediaType = "application/json")
                    })

                    ,
                    @ApiResponse(responseCode = "503", description = "SERVICE_UNAVAILABLE", content = {
                            @Content(schema = @Schema(implementation = ErrorDto.class), mediaType = "application/json")
                    })
                    ,
                    @ApiResponse(responseCode = "504", description = "GATEWAY_TIMEOUT", content = {
                            @Content(schema = @Schema(implementation = ErrorDto.class), mediaType = "application/json")
                    })
            }
    )
    public ResponseEntity<ChallengeAttempt> postResult(@RequestBody @Valid ChallengeAttemptDto attemptDto){
        return ResponseEntity.ok(challengeService.verifyAttempt(attemptDto));
    }
}
