package main.parking

import main.Constants.ParkingStatus
import main.Constants.VehicleColor
import main.entity.CarSpot
import main.entity.ParkingSpot
import main.entity.ParkingTicket
import main.entity.Vehicle
import java.util.*

object ParkingLot: Parkable {

    private lateinit var slots: LinkedList<ParkingSpot>

    override fun create(capacity: Int) : Message {
        slots = LinkedList()
        for (spot in 1..capacity) {
            slots.add(CarSpot(spot))
        }
        return CreationMessage(capacity)
    }

    override fun park(vehicle: Vehicle): Message {
        if (isFull()) return ParkingFullMessage()
        return ParkedMessage(firstFreeSpot(vehicle).number)

    }

    override fun makeSlotFree(slotNumber: Int): LeaveParkingMessage {
        val slot = slots.find { parkedSlots -> parkedSlots.number == slotNumber }
        slot?.removeVehicle()
        return LeaveParkingMessage(slotNumber)
    }

    override fun status(): ParkingStatusMessage {
        return ParkingStatusMessage(slots.filter { parkingSpot -> !parkingSpot.isFree() }.toList())
    }

    override fun parkingSlot(registrationNumber: String): Message {
        return ParkingSpotMessage(slots.firstOrNull { parkingSpot -> parkingSpot.vehicle.registrationNumber == registrationNumber && !parkingSpot.isFree() })
    }

    override fun parkedVehiclesByColor(color: String): Message {
       return ParkedVehicleMessage(slots
           .filter { parkingSpot -> parkingSpot.vehicle.color == VehicleColor.valueOf(color.toUpperCase()) }
           .map { it.vehicle })
    }

    override fun parkedSpotsByColor(color: String): Message {
        return ParkedSpotsMessage(slots
            .filter { parkingSpot -> parkingSpot.vehicle.color==VehicleColor.valueOf(color.toUpperCase()) }
            .map { it.number })
    }

    private fun firstFreeSpot(vehicle: Vehicle): ParkingSpot {
        val parkedSpot = slots.first { parkingSpot -> parkingSpot.isFree() }
        parkedSpot.assignVehicle(vehicle)
        return parkedSpot
    }

    private fun isFull(): Boolean {
        return slots.none { parkingSpot -> parkingSpot.isFree() }
    }

    private fun parkingTicket(vehicle: Vehicle): ParkingTicket {
        val parkingTicket = ParkingTicket()
        vehicle.assign(parkingTicket)
        parkingTicket.parkingStatus = ParkingStatus.ACTIVE
        return parkingTicket
    }
}