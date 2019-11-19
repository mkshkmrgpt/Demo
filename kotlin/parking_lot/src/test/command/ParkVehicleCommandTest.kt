package command

import main.command.ParkVehicleCommand
import main.parking.ParkedMessage
import main.parking.ParkingFullMessage
import main.parking.ParkingLot
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.TestInstance

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
internal class ParkVehicleCommandTest {

    @BeforeAll
    fun init(){
        ParkingLot.create(3)
    }

    @Test
    fun `should park vehicle at spot 1`() {
        val parkedMessage = ParkVehicleCommand.execute(listOf("park", "MH-01-EX-1234", "white")) as ParkedMessage
        assertEquals(1, parkedMessage.spotNumber)
    }

    @Test
    fun `should park vehicle at spot 2`() {
        val parkedMessage = ParkVehicleCommand.execute(listOf("park", "MH-01-EX-1234", "white")) as ParkedMessage
        assertEquals(2, parkedMessage.spotNumber)
    }

    @Test
    fun `should park vehicle at spot 3`() {
        val parkedMessage = ParkVehicleCommand.execute(listOf("park", "MH-01-EX-1234", "white")) as ParkedMessage
        assertEquals(3, parkedMessage.spotNumber)
    }
    @Test
    fun `should return parking full message`() {
        val parkingFullMessage = ParkVehicleCommand.execute(listOf("park", "MH-01-EX-1234", "white"))
        assertTrue(parkingFullMessage is ParkingFullMessage)
    }
}