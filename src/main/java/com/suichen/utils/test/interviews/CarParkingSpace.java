package com.suichen.utils.test.interviews;

public class CarParkingSpace extends AbstractParkingSpace {

    @Override
    public ParkingSpaceEnum getParkingSpaceEnum() {
        return ParkingSpaceEnum.CAR;
    }

    @Override
    public long calculateCost() {
        long cost = (car.getExitTime().getTime() - car.getEnterTime().getTime()) % nd / nh * 5;
        return cost > 60 ? 60 : cost;
    }
}