Public Sub Test()
    Dim SomeUndefinedTypeVar As SomeUndefinedType
    
    SomeUndefinedTypeVar.Cls
    MsgBox SomeUndefinedTypeVar.Width
    
    SomeUndefinedTypeVar.Member(0).Cls
    MsgBox SomeUndefinedTypeVar.Member(0).Width
    
    MsgBox frmInventory.picLogo.Width
    MsgBox frmInventory.picProduct(2).Width
    MsgBox frmInventory.Width
End Sub