Option Explicit

Public cmdPlay As VB.CommandButton

Private Sub List_ItemClick(ByVal item As Object)
    cmdPlay.Enabled = True
    Set cmdPlay = Nothing
End Sub