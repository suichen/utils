package com.suichen.utils.test.interviews;

public class TruckParkingSpace extends AbstractParkingSpace {

    @Override
    public ParkingSpaceEnum getParkingSpaceEnum() {
        return ParkingSpaceEnum.TRUCK;
    }

    @Override
    public long calculateCost() {
        long cost = (car.getExitTime().getTime() - car.getEnterTime().getTime()) % nd / nh * 10;
        return cost > 120 ? 120 : cost;
    }
}
