Public Type MyType
    MyFunction As String
    MySub As String
End Type

Public Sub Test()
    Dim MyTypeArray(1) As MyType

    With MyTypeArray(0)
        MsgBox .MyFunction

        Select Case ""
            Case ""
                MsgBox .MyFunction
        End Select

        For i = 0 To 0
            MsgBox .MyFunction
        Next i

        While True
            MsgBox .MyFunction
        Wend
    End With
End Sub