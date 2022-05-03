@ECHO OFF
cd "%CD%"\ticket-booker
set "port=8080"
set "javaPath="C:\Program Files\Java\jdk-17\bin""
if not "%~1"=="" set "javaPath=%1"
if not "%~2"=="" set "port=%2"
CALL %javaPath%\java.exe -jar .\target\ticket-booker-1.0.0.jar --server.port=%port%