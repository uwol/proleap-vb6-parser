Worksheets("Sheet1").Range("A1").Sort _
    key1:=Worksheets("Sheet1").Range("A1")
Set currentCell = Worksheets("Sheet1").Range("A1")
Do While Not IsEmpty(currentCell)
    Set nextCell = currentCell.Offset(1, 0)
    If nextCell.Value = currentCell.Value Then
        currentCell.EntireRow.Delete
    End If
    Set currentCell = nextCell
Loop