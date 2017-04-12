package org.ruchyov.service.impl;

import org.ruchyov.service.CleaningScheduleService;
import org.springframework.stereotype.Service;

import java.util.*;

@Service("clearingScheduleService")
public class CleaningScheduleServiceImpl extends TimerTask implements CleaningScheduleService {


//        Следующие строчки нужно добавить в главный поток:

//        TimerTask task = new CleaningScheduleServiceImpl();
//        Timer timer = new Timer();
//        timer.schedule(task, 129600000);


    @Override
    public void run() {
        clearCollectionEveryThirtySixHours();
    }

    @Override
    public void clearCollectionEveryThirtySixHours() {

        long currentTime = new Date().getTime();

//      коллекция для хранения элементов содержищихся в CounterDataList в момент когда был вызван метод.
        List<Long> tempList = new ArrayList<>();
        tempList.addAll(CounterServiceImpl.CounterDataList);
        int index = 0;
//      поиск индекса последнего элемента который был добавлен не раньше чем 24 часа назад
        for (int i = 0; i < tempList.size(); i++) {
            if (tempList.get(i) > currentTime - 86400000) {
                index = i;
                break;
            }
        }
//      сохранаям в коллекцию только те элементы который были добавлены позже чем 24 часа назад
        tempList = tempList.subList(0, index);
        CounterServiceImpl.CounterDataList.removeAll(tempList);
    }
}
