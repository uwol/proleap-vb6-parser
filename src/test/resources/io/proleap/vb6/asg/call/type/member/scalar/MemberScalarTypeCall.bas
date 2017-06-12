Public Type MyType
    MyFunction As String
    MySub As String
End Type

Public Sub Test()
    Dim MyTypeInstance As MyType

    MyTypeInstance.MyFunction = "MyModule"
    MsgBox MyTypeInstance.MyFunction
    
    With MyTypeInstance
        MsgBox .MyFunction
    End With
    
    MyTypeInstance.MySub = "MySub"
    MsgBox MyTypeInstance.MySub
End Sub