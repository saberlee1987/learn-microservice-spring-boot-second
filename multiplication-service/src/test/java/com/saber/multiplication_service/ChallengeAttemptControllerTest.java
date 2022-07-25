package com.saber.multiplication_service;

import com.saber.multiplication_service.dto.ChallengeAttempt;
import com.saber.multiplication_service.dto.ChallengeAttemptDto;
import com.saber.multiplication_service.dto.ChallengeDto;
import com.saber.multiplication_service.dto.UserDto;
import com.saber.multiplication_service.services.ChallengeService;
import org.assertj.core.api.BDDAssertions;
import org.eclipse.jetty.server.Authentication;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureWebMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureJsonTesters
public class ChallengeAttemptControllerTest {

    @MockBean
    private ChallengeService challengeService;

    @Autowired
    private MockMvc mvc;

    @Autowired
    private JacksonTester<ChallengeAttemptDto> jsonRequestAttempt;

    @Autowired
    private JacksonTester<ChallengeAttempt> jsonResultAttempt;

    @Test
    void postValidResult() {
        UserDto userDto = new UserDto( "saber66");
        long attemptId = 5L;
        ChallengeAttemptDto attemptDto = new ChallengeAttemptDto(50, 70, "saber66", 3500);
        ChallengeDto challengeDto = new ChallengeDto(50,70);
        ChallengeAttempt expectedResponse = new ChallengeAttempt(attemptId, userDto, challengeDto, 3500, true);

        try {
            BDDMockito.given(challengeService.verifyAttempt(attemptDto)).willReturn(expectedResponse);

            MockHttpServletResponse mockHttpServletResponse = mvc.perform(MockMvcRequestBuilders.post("/services/multiplication/attempts")
                    .contentType(MediaType.APPLICATION_JSON_VALUE)
                    .content(jsonRequestAttempt.write(attemptDto).getJson())).andReturn().getResponse();
            BDDAssertions.then(mockHttpServletResponse.getStatus()).isEqualTo(HttpStatus.OK.value());
            BDDAssertions.then(mockHttpServletResponse.getContentAsString())
                    .isEqualTo(jsonResultAttempt.write(expectedResponse).getJson());

        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    @Test
    void postInvalidResult(){
        ChallengeAttemptDto attemptDto = new ChallengeAttemptDto(2000, -70, "saber66", 1);
        try {
        MockHttpServletResponse response = mvc.perform(
                MockMvcRequestBuilders.post("/services/multiplication/attempts").contentType(MediaType.APPLICATION_JSON)
                        .content(jsonRequestAttempt.write(attemptDto).getJson()))
                .andReturn().getResponse();

        BDDAssertions.then(response.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());

        }catch (Exception ex){
            ex.printStackTrace();
        }
    }
}
