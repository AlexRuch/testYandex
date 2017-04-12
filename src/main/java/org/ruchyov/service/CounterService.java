package org.ruchyov.service;

public interface CounterService {

    long lastMinuteCounter();

    long lastHourCounter();

    long lastDayCounter();

    void counterSavedData();
}
