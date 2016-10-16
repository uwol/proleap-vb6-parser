' Call a Sub procedure.
Call PrintToDebugWindow("Hello World")   
' The above statement causes control to be passed to the following
' Sub procedure.
Sub PrintToDebugWindow(AnyString)
   Debug.Print AnyString   ' Print to the Immediate window.
End Sub

' Call an intrinsic function. The return value of the function is
' discarded.
Call Shell(AppName, 1)   ' AppName contains the path of the 
      ' executable file.

' Call a Microsoft Windows DLL procedure. The Declare statement must be 
' Private in a Class Module, but not in a standard Module.
Private Declare Sub MessageBeep Lib "User" (ByVal N As Integer)
Sub CallMyDll()
   Call MessageBeep(0)   ' Call Windows DLL procedure.
   MessageBeep 0   ' Call again without Call keyword.
End Sub