package t1.school.task1.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import t1.school.task1.model.dto.MethodAverageTimeResponseDto;
import t1.school.task1.model.dto.MethodSumTimeResponseDto;
import t1.school.task1.service.LocomotiveService;
import t1.school.task1.service.MethodTimeTrackerService;

import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(MethodTimeTrackerController.class)
class MethodTimeTrackerControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MethodTimeTrackerService methodTimeTrackerService;

    @MockBean
    private LocomotiveService locomotiveService;


    @Test
    void getAverageMethodsTime() throws Exception {
        MethodAverageTimeResponseDto methodAverageTimeResponseDto_1 = MethodAverageTimeResponseDto.builder()
                .methodName("addLocomotive")
                .averageTime(60.0)
                .build();

        MethodAverageTimeResponseDto methodAverageTimeResponseDto_2 = MethodAverageTimeResponseDto.builder()
                .methodName("addLocomotiveWithCheck")
                .averageTime(2015.0)
                .build();

        MethodAverageTimeResponseDto methodAverageTimeResponseDto_3 = MethodAverageTimeResponseDto.builder()
                .methodName("addLocomotiveWithCheckTask")
                .averageTime(13006.0)
                .build();


        when(methodTimeTrackerService.getAverageMethodsTime())
                .thenReturn(List.of(methodAverageTimeResponseDto_1, methodAverageTimeResponseDto_2, methodAverageTimeResponseDto_3));

        mockMvc.perform(get("/statistics/averageTime"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$", hasSize(3)))
                .andExpect(jsonPath("$.[0].Метод", is("addLocomotive")))
                .andExpect(jsonPath("$.[1].Метод", is("addLocomotiveWithCheck")))
                .andExpect(content().string(containsString("addLocomotiveWithCheckTask")))
                .andExpect(jsonPath("$.[0].['Среднее время выполнения, мс']", is(60.0)));
    }

    @Test
    void getSumMethodsTime() throws Exception {
        MethodSumTimeResponseDto methodSumTimeResponseDto_1 = MethodSumTimeResponseDto.builder()
                .methodName("addLocomotive")
                .executionCount(2L)
                .sumTime(120.0)
                .build();

        MethodSumTimeResponseDto methodSumTimeResponseDto_2 = MethodSumTimeResponseDto.builder()
                .methodName("addLocomotiveWithCheck")
                .executionCount(3L)
                .sumTime(6045.0)
                .build();

        MethodSumTimeResponseDto methodSumTimeResponseDto_3 = MethodSumTimeResponseDto.builder()
                .methodName("addLocomotiveWithCheckTask")
                .executionCount(1L)
                .sumTime(13006.0)
                .build();

        when(methodTimeTrackerService.getSumMethodsTime())
                .thenReturn(List.of( methodSumTimeResponseDto_1,  methodSumTimeResponseDto_2,  methodSumTimeResponseDto_3));

        mockMvc.perform(get("/statistics/sumTime"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$", hasSize(3)))
                .andExpect(jsonPath("$.[0].Метод", is("addLocomotive")))
                .andExpect(jsonPath("$.[1].Метод", is("addLocomotiveWithCheck")))
                .andExpect(content().string(containsString("addLocomotiveWithCheckTask")))
                .andExpect(jsonPath("$.[2].['Количество выполнений']", is(1)))
                .andExpect(jsonPath("$.[0].['Суммарное время выполнения, мс']", is(120.0)));
    }
}