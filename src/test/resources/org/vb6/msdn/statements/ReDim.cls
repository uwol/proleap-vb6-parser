Dim MyArray() As Integer   ' Declare dynamic array.
Redim MyArray(5)   ' Allocate 5 elements.
For I = 1 To 5   ' Loop 5 times.
   MyArray(I) = I   ' Initialize array.
Next I

Redim MyArray(10)   ' Resize to 10 elements.
For I = 1 To 10   ' Loop 10 times.
   MyArray(I) = I   ' Initialize array.
Next I

Redim Preserve MyArray(15)   ' Resize to 15 elements.