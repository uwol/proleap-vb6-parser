Option base 1   ' Set default array subscripts to 1.

Dim Lower
Dim MyArray(20), TwoDArray(3, 4)   ' Declare array variables.
Dim ZeroArray(0 To 5)   ' Override default base subscript.
' Use LBound function to test lower bounds of arrays.
Lower = LBound(MyArray)   ' Returns 1.
Lower = LBound(TwoDArray, 2)   ' Returns 1.
Lower = LBound(ZeroArray)   ' Returns 0.