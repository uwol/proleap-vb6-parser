Dim CurrentColor As Integer
Const BLACK = 0, RED = 1, GREEN = 2, BLUE = 3

' Returns the current color of the pen as a string.
Property Get PenColor() As String
   Select Case CurrentColor
      Case RED
         PenColor = "Red"
      Case GREEN
         PenColor = "Green"
      Case BLUE
         PenColor = "Blue"
   End Select
End Property

' The following code gets the color of the pen 
' calling the Property Get procedure.
ColorName = PenColor