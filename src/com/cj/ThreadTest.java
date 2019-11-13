package com.cj;

/**
 * @author cj
 * @date 2019-11-06 - 22:03
 */
public class ThreadTest {
    public static void main(String[] args) {
        Thread thread = new Thread(){
            @Override
            public void run() {
                System.out.println("0000000000");
            }
        };
        thread.start();
    }
}
