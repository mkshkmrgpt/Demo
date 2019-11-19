package entity

import main.Constants.VehicleColor
import main.entity.CarSpot
import main.entity.Vehicle
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

internal class CarSpotTest{

    @Test
    fun `should mark parking spot not free and assign vehicle to the spot`(){
        val carSpot = CarSpot(1)
        carSpot.assignVehicle(Vehicle("MH-EX-01-1234", VehicleColor.BLACK))
        assertEquals(false, carSpot.isFree())
        assertEquals("MH-EX-01-1234", carSpot.vehicle.registrationNumber)
    }

    @Test
    fun `should mark parking spot free when vehicle is removed`(){
        val carSpot = CarSpot(1)
        carSpot.assignVehicle(Vehicle("MH-EX-01-1234", VehicleColor.BLACK))
        carSpot.removeVehicle()
        assertEquals(true, carSpot.isFree())
    }
}