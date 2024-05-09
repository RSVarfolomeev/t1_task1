package t1.school.task1.service;

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
    public void addLocomotive(Locomotive locomotive) {
        locomotiveRepository.save(locomotive);
    }

    @TrackAsyncTime
    public void addLocomotiveWithCheck(Locomotive locomotive) {
        CompletableFuture<Void> future = CompletableFuture.runAsync(() -> {
            log.info("Start addLocomotiveWithCheck runAsync.");
            //Задержка - имитация проверки данных
            ThreadUtils.waitTime(2_000);
            locomotiveRepository.save(locomotive);
            log.info("End addLocomotiveWithCheck runAsync.");
        });
        future.join();
        log.info("End addLocomotiveWithCheck join.");
    }

    @TrackAsyncTime
    @Async
    @Scheduled(initialDelay = 5_000, fixedDelay = 10_000)
    public void addLocomotiveWithCheckTask() {
        log.info("Start addLocomotiveWithCheckTask.");
        //Задержка - имитация проверки данных
        ThreadUtils.waitTime(13_000);
        locomotiveRepository.save(new Locomotive(LocomotiveSeries.EP20, maxLocomotiveNumberCounter));
        maxLocomotiveNumberCounter++;
        log.info("End addLocomotiveWithCheckTask.");
    }
}
