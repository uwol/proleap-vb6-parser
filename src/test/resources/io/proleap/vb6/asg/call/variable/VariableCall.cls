Private Type SomeComplexType
	Variable1 As Integer
	Variable2 As String
End Type


Dim I As Integer
Dim J

J = I

Dim typeElement As SomeComplexType
typeElement.Variable1 = 1

Dim typeArray(1 To 10) As SomeComplexType
typeArray(1).Variable2 = "hello world"