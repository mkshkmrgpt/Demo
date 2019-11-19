package command

import main.command.LeaveParkingCommand
import main.command.ParkVehicleCommand
import main.parking.LeaveParkingMessage
import main.parking.ParkedMessage
import main.parking.ParkingLot
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.TestInstance

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
internal class LeaveParkingCommandTest {

    @BeforeAll
    fun init(){
        ParkingLot.create(3)
        ParkVehicleCommand.execute(listOf("park", "MH-02-EX-1234", "white"))
        ParkVehicleCommand.execute(listOf("park", "MH-02-EX-1234", "white"))
        ParkVehicleCommand.execute(listOf("park", "MH-02-EX-1234", "white"))
    }

    @Test
    fun `should return LeavingParkingMessage `() {
        val leaveParkingCommand = LeaveParkingCommand.execute(listOf("leave", "2")) as LeaveParkingMessage
        assertEquals(2,leaveParkingCommand.slotFree)
    }

    @Test
    fun `should park new vehicle to last free slot 2`(){
        val parkedMessage = ParkVehicleCommand.execute(listOf("park", "MH-02-EX-1234", "white")) as ParkedMessage
        assertEquals(2, parkedMessage.spotNumber)
    }
}