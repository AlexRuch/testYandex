package org.ruchyov;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.ruchyov.service.CounterService;
import org.ruchyov.service.SaveDataService;
import org.ruchyov.service.impl.CleaningScheduleServiceImpl;
import org.ruchyov.service.impl.CounterServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class YandexTestApplicationTests {

    @Autowired
    CounterService counterService;

    @Autowired
    SaveDataService saveDataService;


    @Test
    public void contextLoads() {
    }


    @Test
    public void testCounter() throws InterruptedException {
        Random random = new Random();
        for (int i = 0; i < 1000; i++) {
            saveDataService.saveData();
            Thread.sleep(random.nextInt(300) + 30);
        }

        System.out.println("LAST MINUTE : " + counterService.lastMinuteCounter());
        System.out.println("LAST HOUR   : " + counterService.lastHourCounter());
        System.out.println("LAST DAY    : " + counterService.lastDayCounter());

    }

    @Test
    public void testThreadCounter() throws InterruptedException {

        int count = 1000000;

        for(int i = 0; i < count; i++){
            Thread saveThread = new Thread(() -> saveDataService.saveData());
            saveThread.start();

        }
        assertEquals(count,CounterServiceImpl.CounterDataList.size());
    }



}
