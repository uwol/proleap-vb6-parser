Public Type MyType
    MyFunction As String
    MySub As String
End Type

Public Sub Test()
    Dim MyTypeInstance As MyType
    
    MyTypeInstance.MyFunction = "MyModule"
    MsgBox MyTypeInstance.MyFunction
    
    MyTypeInstance.MySub = "MySub"
    MsgBox MyTypeInstance.MySub
    
    With MyTypeInstance
        MsgBox .MyFunction
    End With

    Dim MyTypeArray(2) As MyType
    With MyTypeArray(0)
        MsgBox .MyFunction
    End With
End Sub