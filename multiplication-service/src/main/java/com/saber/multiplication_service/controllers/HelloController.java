package com.saber.multiplication_service.controllers;

import com.saber.multiplication_service.dto.ErrorDto;
import com.saber.multiplication_service.dto.HelloDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequestMapping(value = "${service.api.base-path}/hello")
@Tag(name = "hello",description = "hello service")
public class HelloController {


    @GetMapping(value = "/sayHello")
    @Operation(summary = "say Hello", description = "say Hello", method = "GET",
            parameters = {
                    @Parameter(name = "firstName", description = "firstName", in = ParameterIn.QUERY, required = true, example = "saber"),
                    @Parameter(name = "lastName", description = "lastName", in = ParameterIn.QUERY, required = true, example = "Azizi"),
            })
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Success", content = {
                            @Content(schema = @Schema(implementation = HelloDto.class), mediaType = "application/json")
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
    public ResponseEntity<HelloDto> sayHello(@RequestParam(value = "firstName") String firstName,
                                             @RequestParam(value = "lastName") String lastName) {

        log.info("Request for sayHello with firstName {} , lastName {} ", firstName, lastName);

        String message = String.format("Hello %s %s", firstName, lastName);

        HelloDto helloDto = new HelloDto();
        helloDto.setMessage(message);

        log.info("Response for sayHello with firstName {} , lastName {} ====> {}", firstName, lastName, helloDto);

        return ResponseEntity.ok(helloDto);
    }

}
