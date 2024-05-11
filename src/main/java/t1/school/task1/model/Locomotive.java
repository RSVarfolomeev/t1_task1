package t1.school.task1.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import t1.school.task1.model.enums.LocomotiveSeries;

import java.sql.Timestamp;

@Entity
@Table(name = "locomotives")
@Getter
@Setter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Locomotive {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Enumerated(EnumType.STRING)
    @Column(name = "locomotive_series", nullable = false)
    private LocomotiveSeries locomotiveSeries;
    @Column(name = "locomotive_number", nullable = false)
    private Integer locomotiveNumber;
    @CreationTimestamp
    @Column(name = "date_of_added")
    private Timestamp dateOfAdded;
}
