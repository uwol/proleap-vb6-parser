' Sub procedure definition.
' Sub procedure with two arguments.
Sub SubComputeArea(Length, TheWidth)
   Dim Area As Double   ' Declare local variable. 
   If Length = 0 Or TheWidth = 0 Then
   ' If either argument = 0.
      Exit Sub   ' Exit Sub immediately.
   End If
   Area = Length * TheWidth   ' Calculate area of rectangle.
   Debug.Print Area   ' Print Area to Debug window.
End Sub