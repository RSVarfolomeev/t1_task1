package t1.school.task1.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import t1.school.task1.model.MethodTimeTracker;
import t1.school.task1.service.MethodTimeTrackerService;


@Component
@Aspect
@Slf4j
public class TimeTrackerAspect {
    private final MethodTimeTrackerService methodTimeTrackerService;

    public TimeTrackerAspect(MethodTimeTrackerService methodTimeTrackerService) {
        this.methodTimeTrackerService = methodTimeTrackerService;
    }

    @Pointcut("@annotation(t1.school.task1.annotations.TrackTime)")
    public void trackTimePoincut() {
    }

    @Around("trackTimePoincut()")
    public Object trackTime(ProceedingJoinPoint joinPoint) {
        long startTime = System.currentTimeMillis();
        String methodName = joinPoint.getSignature().getName();
        log.info("Запущен метод: {}.", methodName);
        try {
            Object proceed = joinPoint.proceed();
            long timeToExecute = System.currentTimeMillis() - startTime;
            log.info("Выполнен метод {} за {} мс.", methodName, timeToExecute);
            methodTimeTrackerService.save(new MethodTimeTracker(methodName, timeToExecute));
            return proceed;
        } catch (Throwable e) {
            log.error("При выполнении метода {} произошла ошибка: ", methodName, e);
            return null;
        }
    }
}
