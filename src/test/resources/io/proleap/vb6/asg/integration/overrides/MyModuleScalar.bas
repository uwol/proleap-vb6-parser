Public CommonVariable As New MyClass

Public Type MyType
    MyFunction As String
    MySub As String
End Type

Public Enum MyEnum
    Play_Sound
    Play_Sound_Loop
    Close_Window
End Enum

Public GlobalMyClass As MyClass

Public Sub Test()
    Dim MyTypeInstance As MyType

    MyTypeInstance.MyFunction = "MyModule"
    MsgBox MyTypeInstance.MyFunction
    
    MyTypeInstance.MySub = "MySub"
    MsgBox MyTypeInstance.MySub
    
    With MyTypeInstance
        MsgBox .MyFunction
    End With
    
    For i = 0 To 0
        MsgBox CommonVariable.MyProp
        CommonVariable.MyProp = ""
    Next i
End Sub

