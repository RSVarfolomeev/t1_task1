package t1.school.task1.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import t1.school.task1.model.MethodTimeTracker;
import t1.school.task1.service.MethodTimeTrackerService;

import java.util.concurrent.CompletableFuture;


@Component
@Aspect
@Slf4j
public class AsyncTimeTrackerAspect {
    private final MethodTimeTrackerService methodTimeTrackerService;

    public AsyncTimeTrackerAspect(MethodTimeTrackerService methodTimeTrackerService) {
        this.methodTimeTrackerService = methodTimeTrackerService;
    }

    @Pointcut("@annotation(t1.school.task1.annotations.TrackAsyncTime)")
    public void trackAsyncTimePointcut() {
    }

    @Around("trackAsyncTimePointcut()")
    public Object trackAsyncTime(ProceedingJoinPoint joinPoint) {
        return CompletableFuture.runAsync(() -> {
            long startTime = System.currentTimeMillis();
            String methodName = joinPoint.getSignature().getName();
            log.info("Запущен метод: {}.", methodName);
            try {
                joinPoint.proceed();
                long timeToExecute = System.currentTimeMillis() - startTime;
                log.info("Выполнен метод {} за {} мс.", methodName, timeToExecute);
                methodTimeTrackerService.save(new MethodTimeTracker(methodName, timeToExecute));
            } catch (Throwable e) {
                log.error("При выполнении метода {} произошла ошибка: ", methodName, e);
            }
        });
    }
}
