package com.test.cglib;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class Client {
    public static void main(String[] args) {
        final Producer producer = new Producer();
        /*
         *动态代理的特点，字节码随用随创建，随用随加载，不修改源码的基础上对源码增强
         *动态代理有两类：基于接口的动态代理，涉及的类：Enhancer,提供者：第三方cglib库；基于子类的动态代理
         * 如何创建代理对象：使用Enhancer类中的create方法
         *创建代理对象的要求：被代理类不能是最终类
         * create方法的参数：
         *      Class 字节码，用于指定被代理对象的字节码
         *      Callback 用于提供增强的的代码。它是让我们写如何代理，我们一般是写一个该接口的实现类，通常情况下都是匿名类但不是必须的
         *      此接口的实现类都是谁用谁写
         *      一般写的是该接口的子接口实现类：MethodInterceptor
         */

        Producer cglibProducer = (Producer) Enhancer.create(producer.getClass(), new MethodInterceptor() {
            /**
             * @param proxy
             * @param method
             * @param args
             * @param methodProxy   当前执行方法的代理对象
             * @return
             * @throws Throwable
             */
            @Override
            public Object intercept(Object proxy, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
                Object returnValue = null;
                Float money = (Float)args[0];
                if ("saleProduct".equals(method.getName())) {
                    returnValue = method.invoke(producer, money * 0.8f);
                }

                return returnValue;
            }
        });

        cglibProducer.saleProduct(10000f);

    }
}
