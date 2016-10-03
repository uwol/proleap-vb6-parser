Select Case Application.MailSystem
    Case Is = xlMAPI
        MsgBox "Mail system is Microsoft Mail"
    Case Is = xlPowerTalk
        MsgBox "Mail system is PowerTalk"
    Case Is = xlNoMailSystem
        MsgBox "No mail system installed"
End Select

Worksheets("Sheet1").Activate
Select Case ActiveCell.LocationInTable
Case Is = xlRowHeader
    MsgBox "Active cell is part of a row header"
Case Is = xlColumnHeader
    MsgBox "Active cell is part of a column header"
Case Is = xlPageHeader
    MsgBox "Active cell is part of a page header"
Case Is = xlDataHeader
    MsgBox "Active cell is part of a data header"
Case Is = xlRowItem
    MsgBox "Active cell is part of a row item"
Case Is = xlColumnItem
    MsgBox "Active cell is part of a column item"
Case Is = xlPageItem
    MsgBox "Active cell is part of a page item"
Case Is = xlDataItem
    MsgBox "Active cell is part of a data item"
Case Is = xlTableBody
    MsgBox "Active cell is part of the table body"
End Select

Worksheets("Sheet1").Activate
If IsError(ActiveCell.Value) Then
    errval = ActiveCell.Value
    Select Case errval
        Case CVErr(xlErrDiv0)
            MsgBox "#DIV/0! error"
        Case CVErr(xlErrNA)
            MsgBox "#N/A error"
        Case CVErr(xlErrName)
            MsgBox "#NAME? error"
        Case CVErr(xlErrNull)
            MsgBox "#NULL! error"
        Case CVErr(xlErrNum)
            MsgBox "#NUM! error"
        Case CVErr(xlErrRef)
            MsgBox "#REF! error"
        Case CVErr(xlErrValue)
            MsgBox "#VALUE! error"
        Case Else
            MsgBox "This should never happen!!"
    End Select
End If