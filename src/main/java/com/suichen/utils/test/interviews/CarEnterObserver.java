package com.suichen.utils.test.interviews;

import java.util.Date;

public class CarEnterObserver implements Observer {

    Parklot parklot;

    public CarEnterObserver(Parklot parklot) {
        this.parklot = parklot;
    }

    @Override
    public void update(Car car) {
        car.setId(parklot.getCarIdGenerator().incrementAndGet());
        car.setEnterTime(new Date());
        long last;
        if (car.getParkingSpace().getParkingSpaceEnum() == ParkingSpaceEnum.TRUCK) {
            last = parklot.getTruckParkingSpace().decrementAndGet();
        } else if (car.getParkingSpace().getParkingSpaceEnum() == ParkingSpaceEnum.CAR) {
            last = parklot.getCarParkingSpace().decrementAndGet();
        }

        parklot.getCarParkingSpaceMap().put(car, car.getParkingSpace());
    }
}