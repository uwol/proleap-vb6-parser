Sub OnGosubGotoDemo()
Dim Number, MyString
   Number = 2   ' Initialize variable.
   ' Branch to Sub2.
   On Number GoSub Sub1, Sub2   ' Execution resumes here after 
      ' On...GoSub.
   On Number GoTo Line1, Line2   ' Branch to Line2.
   ' Execution does not resume here after On...GoTo.
   Exit Sub
Sub1:
   MyString = "In Sub1" : Return
Sub2:
   MyString = "In Sub2" : Return
Line1: 
   MyString = "In Line1"
Line2: 
   MyString = "In Line2"
End Sub