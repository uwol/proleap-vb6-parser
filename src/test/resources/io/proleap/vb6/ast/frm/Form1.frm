VERSION 5.00
Object = "{BDC217C8-ED16-11CD-956C-0000C04E4C0A}#1.1#0"; "TABCTL32.OCX"
Begin VB.Form Form1 
   Caption         =   "Form1"
   ClientHeight    =   4170
   ClientLeft      =   60
   ClientTop       =   420
   ClientWidth     =   5580
   LinkTopic       =   "Form1"
   ScaleHeight     =   4170
   ScaleWidth      =   5580
   StartUpPosition =   3  'Windows Default
   Begin VB.CommandButton Command5 
      Caption         =   "Command5"
      Height          =   375
      Left            =   2400
      TabIndex        =   8
      Top             =   3600
      Width           =   1455
   End
   Begin VB.CommandButton Command4 
      Caption         =   "Command4"
      Height          =   375
      Left            =   240
      TabIndex        =   7
      Top             =   3600
      Width           =   1575
   End
   Begin TabDlg.SSTab SSTab1 
      Height          =   2655
      Left            =   240
      TabIndex        =   0
      Top             =   240
      Width           =   4095
      _ExtentX        =   7223
      _ExtentY        =   4683
      _Version        =   393216
      TabHeight       =   520
      TabCaption(0)   =   "Tab 0"
      TabPicture(0)   =   "Form1.frx":0000
      Tab(0).ControlEnabled=   -1  'True
      Tab(0).Control(0)=   "Text1"
      Tab(0).Control(0).Enabled=   0   'False
      Tab(0).Control(1)=   "Command1"
      Tab(0).Control(1).Enabled=   0   'False
      Tab(0).ControlCount=   2
      TabCaption(1)   =   "Tab 1"
      TabPicture(1)   =   "Form1.frx":001C
      Tab(1).ControlEnabled=   0   'False
      Tab(1).Control(0)=   "Text2"
      Tab(1).Control(0).Enabled=   0   'False
      Tab(1).Control(1)=   "Command2"
      Tab(1).Control(1).Enabled=   0   'False
      Tab(1).ControlCount=   2
      TabCaption(2)   =   "Tab 2"
      TabPicture(2)   =   "Form1.frx":0038
      Tab(2).ControlEnabled=   0   'False
      Tab(2).Control(0)=   "Text3"
      Tab(2).Control(0).Enabled=   0   'False
      Tab(2).Control(1)=   "Command3"
      Tab(2).Control(1).Enabled=   0   'False
      Tab(2).ControlCount=   2
      Begin VB.CommandButton Command3 
         Caption         =   "Command3"
         Height          =   255
         Left            =   -74040
         TabIndex        =   6
         Top             =   1920
         Width           =   1575
      End
      Begin VB.TextBox Text3 
         Height          =   375
         Left            =   -74760
         TabIndex        =   5
         Text            =   "Text3"
         Top             =   600
         Width           =   1335
      End
      Begin VB.CommandButton Command2 
         Caption         =   "Command2"
         Height          =   375
         Left            =   -74520
         TabIndex        =   4
         Top             =   1680
         Width           =   1095
      End
      Begin VB.TextBox Text2 
         Height          =   285
         Left            =   -74520
         TabIndex        =   3
         Text            =   "Text2"
         Top             =   600
         Width           =   1695
      End
      Begin VB.CommandButton Command1 
         Caption         =   "Command1"
         Height          =   375
         Left            =   1560
         TabIndex        =   2
         Top             =   1800
         Width           =   1215
      End
      Begin VB.TextBox Text1 
         Height          =   375
         Left            =   480
         TabIndex        =   1
         Text            =   "Text1"
         Top             =   720
         Width           =   1455
      End
   End
End
Attribute VB_Name = "Form1"
Attribute VB_GlobalNameSpace = False
Attribute VB_Creatable = False
Attribute VB_PredeclaredId = True
Attribute VB_Exposed = False
