Type Record   ' Define user-defined type.
   ID As Integer
   Name As String * 20
End Type

Dim MyRecord As Record, RecordNumber   ' Declare variables.
' Open sample file for random access.
Open "TESTFILE" For Random Shared As #1 Len = Len(MyRecord)
RecordNumber = 4   ' Define record number.
Lock #1, RecordNumber   ' Lock record.
Get #1, RecordNumber, MyRecord   ' Read record.
MyRecord.ID = 234   ' Modify record.
MyRecord.Name = "John Smith"
Put #1, RecordNumber, MyRecord   ' Write modified record.
Unlock #1, RecordNumber   ' Unlock current record.
Close #1   ' Close file.