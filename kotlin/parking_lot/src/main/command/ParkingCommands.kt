package main.command

object ParkingCommands {
    val commands = HashMap<String, ParkingCommand>()
    fun init() {
        commands["create_parking_lot"] = CreateParkingLotCommand
        commands["park"] = ParkVehicleCommand
        commands["status"] = ParkingStatusCommand
        commands["leave"] = LeaveParkingCommand
        commands["slot_numbers_for_cars_with_colour"] = ParkedSpotsByColorCommand
        commands["registration_numbers_for_cars_with_colour"] = ParkedVehiclesByColorCommand
        commands["slot_number_for_registration_number"] = ParkedSpotByRegistrationCommand
        commands["exit"] = ExitCommand
    }

    fun registerCommand(command: String, impl: ParkingCommand) {
        commands[command] = impl
    }
}