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
        for (int i = 0; i < counterDataList.size(); i++) {
            if (counterDataList.get(i + 1) > currentTime) {
                break;
            } else if (counterDataList.get(i) > (currentTime - 60000) && counterDataList.get(i) < currentTime) {
                minuteCounter++;
            }
        }

        return minuteCounter;
    }

    @Override
    public long lastHourCounter() {
        long hourCounter = 0;
        long currentTime = new Date().getTime();
        for (int i = 0; i < counterDataList.size(); i++) {
            if (counterDataList.get(i + 1) > currentTime) {
                break;
            } else if (counterDataList.get(i) > (currentTime - 3600000) && counterDataList.get(i) < currentTime) {
                hourCounter++;
            }
        }

        return hourCounter;
    }

    @Override
    public long lastDayCounter() {
        long dayCounter = 0;
        long currentTime = new Date().getTime();
        for (int i = 0; i < counterDataList.size(); i++) {
            if (counterDataList.get(i + 1) > currentTime) {
                break;
            } else if (counterDataList.get(i) > (currentTime - 86400000) && counterDataList.get(i) < currentTime) {
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
