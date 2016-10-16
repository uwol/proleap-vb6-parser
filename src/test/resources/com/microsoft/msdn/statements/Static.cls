Static loanAmt
Static loanInt
Static loanTerm
loanAmt = Application.InputBox _
    (Prompt:="Loan amount (100,000 for example)", _
        Default:=loanAmt, Type:=1)
loanInt = Application.InputBox _
    (Prompt:="Annual interest rate (8.75 for example)", _
        Default:=loanInt, Type:=1)
loanTerm = Application.InputBox _
    (Prompt:="Term in years (30 for example)", _
        Default:=loanTerm, Type:=1)
payment = Application.Pmt(loanInt / 1200, loanTerm * 12, loanAmt)
MsgBox "Monthly payment is " & Format(payment, "Currency")