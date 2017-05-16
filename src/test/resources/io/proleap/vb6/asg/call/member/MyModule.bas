Public Type MyType
    MyFunction As String
    MySub As String
End Type

Public Sub Test()
    Dim MyTypeInstance As MyType
    
    MyTypeInstance.MyFunction = "MyModule" ' not detected as a call - fine
    MsgBox MyTypeInstance.MyFunction ' detected as a call - wrong
    
    MyTypeInstance.MySub = "MySub" ' not detected as a call - fine
    MsgBox MyTypeInstance.MySub ' detected as a call - wrong
End Sub