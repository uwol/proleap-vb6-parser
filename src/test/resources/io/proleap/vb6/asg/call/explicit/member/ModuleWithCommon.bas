Public Sub Common()
    DoEvents
End Sub

Public Sub Test()
    Dim ClassA As ClassA
    
    With ClassA
        Call .Common
    End With
End Sub