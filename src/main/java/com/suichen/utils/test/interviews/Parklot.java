package com.suichen.utils.test.interviews;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

public class Parklot {
    private long sum;
    private AtomicLong carIdGenerator;
    private List<Car> historyCars;
    private Map<Car, ParkingSpace> carParkingSpaceMap;
    private AtomicLong truckParkingSpace;
    private AtomicLong carParkingSpace;
    private List<Observer> listeners;

    public Parklot(long truckParkingSpace, long carParkingSpace) {
        this.truckParkingSpace = new AtomicLong(truckParkingSpace);
        this.carParkingSpace = new AtomicLong(carParkingSpace);
        this.sum = 0;
        this.carIdGenerator = new AtomicLong(0);
        this.historyCars = new ArrayList<>();
        this.listeners.add(new CarEnterObserver(this));
        this.listeners.add(new CarExistObserver(this));
        this.carParkingSpaceMap = new ConcurrentHashMap<>();
    }

    public long getSum() {
        return sum;
    }

    public void setSum(long sum) {
        this.sum = sum;
    }

    public AtomicLong getCarIdGenerator() {
        return carIdGenerator;
    }

    public void setCarIdGenerator(AtomicLong carIdGenerator) {
        this.carIdGenerator = carIdGenerator;
    }

    public List<Car> getHistoryCars() {
        return historyCars;
    }

    public void setHistoryCars(List<Car> historyCars) {
        this.historyCars = historyCars;
    }

    public Map<Car, ParkingSpace> getCarParkingSpaceMap() {
        return carParkingSpaceMap;
    }

    public void setCarParkingSpaceMap(Map<Car, ParkingSpace> carParkingSpaceMap) {
        this.carParkingSpaceMap = carParkingSpaceMap;
    }

    public AtomicLong getTruckParkingSpace() {
        return truckParkingSpace;
    }

    public void setTruckParkingSpace(AtomicLong truckParkingSpace) {
        this.truckParkingSpace = truckParkingSpace;
    }

    public AtomicLong getCarParkingSpace() {
        return carParkingSpace;
    }

    public void setCarParkingSpace(AtomicLong carParkingSpace) {
        this.carParkingSpace = carParkingSpace;
    }

    public List<Observer> getListeners() {
        return listeners;
    }

    public void setListeners(List<Observer> listeners) {
        this.listeners = listeners;
    }
}
