' Place some settings in the registry.
SaveSetting appname := "MyApp", section := "Startup", _
            key := "Top", setting := 75 
SaveSetting "MyApp","Startup", "Left", 50 
' Remove section and all its settings from registry.
DeleteSetting "MyApp", "Startup"