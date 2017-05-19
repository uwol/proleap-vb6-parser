Private Function SomeFunction(I, J)
    Dim K
    K = I + J
    SomeFunction = J
End Function

Private Sub SomeSub(I)
    Dim L
    L = I + 1
End Sub

SomeFunction 1, 2
SomeSub 1