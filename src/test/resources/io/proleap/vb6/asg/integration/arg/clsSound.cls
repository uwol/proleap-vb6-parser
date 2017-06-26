Option Explicit

Private Sub List_ItemClick(ByVal cmdPlay As Object)
	Dim objectVariable As Object
	Set objectVariable = cmdPlay

    Set cmdPlay = Nothing
End Sub