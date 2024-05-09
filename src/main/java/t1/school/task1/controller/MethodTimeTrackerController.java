package t1.school.task1.controller;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import t1.school.task1.model.dto.MethodAverageTimeResponseDto;
import t1.school.task1.model.dto.MethodSumTimeResponseDto;
import t1.school.task1.service.MethodTimeTrackerService;

import java.util.List;


@RestController
@RequestMapping("/statistics")
public class MethodTimeTrackerController {

    private final MethodTimeTrackerService methodTimeTrackerService;

    public MethodTimeTrackerController(MethodTimeTrackerService methodTimeTrackerService) {
        this.methodTimeTrackerService = methodTimeTrackerService;
    }

    @Operation(summary = "Получение среднего времени выполнения каждого метода")
    @GetMapping("/averageTime")
    public ResponseEntity<List<MethodAverageTimeResponseDto>> getAverageMethodsTime() {
        return ResponseEntity.ok(methodTimeTrackerService.getAverageMethodsTime());
    }

    @Operation(summary = "Получение суммарного времени выполнения каждого метода" +
            " и суммарного количества вызовов по каждому методу")
    @GetMapping("/sumTime")
    public ResponseEntity<List<MethodSumTimeResponseDto>> getSumMethodsTime() {
        return ResponseEntity.ok(methodTimeTrackerService.getSumMethodsTime());
    }
}
