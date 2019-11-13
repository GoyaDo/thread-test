package com.cj;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @author cj
 * @date 2019-11-07 - 21:25
 */
public class ThreadTest02 {
    static ReentrantLock reentrantLock = new ReentrantLock();

    public static void main(String[] args) {
        Thread t1 = new Thread() {
            @Override
            public void run() {
                testsync();
            }
        };

        t1.setName("t1");

        Thread t2 = new Thread() {
            @Override
            public void run() {
                testsync();
            }
        };
        t2.setName("t2");

        t1.start();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        t2.start();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }



        System.out.println("main");

        //没有其他意义，只是一个标志,改变用户行为
        t2.interrupt();


    }


    public static void testsync() {

        String name = Thread.currentThread().getName();

//        reentrantLock.lock();

        try {
            //针对在队列中的线程可以打断
            reentrantLock.lockInterruptibly();

        } catch (InterruptedException e) {
            System.out.println("xxxxxxxxxxxxxxxxxxxxxxxxxxxx");
            e.printStackTrace();
        }

        System.out.println(name);
        try {
            Thread.sleep(2000000000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(name);
        reentrantLock.unlock();

    }

    //模拟自旋
//    volatile int status = 0;//标识是否在同步块---是否有线程上锁成功
//    void lock(){
//        while (!compareAndSet(0,1)){//耗资源
//
//        }
//        //lock
//
//    }
//    void unlock(){
//        status = 0;
//    }
//
//    boolean compareAndSet(int except,int newValue){
//        //CAS操作，修改status成功则返回true
//        return false;
//    }

    //yield+自旋
    //    volatile int status = 0;//标识是否在同步块---是否有线程上锁成功
//    void lock(){
//        while (!compareAndSet(0,1)){//耗资源
//              yield();//自己实现
    //因为yield是cpu调度，无法保证下一次调度的就是你想要的线程，再很多线程处理的情况下
//        }
//        //lock
//
//    }
//    void unlock(){
//        status = 0;
//    }
//
//    boolean compareAndSet(int except,int newValue){
//        //CAS操作，修改status成功则返回true
//        return false;
//    }

    //sleep+自旋
    //    volatile int status = 0;//标识是否在同步块---是否有线程上锁成功
//    void lock(){
//        while (!compareAndSet(0,1)){//耗资源
//              sleep(10);
    //这里睡多久无法确定
//        }
//        //lock
//
//    }
//    void unlock(){
//        status = 0;
//    }
//
//    boolean compareAndSet(int except,int newValue){
//        //CAS操作，修改status成功则返回true
//        return false;
//    }


}
