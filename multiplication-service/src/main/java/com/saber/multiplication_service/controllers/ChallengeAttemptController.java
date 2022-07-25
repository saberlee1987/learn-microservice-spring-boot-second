package com.saber.multiplication_service.controllers;

import com.saber.multiplication_service.dto.*;
import com.saber.multiplication_service.services.ChallengeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "${service.api.base-path}/attempts", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
@Tag(name = "challenge attempt",description = "challenge attempt")
public class ChallengeAttemptController {
    private final ChallengeService challengeService;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "attempt challenge", description = "attempt challenge", method = "POST",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(required = true,description = "challenge attempt"
                    ,content = @Content(mediaType = "application/json",examples = {@ExampleObject(name = "challenge attempt"
                    ,value ="{\"factorA\": 49,\"factorB\": 74,\"userAlias\": \"saber\",\"guess\": 3626}" )})))
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

    @GetMapping
    @Operation(summary = "last statistics", description = "last statistics", method = "GET",
            parameters = {
                    @Parameter(name = "alias", description = "alias", in = ParameterIn.QUERY, required = true, example = "saber")
            })
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Success", content = {
                            @Content(schema = @Schema(implementation = ChallengeAttemptListResponse.class), mediaType = "application/json")
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
    public ResponseEntity<ChallengeAttemptListResponse> getStatistics(@RequestParam(value = "alias") String userAlias){
        return ResponseEntity.ok(this.challengeService.getStatsForUser(userAlias));
    }
}
