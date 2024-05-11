package t1.school.task1.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import t1.school.task1.model.Locomotive;
import t1.school.task1.model.enums.LocomotiveSeries;
import t1.school.task1.repository.LocomotiveRepository;

@SpringBootTest(classes = LocomotiveService.class)
class LocomotiveServiceTest {

    @MockBean
    private LocomotiveRepository locomotiveRepository;

    @Autowired
    private LocomotiveService locomotiveService;

    @Test
    void addLocomotive() {
        Locomotive locomotiveToDB = Locomotive.builder()
                .locomotiveSeries(LocomotiveSeries.TEP70BS)
                .locomotiveNumber(300)
                .build();

        Locomotive locomotiveFromDBForMock = Locomotive.builder()
                .locomotiveSeries(LocomotiveSeries.TEP70BS)
                .locomotiveNumber(300)
                .build();

        Mockito.doReturn(locomotiveFromDBForMock)
                .when(locomotiveRepository)
                .save(Mockito.any(Locomotive.class));

        Locomotive locomotiveForCheck = locomotiveService.addLocomotive(locomotiveToDB.getLocomotiveSeries(), locomotiveToDB.getLocomotiveNumber());

        Mockito.verify(locomotiveRepository, Mockito.times(1))
                .save(Mockito.any(Locomotive.class));

        System.out.println("Локомотив для проверки: " + locomotiveForCheck);
        System.out.println("Локомотив, подложенный в Mock: " + locomotiveFromDBForMock);

        Assertions.assertNotNull(locomotiveForCheck);
        Assertions.assertEquals(locomotiveForCheck.getLocomotiveNumber(), locomotiveToDB.getLocomotiveNumber());
        Assertions.assertEquals(locomotiveForCheck.getLocomotiveSeries(), locomotiveToDB.getLocomotiveSeries());
    }

    @Test
    void addLocomotiveWithCheck() {
        Locomotive locomotiveToDB = Locomotive.builder()
                .locomotiveSeries(LocomotiveSeries.TEP70BS)
                .locomotiveNumber(300)
                .build();

        Locomotive locomotiveFromDBForMock = Locomotive.builder()
                .locomotiveSeries(LocomotiveSeries.TEP70BS)
                .locomotiveNumber(300)
                .build();

        Mockito.doReturn(locomotiveFromDBForMock)
                .when(locomotiveRepository)
                .save(Mockito.any(Locomotive.class));

        Locomotive locomotiveForCheck = locomotiveService.addLocomotiveWithCheck(locomotiveToDB.getLocomotiveSeries(), locomotiveToDB.getLocomotiveNumber());

        Mockito.verify(locomotiveRepository, Mockito.times(1))
                .save(Mockito.any(Locomotive.class));

        System.out.println("Локомотив для проверки: " + locomotiveForCheck);
        System.out.println("Локомотив, подложенный в Mock: " + locomotiveFromDBForMock);

        Assertions.assertNotNull(locomotiveForCheck);
        Assertions.assertEquals(locomotiveForCheck.getLocomotiveNumber(), locomotiveToDB.getLocomotiveNumber());
        Assertions.assertEquals(locomotiveForCheck.getLocomotiveSeries(), locomotiveToDB.getLocomotiveSeries());
    }
}