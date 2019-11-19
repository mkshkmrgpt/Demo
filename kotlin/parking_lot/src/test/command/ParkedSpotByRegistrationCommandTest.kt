package command

import main.command.ParkVehicleCommand
import main.command.ParkedSpotByRegistrationCommand
import main.entity.ParkingSpot
import main.parking.ParkedSpotsMessage
import main.parking.ParkedVehicleMessage
import main.parking.ParkingLot
import main.parking.ParkingSpotMessage
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.TestInstance

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
internal class ParkedSpotByRegistrationCommandTest {

    @BeforeAll
    fun init() {
        ParkingLot.create(3)
        ParkVehicleCommand.execute(listOf("park", "MH-02-EX-1234", "white"))
        ParkVehicleCommand.execute(listOf("park", "MH-02-EX-1235", "black"))
        ParkVehicleCommand.execute(listOf("park", "MH-02-EX-1236", "white"))
    }

    @Test
    fun `should return 2 vehicles`() {
        val parkedVehicleMessage = ParkedSpotByRegistrationCommand.execute(listOf("", "MH-02-EX-1236")) as ParkingSpotMessage
        assertEquals(3, parkedVehicleMessage.parkingSpot?.number)
    }

    @Test
    fun `should return null when vehicle with registration number is not found`() {
        val parkedVehicleMessage = ParkedSpotByRegistrationCommand.execute(listOf("", "MH-02-EX-1246")) as ParkingSpotMessage
        assertEquals(null, parkedVehicleMessage.parkingSpot)
    }
}