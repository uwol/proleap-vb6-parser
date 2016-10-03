' In Microsoft Windows (16-bit):
Declare Sub MessageBeep Lib "User" (ByVal N As Integer)
' Assume SomeBeep is an alias for the procedure name.
Declare Sub MessageBeep Lib "User" Alias "SomeBeep"(ByVal N As Integer)
' Use an ordinal in the Alias clause to call GetWinFlags.
Declare Function GetWinFlags Lib "Kernel" Alias "#132"() As Long

' In 32-bit Microsoft Windows systems, specify the library USER32.DLL,
' rather than USER.DLL. You can use conditional compilation to write
' code that can run on either Win32 or Win16.
#If Win32 Then
   Declare Sub MessageBeep Lib "User32" (ByVal N As Long)
#Else
   Declare Sub MessageBeep Lib "User" (ByVal N As Integer)
#End If