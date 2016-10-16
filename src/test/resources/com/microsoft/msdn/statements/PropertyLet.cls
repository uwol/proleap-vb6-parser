Dim CurrentColor As Integer
Const BLACK = 0, RED = 1, GREEN = 2, BLUE = 3

' Set the pen color property for a Drawing package.
' The module-level variable CurrentColor is set to
' a numeric value that identifies the color used for drawing.
Property Let PenColor(ColorName As String)
   Select Case ColorName   ' Check color name string.
      Case "Red"
         CurrentColor = RED   ' Assign value for Red.
      Case "Green"
         CurrentColor = GREEN   ' Assign value for Green.
      Case "Blue"
         CurrentColor = BLUE   ' Assign value for Blue.
      Case Else
         CurrentColor = BLACK   ' Assign default value.
   End Select
End Property

' The following code sets the PenColor property for a drawing package
' by calling the Property let procedure.

PenColor = "Red"