package com.cj;

import javax.xml.transform.Source;
import java.util.concurrent.locks.LockSupport;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author cj
 * @date 2019-11-07 - 21:53
 *
 * park+自旋
 *
 */
public class ThreadPark {
    static ReentrantLock reentrantLock = new ReentrantLock();

    public static void main(String[] args) {
        Thread t1 = new Thread() {
            @Override
            public void run() {
                testsync();
            }
        };

        t1.setName("t1");
        t1.start();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("main---1");
        LockSupport.unpark(t1);

    }

    public static void testsync() {
        System.out.println("t1 -1");
        //让线程立马睡眠
        LockSupport.park();
        System.out.println("t1 ------2");
    }
    //模拟park+自旋
//    volatile int status = 0;//标识是否在同步块---是否有线程上锁成功
    //Queue parkQueue;//集合 数组 list
//    void lock(){
//        while (!compareAndSet(0,1)){//耗资源
//             park();
//        }
//        //lock

    //unlock();
//
//    }
//    void unlock(){
    //status=0;
//        lock_notify;
//    }
//
    //void park(){
    //将当前线程假如到等待队列
    //parkQueue.add(currentThread);
    //将当前线程释放cpu 阻塞
    //repleaseCpu();
//}
//    void lock_notify(){
//        //得到要唤醒的线程头部线程
//        Thread t = parkQueue.header();
//        //唤醒等待线程
//        unpark(t);
//    }

//    boolean compareAndSet(int except,int newValue){
//        //CAS操作，修改status成功则返回true
//        return false;
//    }

}
