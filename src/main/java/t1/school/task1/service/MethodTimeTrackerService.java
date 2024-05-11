package t1.school.task1.service;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import t1.school.task1.model.MethodTimeTracker;
import t1.school.task1.model.dto.MethodAverageTimeResponseDto;
import t1.school.task1.model.dto.MethodSumTimeResponseDto;
import t1.school.task1.repository.MethodTimeTrackerRepository;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@Service
public class MethodTimeTrackerService {

    private final MethodTimeTrackerRepository methodExecutionRepository;

    public MethodTimeTrackerService(MethodTimeTrackerRepository methodExecutionRepository) {
        this.methodExecutionRepository = methodExecutionRepository;
    }

    @Async
    public CompletableFuture<MethodTimeTracker> save(String methodName, Long executionTime) {
        return CompletableFuture.completedFuture(methodExecutionRepository.save(MethodTimeTracker.builder()
                .methodName(methodName)
                .executionTime(executionTime)
                .build()));
    }

    public List<MethodAverageTimeResponseDto> getAverageMethodsTime() {
        return methodExecutionRepository.getAverageMethodsTime();
    }

    public List<MethodSumTimeResponseDto> getSumMethodsTime() {
        return methodExecutionRepository.getSumMethodsTime();
    }
}
