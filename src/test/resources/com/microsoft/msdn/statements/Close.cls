Dim I, FileName
For I = 1 To 3   ' Loop 3 times.
   FileName = "TEST" & I   ' Create file name.
   Open FileName For Output As #I   ' Open file.
   Print #I, "This is a test."   ' Write string to file.
Next I
Close   ' Close all 3 open files.