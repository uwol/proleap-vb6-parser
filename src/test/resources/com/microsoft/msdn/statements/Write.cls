Open "TESTFILE" For Output As #1   ' Open file for output.
Write #1, "Hello World", 234   ' Write comma-delimited data.
Write #1,   ' Write blank line.

Dim MyBool, MyDate, MyNull, MyError
' Assign Boolean, Date, Null, and Error values.
MyBool = False : MyDate = #February 12, 1969# : MyNull = Null
MyError = CVErr(32767)
' Boolean data is written as #TRUE# or #FALSE#. Date literals are 
' written in universal date format, for example, #1994-07-13# 
 'represents July 13, 1994. Null data is written as #NULL#. 
' Error data is written as #ERROR errorcode#.
Write #1, MyBool ; " is a Boolean value"
Write #1, MyDate ; " is a date"
Write #1, MyNull ; " is a null value"
Write #1, MyError ; " is an error value"
Close #1   ' Close file.