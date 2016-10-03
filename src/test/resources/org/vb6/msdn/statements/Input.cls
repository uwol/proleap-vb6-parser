Dim MyString, MyNumber
Open "TESTFILE" For Input As #1   ' Open file for input.
Do While Not EOF(1)   ' Loop until end of file.
   Input #1, MyString, MyNumber   ' Read data into two variables.
   Debug.Print MyString, MyNumber   ' Print data to the Immediate window.
Loop
Close #1   ' Close file.