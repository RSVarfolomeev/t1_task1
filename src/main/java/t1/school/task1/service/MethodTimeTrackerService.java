package t1.school.task1.service;

import org.springframework.stereotype.Service;
import t1.school.task1.model.MethodTimeTracker;
import t1.school.task1.model.dto.MethodAverageTimeResponseDto;
import t1.school.task1.model.dto.MethodSumTimeResponseDto;
import t1.school.task1.repository.MethodTimeTrackerRepository;

import java.util.List;

@Service
public class MethodTimeTrackerService {

    private final MethodTimeTrackerRepository methodExecutionRepository;

    public MethodTimeTrackerService(MethodTimeTrackerRepository methodExecutionRepository) {
        this.methodExecutionRepository = methodExecutionRepository;
    }

    public void save(MethodTimeTracker methodTimeTracker) {
        methodExecutionRepository.save(methodTimeTracker);
    }

    public List<MethodAverageTimeResponseDto> getAverageMethodsTime() {
        return methodExecutionRepository.getAverageMethodsTime();
    }

    public List<MethodSumTimeResponseDto> getSumMethodsTime() {
        return methodExecutionRepository.getSumMethodsTime();
    }
}
