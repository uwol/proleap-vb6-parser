Option Explicit

Public Sub Test()
    Select Case 1
        Case Sound_Play, Sound_Play_Looping
            MsgBox "playing sound..."
        
        Case Sound_Stop
            MsgBox "stopping sound..."
    End Select
End Sub