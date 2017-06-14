Option Explicit

Private myNode As clsListNode

Private Property Get Node() As clsListNode
	Set Node = myNode
End Property

Private Property Let Node(ByRef newNode As clsListNode)
	Set myNode = newNode
End Property

Public Sub SomeSub(ByRef data As Object)
	myNode.Data = data
End Sub

Public Function SomeFunction(ByRef node As clsListNode) As Boolean
	myNode.Prev.Prev = node.Prev
	myNode.Prev.Next = node.Next
End Function