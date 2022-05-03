@ECHO OFF
set baseDir="%CD%"
CALL %baseDir%\Compile.bat
CALL %baseDir%\Run.bat %1 %2