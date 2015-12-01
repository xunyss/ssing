
@echo off

set CLASSPATH=            D:/xdev/git/ssing/ssing-cli/target/dependency/*
set CLASSPATH=%CLASSPATH%;D:/xdev/git/ssing/ssing-cli/target/classes
REM CLASSPATH=%CLASSPATH%;D:/xdev/git/ssing/ssing-cli/target/ssing-cli-0.0.1-SNAPSHOT.jar

set STARTCLASS=com.xunyss.ssing.cli.CliLauncher

java -classpath %CLASSPATH% %STARTCLASS% %*
