package parking

import main.Constants.VehicleColor
import main.entity.Vehicle
import main.parking.*
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.MethodOrderer
import org.junit.jupiter.api.Order
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestMethodOrder
import kotlin.test.assertEquals
import kotlin.test.assertNull

@TestMethodOrder(value = MethodOrderer.OrderAnnotation::class)
internal class ParkingLotTest{
    @Test
    @Order(1)
    fun `create parking with provided capacity`() {
        val creationMessage = ParkingLot.create(6) as CreationMessage
        assert(creationMessage.capacity == 6)
    }

    @Test
    @Order(2)
    fun `should allow parking upto defined capacity`() {
        val parkedMessage1 = ParkingLot.park(Vehicle("KA-01-HH-1234", VehicleColor.WHITE)) as ParkedMessage
        val parkedMessage2 = ParkingLot.park(Vehicle("KA-01-HH-1235", VehicleColor.BLACK)) as ParkedMessage
        val parkedMessage3 = ParkingLot.park(Vehicle("KA-01-HH-1236", VehicleColor.GREEN)) as ParkedMessage
        val parkedMessage4 = ParkingLot.park(Vehicle("KA-01-HH-1237", VehicleColor.GREY)) as ParkedMessage
        val parkedMessage5 = ParkingLot.park(Vehicle("KA-01-HH-1238", VehicleColor.WHITE)) as ParkedMessage
        val parkedMessage6 = ParkingLot.park(Vehicle("KA-01-HH-1239", VehicleColor.BLUE)) as ParkedMessage
        assert(parkedMessage1.spotNumber == 1)
        assert(parkedMessage2.spotNumber == 2)
        assert(parkedMessage3.spotNumber == 3)
        assert(parkedMessage4.spotNumber == 4)
        assert(parkedMessage5.spotNumber == 5)
        assert(parkedMessage6.spotNumber == 6)
    }

    @Test
    @Order(3)
    fun `should return parking full message after parking is full`() {
        val parkedMessage7 = ParkingLot.park(Vehicle("KA-01-HH-1239", VehicleColor.BLUE)) as ParkingFullMessage
        assertTrue(parkedMessage7 is ParkingFullMessage)
    }

    @Test
    @Order(4)
    fun `should free up slot number 4`(){
        val leaveParkingMessage = ParkingLot.makeSlotFree(4) as LeaveParkingMessage
        assertTrue(leaveParkingMessage is LeaveParkingMessage)
        assertTrue(leaveParkingMessage.slotFree==4)
    }

    @Test
    @Order(5)
    fun `should generate status for parked vehicles`(){
        val parkingStatusMessage = ParkingLot.status()
        assertTrue(parkingStatusMessage.parkingSpots.size==5)
        val parkingSpot =  parkingStatusMessage.parkingSpots.find { parkingSpot -> parkingSpot.number==4 }
        assertNull(parkingSpot)
    }

    @Test
    @Order(6)
    fun `find parking slot by vehicle registration number`(){
        val parkingSlot = ParkingLot.parkingSlot("KA-01-HH-1235") as ParkingSpotMessage
        assertEquals(2,parkingSlot.parkingSpot?.number)
    }

    @Test
    @Order(7)
    fun `find all parked white colored vehicles`(){
        val parkedVehicleMessage= ParkingLot.parkedVehiclesByColor("white") as ParkedVehicleMessage
        assertEquals("KA-01-HH-1234", parkedVehicleMessage.vehicles[0].registrationNumber)
        assertEquals("KA-01-HH-1238", parkedVehicleMessage.vehicles[1].registrationNumber)
    }

    @Test
    @Order(8)
    fun `find all slots with white colored vechicles`(){
        val parkedVehicleMessage = ParkingLot.parkedSpotsByColor("black") as ParkedSpotsMessage
        assertEquals(2, parkedVehicleMessage.spots[0])
    }
}