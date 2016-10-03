Dim MyAppID, ReturnValue
AppActivate "Microsoft Word"   ' Activate Microsoft 
   ' Word.

' AppActivate can also use the return value of the Shell function.
MyAppID = Shell("C:\WORD\WINWORD.EXE", 1)   ' Run Microsoft Word.
AppActivate MyAppID   ' Activate Microsoft 
   ' Word.



' You can also use the return value of the Shell function.
ReturnValue = Shell("c:\EXCEL\EXCEL.EXE",1)   ' Run Microsoft Excel.
AppActivate ReturnValue   ' Activate Microsoft 
   ' Excel.