Dim MyString
MyString = "The dog jumps"   ' Initialize string.
Mid(MyString, 5, 3) = "fox"   ' MyString = "The fox jumps".
Mid(MyString, 5) = "cow"   ' MyString = "The cow jumps".
Mid(MyString, 5) = "cow jumped over"   ' MyString = "The cow jumpe".
Mid(MyString, 5, 3) = "duck"   ' MyString = "The duc jumpe".