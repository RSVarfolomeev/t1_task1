package t1.school.task1;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import t1.school.task1.model.Locomotive;
import t1.school.task1.model.enums.LocomotiveSeries;
import t1.school.task1.service.LocomotiveService;


@SpringBootApplication
@RequiredArgsConstructor
public class Task1Application {

	private final LocomotiveService locomotiveService;

	public static void main(String[] args) {
		SpringApplication.run(Task1Application.class, args);
	}

	@EventListener(ApplicationReadyEvent.class)
	public void onReady() {
		locomotiveService.addLocomotive(new Locomotive(LocomotiveSeries.EP1M, 1));
		locomotiveService.addLocomotiveWithCheck(new Locomotive(LocomotiveSeries.TEP70BS, 300));
	}
}