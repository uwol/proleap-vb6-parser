Public Type MyType
    MyFunction As String
    MySub As String
End Type

Public Sub Test()
    Dim MyTypeArray(1) As MyType

    With MyTypeArray(0)
        MsgBox .MyFunction
    End With
End Sub