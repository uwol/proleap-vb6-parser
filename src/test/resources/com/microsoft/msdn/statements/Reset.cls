Dim FileNumber
For FileNumber = 1 To 5   ' Loop 5 times.
   ' Open file for output. FileNumber is concatenated into the string 
   ' TEST for the file name, but is a number following a #.
   Open "TEST" & FileNumber For Output As #FileNumber
   Write #FileNumber, "Hello World"   ' Write data to file.
Next FileNumber
Reset   ' Close files and write contents 
      ' to disk.