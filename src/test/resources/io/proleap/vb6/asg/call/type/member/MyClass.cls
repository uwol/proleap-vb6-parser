Public Function MyFunction() As String
    MyFunction = "MyFunction"
End Function

Public Property Get MyPropertyGet() As String
    MyPropertyGet = "MyPropertyGet"
End Property

Public Sub MySub()
    MsgBox Me.MyFunction
    MsgBox Me.MyPropertyGet
End Sub