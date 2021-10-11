package com.utils;

import org.aspectj.lang.ProceedingJoinPoint;

public class Logger {
    /**
    *计划让其在切入点方法执行之前执行（业务层方法），前置通知
     */
    public void printLog() {
        System.out.println("Logger类中开始记录日志");
    }

    /**
     *后置通知
     */
    public void afterPrintLog() {
        System.out.println("Logger类中afterPrintLog方法开始记录日志");
    }

    /**
     *异常通知
     */
    public void afterThrowPrintLog() {
        System.out.println("Logger类中afterThrowPrintLog方法开始记录日志");
    }

    /**
     * 最终通知
     */
    public void finalPrintLog() {
        System.out.println("Logger类中finalPrintLog方法开始记录日志");
    }

    /**
     * 环绕通知
     * spring框架提供了ProceedingJoinPoint接口，该接口中有一个方法proceed(),明确调用切入点方法
     * 该接口可以作为环绕通知的方法参数，
     */
    public Object aroundPrintLog(ProceedingJoinPoint proceedingJoinPoint) {
        try {
            Object rtValue;
            Object[] args = proceedingJoinPoint.getArgs();
            System.out.println("Logger类中around-before开始记录日志");     //写在proceed之前表示前置通知
            rtValue = proceedingJoinPoint.proceed(args);
            System.out.println("Logger类中around-after开始记录日志");     //写在proceed之后表示后置通知
            return rtValue;
        } catch (Throwable t) {
            System.out.println("Logger类中around-throw开始记录日志");     //写在catch里面表示异常通知
            return new RuntimeException(t);
        } finally {
            System.out.println("Logger类中around-final开始记录日志");     //写在proceed之前表示最终通知
        }
    }
}
