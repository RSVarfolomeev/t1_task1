package t1.school.task1.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import t1.school.task1.model.MethodTimeTracker;
import t1.school.task1.model.dto.MethodAverageTimeResponseDto;
import t1.school.task1.model.dto.MethodSumTimeResponseDto;

import java.util.List;

@Repository
public interface MethodTimeTrackerRepository extends JpaRepository<MethodTimeTracker, Long> {


    @Query("SELECT new t1.school.task1.model.dto.MethodAverageTimeResponseDto(m.methodName, AVG(m.executionTime))" +
            " FROM MethodTimeTracker m GROUP BY m.methodName")
    List<MethodAverageTimeResponseDto> getAverageMethodsTime();

    @Query("SELECT new t1.school.task1.model.dto.MethodSumTimeResponseDto(m.methodName, COUNT(m.methodName), SUM(m.executionTime))" +
            " FROM MethodTimeTracker m GROUP BY m.methodName")
    List<MethodSumTimeResponseDto> getSumMethodsTime();
}
