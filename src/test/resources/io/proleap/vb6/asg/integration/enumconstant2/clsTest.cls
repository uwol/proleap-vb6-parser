Option Explicit

Public Sub Test()
    Select Case 1
        Case Sound_Play, Sound_Play_Looping
            MsgBox "playing sound..."
        
        Case Sound_Stop
            MsgBox "stopping sound..."
    End Select
    
    ' these 2 "cmdPlay" are being detected as clsSound cmdPlay public member
    Call ShowNumber(cmdPlay, "number") 
    Call MsgBox(cmdPlay)
End Sub