package command

import main.command.ParkVehicleCommand
import main.command.ParkedSpotsByColorCommand
import main.parking.ParkedSpotsMessage
import main.parking.ParkingLot
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.TestInstance

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
internal class ParkedSpotsByColorCommandTest {

    @BeforeAll
    fun init(){
        ParkingLot.create(3)
        ParkVehicleCommand.execute(listOf("park", "MH-02-EX-1234", "white"))
        ParkVehicleCommand.execute(listOf("park", "MH-02-EX-1234", "black"))
        ParkVehicleCommand.execute(listOf("park", "MH-02-EX-1234", "white"))
    }

    @Test
    fun `should return 2 parking spots which has white colored vehicle parked`() {
        val parkedSpotsMessage = ParkedSpotsByColorCommand.execute(listOf("", "white")) as ParkedSpotsMessage
        assertEquals(2, parkedSpotsMessage.spots.size)
    }

    @Test
    fun `should return 1 parking spots which has white colored vehicle parked`() {
        val parkedSpotsMessage = ParkedSpotsByColorCommand.execute(listOf("", "black")) as ParkedSpotsMessage
        assertEquals(1, parkedSpotsMessage.spots.size)
    }
}