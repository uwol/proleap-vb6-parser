Type Record   ' Define user-defined type.
   ID As Integer
   Name As String * 20
End Type

Dim MyRecord As Record, MaxSize, RecordNumber   ' Declare variables.
' Open file in random-file mode.
Open "TESTFILE" For Random As #1 Len = Len(MyRecord)
MaxSize = LOF(1) \ Len(MyRecord)   ' Get number of records in file.
' The loop reads all records starting from the last.
For RecordNumber = MaxSize To 1 Step - 1
   Seek #1, RecordNumber   ' Set position.
   Get #1, , MyRecord   ' Read record.
Next RecordNumber
Close #1   ' Close file.

Dim MaxSize, NextChar, MyChar
Open "TESTFILE" For Input As #1   ' Open file for input.
MaxSize = LOF(1)   ' Get size of file in bytes.
' The loop reads all characters starting from the last.
For NextChar = MaxSize To 1 Step -1   
   Seek #1, NextChar   ' Set position.
   MyChar = Input(1, #1)   ' Read character.
Next NextChar
Close #1   ' Close file.