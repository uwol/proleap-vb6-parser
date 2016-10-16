' The following user-defined function returns the square root of the
' argument passed to it.
Function CalculateSquareRoot(NumberArg As Double) As Double
   If NumberArg < 0 Then   ' Evaluate argument.
      Exit Function   ' Exit to calling procedure.
   Else
      CalculateSquareRoot = Sqr(NumberArg)   ' Return square root.
   End If
End Function

Function CalcSum(ByVal FirstArg As Integer, ParamArray OtherArgs())
Dim ReturnValue 
' If the function is invoked as follows:
ReturnValue = CalcSum(4, 3 ,2 ,1)
' Local variables are assigned the following values: FirstArg = 4,
' OtherArgs(1) = 3, OtherArgs(2) = 2, and so on, assuming default
' lower bound for arrays = 1.
End Function