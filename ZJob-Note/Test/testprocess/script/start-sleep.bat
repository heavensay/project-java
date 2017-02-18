@echo off

rem  后台运行，不显示cmd窗口
rem  if "%1" == "h" goto begin 
rem　　mshta vbscript:createobject("wscript.shell").run("%~nx0 h",0)(window.close)&&exit 
rem　　:begin

echo parent start

rem start /b  C:/Users/admin/git/project-java/ZJob-Note/Test/testprocess/script/sleep.bat
 call C:/Users/admin/git/project-java/ZJob-Note/Test/testprocess/script/sleep.bat

echo parent end

exit /b 3