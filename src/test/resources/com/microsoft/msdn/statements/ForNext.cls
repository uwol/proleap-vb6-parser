Worksheets("Sheet1").Activate
areaCount = Selection.Areas.Count
If areaCount <= 1 Then
    MsgBox "The selection contains " & _
        Selection.Columns.Count & " columns."
Else
    For i = 1 To areaCount
        MsgBox "Area " & i & " of the selection contains " & _
            Selection.Areas(i).Columns.Count & " columns."
    Next i
End If

Set newSheet = Sheets.Add(Type:=xlWorksheet)
For i = 1 To Sheets.Count
    newSheet.Cells(i, 1).Value = Sheets(i).Name
Next i

Dim items() As Boolean
Set lbox = Worksheets("Sheet1").ListBoxes(1)
ReDim items(1 To lbox.ListCount)
For i = 1 To lbox.ListCount
    If i Mod 2 = 1 Then
        items(i) = True
    Else
        items(i) = False
    End If
Next
lbox.MultiSelect = xlExtended
lbox.Selected = items