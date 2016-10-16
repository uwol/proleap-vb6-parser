Sub GotoStatementDemo()
Dim Number, MyString
   Number = 1    ' Initialize variable.
   ' Evaluate Number and branch to appropriate label.
   If Number = 1 Then GoTo Line1 Else GoTo Line2
   
Line1:
   MyString = "Number equals 1"
   GoTo LastLine   ' Go to LastLine.
Line2:
   ' The following statement never gets executed.
   MyString = "Number equals 2"
LastLine: 
   Debug.Print MyString   ' Print "Number equals 1" in
      ' the Immediate window.
End Sub