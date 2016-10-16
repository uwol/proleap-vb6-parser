Set dataTableRange = Worksheets("Sheet1").Range("A1:K11")
Set rowInputCell = Worksheets("Sheet1").Range("A12")
Set columnInputCell = Worksheets("Sheet1").Range("A13")

Worksheets("Sheet1").Range("A1").Formula = "=A12*A13"
For i = 2 To 11
    Worksheets("Sheet1").Cells(i, 1) = i - 1
    Worksheets("Sheet1").Cells(1, i) = i - 1
Next i
dataTableRange.Table rowInputCell, columnInputCell
With Worksheets("Sheet1").Range("A1").CurrentRegion
    .Rows(1).Font.Bold = True
    .Columns(1).Font.Bold = True
    .Columns.AutoFit
End With