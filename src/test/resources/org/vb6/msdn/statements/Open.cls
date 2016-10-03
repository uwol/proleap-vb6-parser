Open "TESTFILE" For Input As #1
' Close before reopening in another mode.
Close #1

Open "TESTFILE" For Binary Access Write As #1
' Close before reopening in another mode.
Close #1

Type Record   ' Define user-defined type.
   ID As Integer
   Name As String * 20
End Type

Dim MyRecord As Record   ' Declare variable.
Open "TESTFILE" For Random As #1 Len = Len(MyRecord)
' Close before reopening in another mode.
Close #1

Open "TESTFILE" For Output Shared As #1
' Close before reopening in another mode.
Close #1

Open "TESTFILE" For Binary Access Read Lock Read As #1