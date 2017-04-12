package org.ruchyov.service.impl;

import org.ruchyov.service.CounterService;
import org.ruchyov.service.SaveDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("saveDataService")
public class SaveDataServiceImpl implements SaveDataService {

    @Autowired
    CounterService counterService;

    @Override
    public void saveData() {
        counterService.counterSavedData();
    }
}
