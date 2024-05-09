package t1.school.task1.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "method_time_tracker")
@Getter
@Setter
@NoArgsConstructor
public class MethodTimeTracker {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "method_name")
    private String methodName;
    @Column(name = "execution_time")
    private long executionTime;

    public MethodTimeTracker(String methodName, long executionTime) {
        this.methodName = methodName;
        this.executionTime = executionTime;
    }
}
