
@echo off

set CLASSPATH=            D:/xdev/git/ssing/ssing-cli/target/ssing-cli-0.0.1-SNAPSHOT.jar
set CLASSPATH=%CLASSPATH%;D:/xdev/git/ssing/ssing-cli/target/dependency/*

set STARTCLASS=com.xunyss.ssing.cli.CliMain

java -classpath %CLASSPATH% %STARTCLASS% %*
