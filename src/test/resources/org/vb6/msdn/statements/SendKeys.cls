Dim ReturnValue, I
ReturnValue = Shell("CALC.EXE", 1)   ' Run Calculator.
AppActivate ReturnValue    ' Activate the Calculator.
For I = 1 To 100   ' Set up counting loop.
   SendKeys I & "{+}", True   ' Send keystrokes to Calculator
Next I   ' to add each value of I.
SendKeys "=", True   ' Get grand total.
SendKeys "%{F4}", True   ' Send ALT+F4 to close Calculator.