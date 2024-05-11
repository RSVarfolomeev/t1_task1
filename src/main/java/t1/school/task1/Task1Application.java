package t1.school.task1;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import t1.school.task1.model.enums.LocomotiveSeries;
import t1.school.task1.service.LocomotiveService;

import java.util.concurrent.CompletableFuture;


@SpringBootApplication
@RequiredArgsConstructor
public class Task1Application {

    private final LocomotiveService locomotiveService;

    public static void main(String[] args) {
        SpringApplication.run(Task1Application.class, args);
    }

    @EventListener(ApplicationReadyEvent.class)
    public void onReady() {
        locomotiveService.addLocomotive(LocomotiveSeries.EP1M, 1);
        CompletableFuture.runAsync(() ->
                locomotiveService.addLocomotiveWithCheck(LocomotiveSeries.TEP70BS, 299));
        CompletableFuture.runAsync(() ->
                locomotiveService.addLocomotiveWithCheck(LocomotiveSeries.TEP70BS, 300));
    }
}