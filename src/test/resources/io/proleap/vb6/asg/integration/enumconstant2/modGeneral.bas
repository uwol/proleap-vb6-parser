Option Explicit

Public Enum eSoundAction
    Sound_Load
    Sound_Play
    Sound_Stop
    cmdPlay
End Enum

Public Sub Test()
	Call MsgBox(cmdPlay)
    Call ShowNumber(cmdPlay, "number")
End Sub

Public Sub ShowNumber(ByVal n As Byte, Optional ByVal title As String = "")
	MsgBox n, , title
End Sub