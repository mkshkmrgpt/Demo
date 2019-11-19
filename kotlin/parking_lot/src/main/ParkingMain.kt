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

/*
*
*
create_parking_lot 6
park KA-01-HH-1234 White
park KA-01-HH-9999 White
park KA-01-BB-0001 Black
park KA-01-HH-7777 Red

park KA-01-HH-2701 Blue
park KA-01-HH-3141 Black
leave 4
status
park KA-01-P-333 White
park DL-12-AA-9999 White
registration_numbers_for_cars_with_colour White
slot_numbers_for_cars_with_colour White
slot_number_for_registration_number KA-01-HH-3141
slot_number_for_registration_number MH-04-AY-1111
*
*
*
* */