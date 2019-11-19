package main

import main.command.ParkingCommands.commands
import main.command.ParkingCommands.registerCommand
import main.command.*
import main.command.ParkingCommands

fun main() {
    ParkingCommands.init()
    registerCommand("slot_number_for_registration_number", ParkedSpotByRegistrationCommand)
    while (true) {
        val command = readLine()?.split(" ")?.toList()
        val parkingCommand: ParkingCommand? = commands[command?.get(0)]
        when {
            null != parkingCommand -> parkingCommand.execute(command).show()
            else -> println("Invalid command")
        }
    }
}