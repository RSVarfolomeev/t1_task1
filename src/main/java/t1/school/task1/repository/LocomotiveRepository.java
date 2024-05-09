package t1.school.task1.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import t1.school.task1.model.Locomotive;

@Repository
public interface LocomotiveRepository extends JpaRepository<Locomotive, Long> {

}
