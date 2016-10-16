Dim I
Open "TESTFILE" For Output As #1   ' Open file for output.
VBA.Width 1, 5   ' Set output line width to 5.
For I = 0 To 9   ' Loop 10 times.
   Print #1, Chr(48 + I);   ' Prints five characters per line.
Next I
Close #1   ' Close file.