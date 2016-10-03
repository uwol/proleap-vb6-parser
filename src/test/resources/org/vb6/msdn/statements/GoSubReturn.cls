Sub GosubDemo()
Dim Num
' Solicit a number from the user.
   Num = InputBox("Enter a positive number to be divided by 2.")
' Only use routine if user enters a positive number.
   If Num > 0 Then GoSub MyRoutine   
   Debug.Print Num
   Exit Sub   ' Use Exit to prevent an error.
MyRoutine:
   Num = Num/2   ' Perform the division.
   Return   ' Return control to statement.
End Sub   ' following the GoSub statement.