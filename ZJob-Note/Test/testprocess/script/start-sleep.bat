@echo off

rem  ��̨���У�����ʾcmd����
rem  if "%1" == "h" goto begin 
rem����mshta vbscript:createobject("wscript.shell").run("%~nx0 h",0)(window.close)&&exit 
rem����:begin

echo parent start

start /b  C:/Users/admin/git/project-java/ZJob-Note/Test/testprocess/script/sleep.bat
rem call C:/Users/admin/git/project-java/ZJob-Note/Test/testprocess/script/sleep.bat

echo parent end

exit 3