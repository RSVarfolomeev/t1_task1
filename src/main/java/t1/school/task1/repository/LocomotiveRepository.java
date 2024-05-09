package t1.school.task1.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import t1.school.task1.model.Locomotive;
import t1.school.task1.model.enums.LocomotiveSeries;

import java.util.List;


@Repository
public interface LocomotiveRepository extends JpaRepository<Locomotive, Long> {
    List<Locomotive> findAllByLocomotiveSeries(LocomotiveSeries locomotiveSeries);

    List<Locomotive> findAllByLocomotiveSeriesAndAndLocomotiveNumber(LocomotiveSeries locomotiveSeries, int locomotiveNumber);
}
