Set newSheet = Worksheets.Add
newSheet.Name = "1995 Budget"

Set newSheet = ActiveWorkbook.Worksheets.Add
i = 1
For Each nm In ActiveWorkbook.Names
    newSheet.Cells(i, 1).Value = nm.NameLocal
    newSheet.Cells(i, 2).Value = "'" & nm.RefersToLocal
    i = i + 1
Next