For Each c in Worksheets("Sheet1").Range("A1:D10")
    If c.Value < .001 Then
        c.Value = 0
    End If
Next c

numBlanks = 0
For Each c In Range("TestRange")
    If c.Value = "" Then
        numBlanks = numBlanks + 1
    End If
Next c
MsgBox "There are " & numBlanks & " empty cells in this range."

For Each w In Workbooks
    If w.Name <> ThisWorkbook.Name Then
        w.Close savechanges:=True
    End If
Next w

Application.DisplayAlerts = False
For Each w In Worksheets
    w.Delete
Next w
Application.DisplayAlerts = True

Set newSheet = ActiveWorkbook.Worksheets.Add
i = 1
For Each nm In ActiveWorkbook.Names
    newSheet.Cells(i, 1).Value = nm.NameLocal
    newSheet.Cells(i, 2).Value = "'" & nm.RefersToLocal
    i = i + 1
Next nm