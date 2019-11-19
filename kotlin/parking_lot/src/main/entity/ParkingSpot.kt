package main.entity

import main.Constants.ParkingSpotType

abstract class ParkingSpot(val number: Int, parkingSpotType: ParkingSpotType) {

    private var freeStatus = true
    lateinit var vehicle: Vehicle

    fun isFree(): Boolean {
        return freeStatus
    }

    fun assignVehicle(vehicle: Vehicle) {
        this.vehicle = vehicle
        freeStatus = false
    }

    fun removeVehicle() {
        this.freeStatus = true
    }
}

class CarSpot(number: Int) : ParkingSpot(number, ParkingSpotType.CAR)