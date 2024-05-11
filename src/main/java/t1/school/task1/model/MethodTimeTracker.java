package t1.school.task1.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "method_time_tracker")
@Getter
@Setter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class MethodTimeTracker {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "method_name")
    private String methodName;
    @Column(name = "execution_time")
    private Long executionTime;
}
