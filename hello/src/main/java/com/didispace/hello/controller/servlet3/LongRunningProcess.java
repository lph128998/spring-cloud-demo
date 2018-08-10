package com.didispace.hello.controller.servlet3;

import java.util.concurrent.ThreadLocalRandom;

/**
 * @author liupeihua
 * @date 2018/6/15 上午10:21
 */
public class LongRunningProcess {

    public void run() {
        int mills = ThreadLocalRandom.current().nextInt(2000);

        String currentThread = Thread.currentThread().getName();
        System.out.println(currentThread + " sleep for " + mills + " milliseconds.");
        try {
            Thread.sleep(mills);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
        }
    }
}
