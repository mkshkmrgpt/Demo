package main.parking

import main.entity.Vehicle

interface Parkable {
    fun create(capacity: Int): Message
    fun park(vehicle: Vehicle): Message
    fun makeSlotFree(slotNumber: Int): Message
    fun status(): Message
    fun parkingSlot(registrationNumber: String): Message
    fun parkedVehiclesByColor(color: String): Message
    fun parkedSpotsByColor(color: String): Message
}
