package main.entity

import main.Constants.VehicleColor

class Vehicle(val registrationNumber: String, val color: VehicleColor) {

    private var parkingTicket: ParkingTicket? = null

    fun assign(parkingTicket: ParkingTicket) {
        this.parkingTicket = parkingTicket
    }
}
