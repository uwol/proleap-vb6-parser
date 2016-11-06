Private Function Function1()
    Function1 = 1
End Function

Private Function Function2(I, J)
    Function2 = 2
End Function

Private Sub TestSimpleCalls()
    Function1    
    Function2 1, 2

    Dim I

    I = Function1
    I = Function1()
    I = Function2(1, 2)
End Sub
