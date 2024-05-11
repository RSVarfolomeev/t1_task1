package t1.school.task1.service;

import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import t1.school.task1.annotations.TrackTime;
import t1.school.task1.annotations.TrackAsyncTime;
import org.springframework.stereotype.Service;
import t1.school.task1.model.Locomotive;
import t1.school.task1.model.enums.LocomotiveSeries;
import t1.school.task1.repository.LocomotiveRepository;
import t1.school.task1.utils.ThreadUtils;

import java.util.concurrent.CompletableFuture;

@Service
@Slf4j
public class LocomotiveService {

    private int maxLocomotiveNumberCounter = 1;

    private final LocomotiveRepository locomotiveRepository;

    public LocomotiveService(LocomotiveRepository locomotiveRepository) {
        this.locomotiveRepository = locomotiveRepository;
    }

    @TrackTime
    public Locomotive addLocomotive(LocomotiveSeries locomotiveSeries, Integer locomotiveNumber) {
        return locomotiveRepository.save(Locomotive.builder()
                .locomotiveSeries(locomotiveSeries)
                .locomotiveNumber(locomotiveNumber)
                .build());
    }

    @TrackAsyncTime
    public Locomotive addLocomotiveWithCheck(@NonNull LocomotiveSeries locomotiveSeries, @NonNull Integer locomotiveNumber) {
        try {
            CompletableFuture<Locomotive> future = CompletableFuture.supplyAsync(() -> {
                log.info("Start addLocomotiveWithCheck runAsync.");
                //Задержка - имитация проверки данных
                ThreadUtils.waitTime(2_000);
                Locomotive locomotive = locomotiveRepository.save(Locomotive.builder()
                        .locomotiveSeries(locomotiveSeries)
                        .locomotiveNumber(locomotiveNumber)
                        .build());
                log.info("End addLocomotiveWithCheck runAsync.");
                return locomotive;
            });

            Locomotive locomotive = future.join();
            log.info("End addLocomotiveWithCheck join. Locomotive={}", locomotive);
            return locomotive;
        } catch (Exception ex) {
            throw new RuntimeException("Возникла ошибка в методе добавления локомотива с проверкой данных: ", ex);
        }
    }

    @TrackAsyncTime
    @Async
    @Scheduled(initialDelay = 5_000, fixedDelay = 10_000)
    public void addLocomotiveWithCheckTask() {
        try {
            //Создание локомотива
            Locomotive locomotive = Locomotive.builder()
                    .locomotiveSeries(LocomotiveSeries.EP20)
                    .locomotiveNumber(maxLocomotiveNumberCounter)
                    .build();
            log.info("Start addLocomotiveWithCheckTask. Locomotive={}", locomotive);

            maxLocomotiveNumberCounter++;

            //Задержка - имитация проверки данных
            ThreadUtils.waitTime(13_000);

            locomotiveRepository.save(locomotive);
            log.info("End addLocomotiveWithCheckTask. Locomotive={}", locomotive);
        } catch (Exception ex) {
            maxLocomotiveNumberCounter--;
            log.error("Возникла ошибка в периодической задаче добавления локомотива с проверкой данных: ", ex);
        }
    }
}
