Private Sub MyFirstSub()
  On Local Error GoTo ErrHandler
    MsgBox "MyFirstSub"
    Exit Sub

  ErrHandler:
End Sub