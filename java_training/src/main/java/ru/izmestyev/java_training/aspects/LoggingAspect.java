package ru.izmestyev.java_training.aspects;


import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import java.util.HashMap;
import java.util.Map;

@Aspect
public class LoggingAspect {
    private Map<Class<?>, Integer> counter = new HashMap<>();

    @AfterReturning("allLogEventMethods()")
    public void count(JoinPoint joinPoint) {
        Class<?> clazz = joinPoint.getTarget().getClass();
        if (!counter.containsKey(clazz)) {
            counter.put(clazz, 0);
        }
        counter.put(clazz, counter.get(clazz) + 1);
    }

    @Pointcut("execution(* *.logEvent(..))")
    private void allLogEventMethods() {
    }

    @Before("allLogEventMethods()")
    public void logBefore(JoinPoint joinPoint) {
        System.out.println("BEFORE: " + joinPoint.getTarget().getClass().getSimpleName() +
                joinPoint.getSignature().getName());
    }

    @AfterReturning("execution(* ru.izmestyev.java_training.App.logEvent(..))")
    public void outputLoggerCounter() {
        System.out.println("Number of calls: ");
        for (Map.Entry<Class<?>, Integer> count : counter.entrySet()) {
            System.out.println(count.getKey().getSimpleName() + " " + count.getValue());
        }
    }
}