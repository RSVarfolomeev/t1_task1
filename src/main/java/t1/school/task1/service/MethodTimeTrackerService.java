package t1.school.task1.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
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

//    @Transactional
    public void save(MethodTimeTracker methodTimeTracker) {
        CompletableFuture.runAsync(() -> {
            //    int i = 1;
//            if (i==1)
//                throw new RuntimeException("Exception in addLocomotiveWithCheckTask");
            methodExecutionRepository.save(methodTimeTracker);
        });
    }

    public List<MethodAverageTimeResponseDto> getAverageMethodsTime() {
        return methodExecutionRepository.getAverageMethodsTime();
    }

    public List<MethodSumTimeResponseDto> getSumMethodsTime() {
        return methodExecutionRepository.getSumMethodsTime();
    }
}
