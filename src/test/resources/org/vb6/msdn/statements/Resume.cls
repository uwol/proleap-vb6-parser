Sub ResumeStatementDemo()
   On Error GoTo ErrorHandler   ' Enable error-handling routine.
   Open "TESTFILE" For Output As #1   ' Open file for output.
   Kill "TESTFILE"   ' Attempt to delete open file.
   Exit Sub   ' Exit Sub to avoid error handler.
ErrorHandler:   ' Error-handling routine.
   Select Case Err.Number   ' Evaluate error number.
      Case 55   ' "File already open" error.
         Close #1   ' Close open file.
      Case Else
         ' Handle other situations here.... 
   End Select
   Resume   ' Resume execution at same line
   ' that caused the error.
End Sub