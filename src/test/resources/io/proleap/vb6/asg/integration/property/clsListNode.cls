Option Explicit

Private myData As Object
Private myPrev As Object
Private myNext As Object

Public Property Get Data() As Object
    Set Data = myData
End Property

Public Property Let Data(ByRef newData As Object)
    Set myData = newData
End Property

Public Property Get Prev() As clsListNode
    Set Prev = myPrev
End Property

Public Property Let Prev(ByRef newPrev As clsListNode)
    Set myPrev = newPrev
End Property

Public Property Get Next() As clsListNode
    Set Next = myNext
End Property

Public Property Let Next(ByRef newNext As clsListNode)
    Set myNext = newNext
End Property