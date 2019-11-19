package command

import main.command.ParkVehicleCommand
import main.command.ParkedVehiclesByColorCommand
import main.parking.ParkedVehicleMessage
import main.parking.ParkingLot
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.TestInstance

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
internal class ParkedVehiclesByColorCommandTest {

    @BeforeAll
    fun init(){
        ParkingLot.create(3)
        ParkVehicleCommand.execute(listOf("park", "MH-02-EX-1234", "white"))
        ParkVehicleCommand.execute(listOf("park", "MH-02-EX-1235", "black"))
        ParkVehicleCommand.execute(listOf("park", "MH-02-EX-1236", "white"))
    }

    @Test
    fun `should return 2 vehicles`() {
        val parkedVehicleMessage = ParkedVehiclesByColorCommand.execute(listOf("", "white")) as ParkedVehicleMessage
        assertEquals(2, parkedVehicleMessage.vehicles.size)
        assertEquals("MH-02-EX-1234", parkedVehicleMessage.vehicles[0].registrationNumber)
        assertEquals("MH-02-EX-1236", parkedVehicleMessage.vehicles[1].registrationNumber)

    }
}