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

If Application.OperatingSystem Like "*Macintosh*" Then
    Application.StandardFont = "Geneva"
Else
    Application.StandardFont = "Arial"
End If