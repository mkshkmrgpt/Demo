package command

import main.command.CreateParkingLotCommand
import main.parking.CreationMessage
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

internal class CreateParkingLotCommandTest {

    @Test
    fun `should return CreationMessage with capacity`() {
        val creationMessage = CreateParkingLotCommand.execute(listOf("create_parking_lot", "5")) as CreationMessage
        assertEquals(5, creationMessage.capacity)
    }
}