package org.ruchyov.service.impl;


import org.ruchyov.service.CounterService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

@Service("counterService")
class CounterServiceImpl implements CounterService {

    static List<Long> CounterDataList = new ArrayList<>();


    @Override
    public long lastMinuteCounter() {
        long minuteCounter = 0;
        long currentTime = new Date().getTime();

//        дополнительная коллекция введена для того, чтобы во время выполнения цикла for не возникло ошибки ConcurrentModificationException
        List<Long> tempList = new ArrayList<>();
        tempList.addAll(CounterDataList);

        for (long time : tempList) {

            if (time > currentTime) {
                break;
            } else if (time > (currentTime - 60000)) {
                minuteCounter++;
            }
        }
        return minuteCounter;
    }

    @Override
    public long lastHourCounter() {
        long hourCounter = 0;
        long currentTime = new Date().getTime();
        List<Long> tempList = new ArrayList<>();
        tempList.addAll(CounterDataList);

        for (long time : tempList) {

            if (time > currentTime) {
                break;
            } else if (time > (currentTime - 3600000)) {
                hourCounter++;
            }
        }
        return hourCounter;
    }

    @Override
    public long lastDayCounter() {
        long dayCounter = 0;
        long currentTime = new Date().getTime();
        List<Long> tempList = new ArrayList<>();
        tempList.addAll(CounterDataList);

        for (long time : tempList) {

            if (time > currentTime) {
                break;
            } else if (time > (currentTime - 86400000)) {
                dayCounter++;
            }
        }
        return dayCounter;
    }

    @Override
    public void counterSavedData() {
        CounterDataList.add(new Date().getTime());
    }
}
