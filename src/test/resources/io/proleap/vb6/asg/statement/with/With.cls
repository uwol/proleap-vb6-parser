Public Type MyType
    MyProperty As String
End Type

Dim SomeObject As MyType

With SomeObject
	.MyProperty = "SomeValue"
End With