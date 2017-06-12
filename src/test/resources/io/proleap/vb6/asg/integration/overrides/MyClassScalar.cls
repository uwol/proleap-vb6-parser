Option Explicit

Private Type tCustomType
    Number As Integer
    Word As String
    Something As MyClass
End Type

Private MyTypeScalar As tCustomType
Private MyClassScalar As MyClass

Public Sub TestType()
    If True Then Call MyTypeScalar.Something.Pause(1)
    
    Dim str As String

    With MyTypeScalar.Something
        str = .MyFunction
        .Loaded = True
        .Loaded = .Loaded
        Call .Pause(2)
        .Pause 1
    End With
    
    MyTypeScalar.Something.Initialize 1, True, "."
End Sub

Public Sub TestClass()
    If True Then Call MyClassScalar.Pause(1)
    
    Dim str As String

    With MyClassScalar
        str = .MyFunction
        .Loaded = True
        .Loaded = .Loaded
        Call .Pause(2)
        .Pause 1
    End With
    
    MyClassScalar.Initialize 1, True, "."
End Sub

Public Sub TestGlobal()
    Select Case 1
        Case Close_Window, 7
            If True Then Call GlobalMyClass.Close_Window
        
        Case Play_Sound, Play_Sound_Loop
            Call GlobalMyClass.Play_Sound(3)
    End Select
End Sub