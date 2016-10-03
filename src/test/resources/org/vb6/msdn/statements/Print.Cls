Open "TESTFILE" For Output As #1   ' Open file for output.
Print #1, "This is a test"   ' Print text to file.
Print #1,   ' Print blank line to file.
Print #1, "Zone 1"; Tab ; "Zone 2"   ' Print in two print zones.
Print #1, "Hello" ; " " ; "World"   ' Separate strings with space.
Print #1, Spc(5) ; "5 leading spaces "   ' Print five leading spaces.
Print #1, Tab(10) ; "Hello"   ' Print word at column 10.

' Assign Boolean, Date, Null and Error values.
Dim MyBool, MyDate, MyNull, MyError
MyBool = False : MyDate = #February 12, 1969# : MyNull = Null
MyError = CVErr(32767)
' True, False, Null, and Error are translated using locale settings of 
' your system. Date literals are written using standard short date 
' format.
Print #1, MyBool ; " is a Boolean value"
Print #1, MyDate ; " is a date"
Print #1, MyNull ; " is a null value"
Print #1, MyError ; " is an error value"
Close #1   ' Close file.