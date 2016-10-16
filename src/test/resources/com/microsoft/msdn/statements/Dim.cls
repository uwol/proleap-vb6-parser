' AnyValue and MyValue are declared as Variant by default with values
' set to Empty.
Dim AnyValue, MyValue

' Explicitly declare a variable of type Integer.
Dim Number As Integer

' Multiple declarations on a single line. AnotherVar is of type Variant
' because its type is omitted.
Dim AnotherVar, Choice As Boolean, BirthDate As Date

' DayArray is an array of Variants with 51 elements indexed, from
' 0 thru 50, assuming Option Base is set to 0 (default) for
' the current module.
Dim DayArray(50)

' Matrix is a two-dimensional array of integers.
Dim Matrix(3, 4) As Integer

' MyMatrix is a three-dimensional array of doubles with explicit
' bounds.
Dim MyMatrix(1 To 5,  4 To 9,  3 To 5) As Double

' BirthDay is an array of dates with indexes from 1 to 10.
Dim BirthDay(1 To 10) As Date      

' MyArray is a dynamic array of variants.
Dim MyArray()