#Sample commands

##Test inputs
create_parking_lot 6<br>
park KA-01-HH-1234 White<br>
park KA-01-HH-9999 White<br>
park KA-01-BB-0001 Black<br>
park KA-01-HH-7777 Red<br>

park KA-01-HH-2701 Blue<br>
park KA-01-HH-3141 Black<br>
leave 4<br>
status<br>
park KA-01-P-333 White<br>
park DL-12-AA-9999 White<br>
registration_numbers_for_cars_with_colour White<br>
slot_numbers_for_cars_with_colour White<br>
slot_number_for_registration_number KA-01-HH-3141<br>
slot_number_for_registration_number MH-04-AY-1111<br>


##Test Output
Created a parking lot with 6 slots<br>
Allocated slot number: 1<br>
Allocated slot number: 2<br>
Allocated slot number: 3<br>
Allocated slot number: 4<br>
Allocated slot number: 5<br>
Allocated slot number: 6<br>
Slot number 4 is free<br>
Slot No. Registration No Color<br>
1 KA-01-HH-1234 White<br>
2 KA-01-HH-9999 White<br>
3 KA-01-BB-0001 Black<br>
5 KA-01-HH-2701 Blue<br>
6 KA-01-HH-3141 Black<br>
Allocated slot number: 4<br>
Sorry, parking lot is full<br>
KA-01-HH-1234, KA-01-HH-9999, KA-01-P-333<br>
1, 2, 4<br>
6<br>
Not found<br>