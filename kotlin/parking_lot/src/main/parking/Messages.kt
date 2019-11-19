package main.parking

import main.entity.ParkingSpot
import main.entity.Vehicle

interface Message {
    fun show()
}

class CreationMessage(val capacity: Int) : Message {
    override fun show() {
        println("Created a parking lot with $capacity slots")
    }
}

class ParkedMessage(val spotNumber: Int) : Message {
    override fun show() {
        println("Allocated slot number: $spotNumber")
    }
}

class ParkingFullMessage : Message {
    override fun show() {
        println("Sorry, parking lot is full")
    }
}

class LeaveParkingMessage(val slotFree: Int?) : Message {
    override fun show() {
        println("Slot number $slotFree is free")
    }
}

class ParkingStatusMessage(val parkingSpots: List<ParkingSpot>) : Message {
    override fun show() {
        println("Slot No.\t Registration No\tColor")
        parkingSpots.forEach { println("${it.number}\t\t\t${it.vehicle.registrationNumber}\t\t${it.vehicle.color}") }
    }
}

class ParkingSpotMessage(val parkingSpot: ParkingSpot?) : Message {
    override fun show() = when (parkingSpot) {
        null -> println("Not found")
        else -> println(parkingSpot.number)
    }
}

class ParkedVehicleMessage(val vehicles:List<Vehicle>):Message{
    override fun show() {
       vehicles.forEach { print("${it.registrationNumber}, ") }
        println()
    }
}

class ParkedSpotsMessage(val spots:List<Int>):Message{
    override fun show() {
        when (spots) {
            null -> println("Not found")
        }
        spots.forEach { print("${it}, ") }
    }
}


