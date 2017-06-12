Option Explicit

Private Type tCustomType
    Number As Integer
    Word As String
    Something As MyClass
End Type

Private MyTypeScalar() As tCustomType
Private MyClassScalar() As MyClass

Private CurrentIndex As Integer

Public Sub TestType()
    If True Then Call MyTypeScalar(CurrentIndex).Something.Pause(1)
    
    Dim str As String

    With MyTypeScalar(CurrentIndex).Something
        str = .MyFunction
        .Loaded = True
        .Loaded = .Loaded
        Call .Pause(2)
        .Pause 1
    End With
    
    MyTypeScalar(CurrentIndex).Something.Initialize 1, True, "."

End Sub

Public Sub TestClass()
    If True Then Call MyClassScalar(CurrentIndex).Pause(1)
    
    Dim str As String

    With MyClassScalar(CurrentIndex)
        str = .MyFunction
        .Loaded = True
        .Loaded = .Loaded
        Call .Pause(2)
        .Pause 1
    End With
    
    MyClassScalar(CurrentIndex).Initialize 1, True, "."
End Sub