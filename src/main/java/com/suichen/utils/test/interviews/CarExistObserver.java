package com.suichen.utils.test.interviews;

import java.util.Date;

public class CarExistObserver implements Observer {

    Parklot parklot;

    @Override
    public void update(Car car) {
        car.setExitTime(new Date());
        car.setCost(car.getParkingSpace().calculateCost());
        parklot.getCarParkingSpaceMap().remove(car);
        parklot.getHistoryCars().add(car);
        if (car.getParkingSpace().getParkingSpaceEnum() == ParkingSpaceEnum.TRUCK) {
            parklot.getTruckParkingSpace().incrementAndGet();
        } else if (car.getParkingSpace().getParkingSpaceEnum() == ParkingSpaceEnum.CAR) {
            parklot.getCarParkingSpace().incrementAndGet();
        }
    }


    public CarExistObserver(Parklot parklot) {
        this.parklot = parklot;
    }
}