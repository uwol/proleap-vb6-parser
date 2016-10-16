Sub OnErrorStatementDemo()
   On Error GoTo ErrorHandler   ' Enable error-handling routine.
   Open "TESTFILE" For Output As #1   ' Open file for output.
   Kill "TESTFILE"   ' Attempt to delete open 
            ' file.
   On Error Goto 0   ' Turn off error trapping.
   On Error Resume Next   ' Defer error trapping.
   ObjectRef = GetObject("MyWord.Basic")   ' Try to start nonexistent
            ' object, then test for 
'Check for likely Automation errors.
   If Err.Number = 440 Or Err.Number = 432 Then
      ' Tell user what happened. Then clear the Err object.
      Msg = "There was an error attempting to open the Automation object!"
      MsgBox Msg, , "Deferred Error Test"
      Err.Clear   ' Clear Err object fields 
   End If   
Exit Sub      ' Exit to avoid handler.
ErrorHandler:   ' Error-handling routine.
   Select Case Err.Number   ' Evaluate error number.
      Case 55   ' "File already open" error.
         Close #1   ' Close open file.
      Case Else
         ' Handle other situations here... 
   End Select
   Resume   ' Resume execution at same line
            ' that caused the error.
End Sub