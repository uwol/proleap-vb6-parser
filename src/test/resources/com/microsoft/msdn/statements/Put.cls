Type Record   ' Define user-defined type.
   ID As Integer
   Name As String * 20
End Type

Dim MyRecord As Record, RecordNumber   ' Declare variables.
' Open file for random access.
Open "TESTFILE" For Random As #1 Len = Len(MyRecord)
For RecordNumber = 1 To 5   ' Loop 5 times.
   MyRecord.ID = RecordNumber   ' Define ID.
   MyRecord.Name = "My Name" & RecordNumber   ' Create a string.
   Put #1, RecordNumber, MyRecord   ' Write record to file.
Next RecordNumber
Close #1   ' Close file.