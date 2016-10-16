Dim TextLine
Open "TESTFILE" For Input As #1   ' Open file.
Do While Not EOF(1)   ' Loop until end of file.
   Line Input #1, TextLine   ' Read line into variable.
   Debug.Print TextLine   ' Print to the Immediate window.
Loop
Close #1   ' Close file.