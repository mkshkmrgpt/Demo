package main.command

import main.Constants.VehicleColor
import main.entity.Vehicle
import main.parking.Message
import main.parking.ParkingLot
import kotlin.system.exitProcess

interface ParkingCommand {
    fun execute(value: Any?): Message
}

object CreateParkingLotCommand : ParkingCommand {
    override fun execute(value: Any?): Message {
        val list = value as List<*>
        val capacity = list[1] as String?
        return ParkingLot.create(Integer.parseInt(capacity))
    }
}

object ParkVehicleCommand : ParkingCommand {
    override fun execute(value: Any?): Message {
        val list = value as List<*>
        val vehicle = Vehicle(list[1] as String, VehicleColor.valueOf((list[2] as String).trim().toUpperCase()))
        return ParkingLot.park(vehicle)
    }
}

object LeaveParkingCommand : ParkingCommand {
    override fun execute(value: Any?): Message {
        val list = value as List<*>
        return ParkingLot.makeSlotFree(Integer.parseInt(list[1] as String))
    }
}

object ParkingStatusCommand : ParkingCommand {
    override fun execute(value: Any?): Message {
        return ParkingLot.status()
    }
}

object ParkedSpotByRegistrationCommand: ParkingCommand{
    override fun execute(value: Any?): Message {
        val list = value as List<*>
        return ParkingLot.parkingSlot(list[1] as String)
    }
}

object ParkedSpotsByColorCommand: ParkingCommand{
    override fun execute(value: Any?): Message {
        val list = value as List<*>
        return ParkingLot.parkedSpotsByColor(list[1] as String)
    }
}

object ParkedVehiclesByColorCommand: ParkingCommand{
    override fun execute(value: Any?): Message {
        val list = value as List<*>
        return ParkingLot.parkedVehiclesByColor(list[1] as String)
    }
}

object ExitCommand : ParkingCommand {
    override fun execute(value: Any?): Message {
        exitProcess(0)
    }
}