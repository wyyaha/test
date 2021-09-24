package com.test.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class Client {
    public static void main(String[] args) {
        final Producer producer = new Producer();
        /*
         *动态代理的特点，字节码随用随创建，随用随加载，不修改源码的基础上对源码增强
         *动态代理有两类：基于接口的动态代理，涉及的类：Proxy,提供者：JDK官方；基于子类的动态代理
         *创建代理对象的要求：被代理类最少实现一个接口，如果没有则不能用
         * newProxyInstance方法的参数：
         *      ClassLoader 类加载器，用于加载代理对象字节码，和被代理对象Producer使用相同的加载器
         *      Class[] 字节码数组，用于让代理对象和被代理对象有相同的接口
         *      InvocationHandler 用于提供增强的的代码。它是让我们写如何代理，我们一般是写一个该接口的实现类，通常情况下都是匿名类但不是必须的
         *      此接口的实现类都是谁用谁写
         */
        IProducer proxyProducer = (IProducer) Proxy.newProxyInstance(producer.getClass().getClassLoader(), producer.getClass().getInterfaces(), new InvocationHandler() {
            // 执行被代理对象的任何接口方法都会经过该方法
            /*
                proxy 指代理对象的引用
                method 当前执行的方法
                args 当前执行方法所需的参数
                return 和被代理对象方法有相同的返回值
             */
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                Object returnValue = null;
                Float money = (Float)args[0];
                if ("saleProduct".equals(method.getName())) {
                    returnValue = method.invoke(producer, money * 0.8f);
                }

                return returnValue;
            }
        });

        proxyProducer.saleProduct(10000f);

    }
}
