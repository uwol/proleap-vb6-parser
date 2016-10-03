' Declare array variables.
Dim NumArray(10) As Integer   ' Integer array.
Dim StrVarArray(10) As String   ' Variable-string array.
Dim StrFixArray(10) As String * 10   ' Fixed-string array.
Dim VarArray(10) As Variant   ' Variant array.
Dim DynamicArray() As Integer   ' Dynamic array.
ReDim DynamicArray(10)   ' Allocate storage space.
Erase NumArray   ' Each element set to 0.
Erase StrVarArray   ' Each element set to zero-length 
   ' string ("").   
Erase StrFixArray   ' Each element set to 0.
Erase VarArray   ' Each element set to Empty.
Erase DynamicArray   ' Free memory used by array.