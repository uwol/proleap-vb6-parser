Sub studentInfo(ByVal name As String, Optional ByVal age As Integer = 0, Optional ByVal birth As Date = #1/1/2000#)
  Debug.Print "Name = " & name & "; age = " & CStr(age) & "; birth date = " & CStr(birth)
End Sub

Call studentInfo(age:=19, birth:=#9/21/1981#, name:="Mary")