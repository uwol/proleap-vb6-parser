Sub ExitStatementDemo()
Dim I, MyNum
   Do            ' Set up infinite loop.
      For I = 1 To 1000   ' Loop 1000 times.
         MyNum = Int(Rnd * 1000)   ' Generate random numbers.
         Select Case MyNum   ' Evaluate random number.
            Case 7: Exit For   ' If 7, exit For...Next.
            Case 29: Exit Do   ' If 29, exit Do...Loop.
            Case 54: Exit Sub   ' If 54, exit Sub procedure.
         End Select
      Next I
   Loop
End Sub