Public MyArray() As TextBox

Public Sub MySub()
    ReDim MyArray(0 To 0) As TextBox
    MsgBox MyArray(0).Text
    MyArray(0).Text = ""
End Sub