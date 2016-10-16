Type EmployeeRecord   ' Create user-defined type.
   ID As Integer   ' Define elements of data type.
   Name As String * 20
   Address As String * 30
   Phone As Long
   HireDate As Date
End Type
Sub CreateRecord()
   Dim MyRecord As EmployeeRecord   ' Declare variable.

   ' Assignment to EmployeeRecord variable must occur in a procedure.
   MyRecord.ID = 12003   ' Assign a value to an element.
End Sub