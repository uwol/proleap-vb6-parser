Private Sub Form_Click ()
   Dim Answer, Msg   ' Declare variable.
   Unload Form1   ' Unload form.
   Msg = "Form1 has been unloaded. Choose Yes to load and "
   Msg = Msg & "display the form. Choose No to load the form "
   Msg = Msg & "and leave it invisible."
   Answer = MsgBox(Msg, vbYesNo)   ' Get user response.
   If Answer = vbYes Then   ' Evaluate answer.
      Show   ' If Yes, show form.
   Else
      Load Form1   ' If No, just load it.
      Msg = "Form1 is now loaded. Choose OK to display it."
      MsgBox Msg   ' Display message.
      Show   ' Show form.
   End If
End Sub