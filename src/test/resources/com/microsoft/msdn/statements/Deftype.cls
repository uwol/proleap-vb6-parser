' Variable names beginning with A through K default to Integer.
DefInt A-K
' Variable names beginning with L through Z default to String.
DefStr L-Z
CalcVar = 4   ' Initialize Integer.
StringVar = "Hello there"   ' Initialize String.
AnyVar = "Hello"    ' Causes "Type mismatch" error.
Dim Calc As Double   ' Explicitly set the type to Double.
Calc = 2.3455   ' Assign a Double.

' Deftype statements also apply to function procedures.
CalcNum = ATestFunction(4)   ' Call user-defined function.
' ATestFunction function procedure definition.
Function ATestFunction(INumber)
   ATestFunction = INumber * 2   ' Return value is an integer.
End Function