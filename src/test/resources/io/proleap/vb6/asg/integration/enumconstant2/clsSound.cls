Option Explicit

Public cmdPlay As VB.CommandButton

Public Function Sound_Play(ByVal Sound As Integer) As Boolean
    If (Sound <= 0) Then Exit Function
    Sound_Play = True
End Function

Public Function Sound_Stop(ByVal Sound As Integer) As Boolean
    If (Sound <= 0) Then Exit Function
    Sound_Stop = True
End Function