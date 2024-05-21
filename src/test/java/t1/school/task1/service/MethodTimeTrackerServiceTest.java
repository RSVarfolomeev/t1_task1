package t1.school.task1.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import t1.school.task1.model.MethodTimeTracker;
import t1.school.task1.model.dto.MethodAverageTimeResponseDto;
import t1.school.task1.model.dto.MethodSumTimeResponseDto;
import t1.school.task1.repository.MethodTimeTrackerRepository;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import static org.mockito.BDDMockito.given;

@SpringBootTest(classes = MethodTimeTrackerService.class)
class MethodTimeTrackerServiceTest {

    @MockBean
    private MethodTimeTrackerRepository methodTimeTrackerRepository;

    @Autowired
    private MethodTimeTrackerService methodTimeTrackerService;

    @Test
    void save() {
        MethodTimeTracker methodTimeTrackerToDB = MethodTimeTracker.builder()
                .methodName("addLocomotiveWithCheck")
                .executionTime(2015L)
                .build();

        MethodTimeTracker methodTimeTrackerFromDBForMock = MethodTimeTracker.builder()
                .methodName("addLocomotiveWithCheck")
                .executionTime(2015L)
                .build();

        Mockito.doReturn(methodTimeTrackerFromDBForMock)
                .when(methodTimeTrackerRepository)
                .save(Mockito.any(MethodTimeTracker.class));

        CompletableFuture<MethodTimeTracker> methodTimeTrackerForCheck = methodTimeTrackerService.save(methodTimeTrackerToDB.getMethodName(), methodTimeTrackerToDB.getExecutionTime());

        Mockito.verify(methodTimeTrackerRepository, Mockito.times(1))
                .save(Mockito.any(MethodTimeTracker.class));

        System.out.println("MethodTimeTracker для проверки: " + methodTimeTrackerForCheck);
        System.out.println("MethodTimeTracker, подложенный в Mock: " + methodTimeTrackerFromDBForMock);

        Assertions.assertNotNull(methodTimeTrackerForCheck);
        Assertions.assertEquals(methodTimeTrackerForCheck.join().getMethodName(), methodTimeTrackerToDB.getMethodName());
        Assertions.assertEquals(methodTimeTrackerForCheck.join().getExecutionTime(), methodTimeTrackerToDB.getExecutionTime());
    }

    @Test
    void getAverageMethodsTime() {
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

        given(methodTimeTrackerRepository.getAverageMethodsTime())
                .willReturn(List.of(methodAverageTimeResponseDto_1, methodAverageTimeResponseDto_2, methodAverageTimeResponseDto_3));

        List<MethodAverageTimeResponseDto> methodAverageTimeResponseDtoList = methodTimeTrackerService.getAverageMethodsTime();

        Assertions.assertNotNull(methodAverageTimeResponseDtoList);
        Assertions.assertEquals(3, methodAverageTimeResponseDtoList.size());
    }

    @Test
    void getSumMethodsTime() {
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

        given(methodTimeTrackerRepository.getSumMethodsTime())
                .willReturn(List.of(methodSumTimeResponseDto_1, methodSumTimeResponseDto_2, methodSumTimeResponseDto_3));

        List<MethodSumTimeResponseDto> methodSumTimeResponseDtoList = methodTimeTrackerService.getSumMethodsTime();

        Assertions.assertNotNull(methodSumTimeResponseDtoList);
        Assertions.assertEquals(3, methodSumTimeResponseDtoList.size());
    }
}