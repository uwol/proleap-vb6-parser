Private Sub SomeOtherSub(ByRef arrData() As Byte)
    MsgBox UBound(arrData())
	MsgBox UBound(arrData(1))
End Sub

Dim MyArray(0) As Byte
SomeOtherSub MyArray