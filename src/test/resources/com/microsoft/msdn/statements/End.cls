Sub Form_Load
   Dim Password, Pword
   PassWord = "Swordfish"
   Pword = InputBox("Type in your password")
   If Pword <> PassWord Then
      MsgBox "Sorry, incorrect password"
      End
   End If
End Sub