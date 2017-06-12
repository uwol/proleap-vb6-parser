Option Explicit

Private CommonVariable(1) As String

Private mPaused As Long
Private mLoaded As Boolean

Public Close_Window As Boolean

Public Function MyFunction() As String
    MyFunction = "MyFunction"
End Function

Public Property Get MyPropertyGet() As String
    MyPropertyGet = "MyPropertyGet"
End Property

Public Sub MySub()
    MsgBox Me.MyFunction
    MsgBox Me.MyPropertyGet
End Sub

Public Sub Pause(ByVal lng As Long)
    mPaused = True
End Sub

Public Property Get Loaded() As Boolean
    Loaded = mLoaded
End Property

Public Property Let Loaded(ByVal b As Boolean)
    mLoaded = b
End Property

Public Function Initialize(ByVal a As Long, ByVal b As Boolean, Optional ByVal c As String, Optional ByVal d As String) As Boolean
    Initialize = True
End Function

Public Function Play_Sound(ByVal a As Byte) As Boolean
    Play_Sound = True
End Function
