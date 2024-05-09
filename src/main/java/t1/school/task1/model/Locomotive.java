package t1.school.task1.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import t1.school.task1.model.enums.LocomotiveSeries;

import java.sql.Timestamp;

@Entity
@Table(name = "locomotives")
@Getter
@Setter
@NoArgsConstructor
public class Locomotive {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Enumerated(EnumType.STRING)
    @Column(name = "locomotive_series")
    private LocomotiveSeries locomotiveSeries;
    @Column(name = "locomotive_number")
    private int locomotiveNumber;
    @CreationTimestamp
    @Column(name = "date_of_added")
    private Timestamp dateOfAdded;

    public Locomotive(LocomotiveSeries locomotiveSeries, int locomotiveNumber) {
        this.locomotiveSeries = locomotiveSeries;
        this.locomotiveNumber = locomotiveNumber;
    }
}
