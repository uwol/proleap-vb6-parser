Option Explicit

Private WithEvents mText As TimerState

Private Sub Command1_Click()
Text1.Text = "From Now"
    Text1.Refresh
    Text2.Text = "0"
    Text2.Refresh
Call mText.TimerTask(9.84)
End Sub

Private Sub Form_Load()
    Command1.Caption = "Click to Start Timer"
    Text1.Text = ""
    Text2.Text = ""
    Label1.Caption = "The fastest 100 meter run took this long:"
    Set mText = New TimerState
    End Sub

Private Sub mText_ChangeText()
    Text1.Text = "Until Now"
    Text2.Text = "9.84"
End Sub

Private Sub mText_UpdateTime(ByVal dblJump As Double)
    Text2.Text = Str(Format(dblJump, "0"))
    DoEvents
End Sub