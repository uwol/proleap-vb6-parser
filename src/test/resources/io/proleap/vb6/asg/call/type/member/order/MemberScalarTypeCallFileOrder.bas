Private Type CustomType
    Something As MyClass
End Type

Private MyTypeScalar As CustomType
Private MyClassScalar As MyClass

Public Sub TestType()
    Dim str As String

    With MyTypeScalar.Something
        str = .MyFunction
    End With
End Sub

Public Sub TestClass()
    Dim str As String

    With MyClassScalar
        str = .MyFunction
    End With    
End Sub