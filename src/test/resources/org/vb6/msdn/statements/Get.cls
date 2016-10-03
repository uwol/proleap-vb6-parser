Type Record   ' Define user-defined type.
   ID As Integer
   Name As String * 20
End Type

Dim MyRecord As Record, Position   ' Declare variables.
' Open sample file for random access.
Open "TESTFILE" For Random As #1 Len = Len(MyRecord)
' Read the sample file using the Get statement.
Position = 3   ' Define record number.
Get #1, Position, MyRecord   ' Read third record.
Close #1   ' Close file.