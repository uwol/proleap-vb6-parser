Option Explicit
Public Event UpdateTime(ByVal dblJump As Double)
Public Event ChangeText()

Public Sub TimerTask(ByVal Duration As Double)
    Dim dblStart As Double
    Dim dblSecond As Double
    Dim dblSoFar As Double
    dblStart = Timer
    dblSoFar = dblStart
    
    Do While Timer < dblStart + Duration
        If Timer - dblSoFar >= 1 Then
            dblSoFar = dblSoFar + 1
            RaiseEvent UpdateTime(Timer - dblStart)
        End If
    Loop
    
    RaiseEvent ChangeText
    
End Sub