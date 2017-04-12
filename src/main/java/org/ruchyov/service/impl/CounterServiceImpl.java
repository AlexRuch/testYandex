package org.ruchyov.service.impl;


import org.ruchyov.service.CounterService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service("counterService")
public class CounterServiceImpl implements CounterService {

    private List<Long> counterDataList = new ArrayList<>();

    @Override
    public long lastMinuteCounter() {
        long minuteCounter = 0;
        long currentTime = new Date().getTime();
        for (Long time : counterDataList) {
            if (time > (currentTime - 60000)) {
                minuteCounter++;
            }
        }

        return minuteCounter;
    }

    @Override
    public long lastHourCounter() {
        long hourCounter = 0;
        long currentTime = new Date().getTime();
        for (Long time : counterDataList) {
            if (time > (currentTime - 3600000)) {
                hourCounter++;
            }
        }

        return hourCounter;
    }

    @Override
    public long lastDayCounter() {
        long dayCounter = 0;
        long currentTime = new Date().getTime();
        for (Long time : counterDataList) {
            if (time > (currentTime - 86400000)) {
                dayCounter++;
            }
        }

        return dayCounter;
    }

    @Override
    public void counterSavedData() {
        counterDataList.add(new Date().getTime());
    }
}
