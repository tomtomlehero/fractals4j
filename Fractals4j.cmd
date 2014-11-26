@echo off

set FRACTALS4J_JAR=C:\MLA\dvpt\_mvn_repo_\fr\mla\fractals4j\1.0-SNAPSHOT\fractals4j-1.0-SNAPSHOT.jar
set FRACTALS4J_MAIN_CLASS=fr.mla.fractals4j.Fractals4jApp

set X0=-2.0
set X1=1.0
set Y0=-1.0
set Y1=1.0

set WIDTH=800
set HEIGHT=600

set MAX_ITERATIONS=1000

:: DO NOT APPEND '/'
set WORKING_DIRECTORY="C:/Users/MHDB4820/Desktop/fractals4jWorkshop"

set JAVA_8_HOME=C:\Program Files\Java\jdk1.8.0_05

"%JAVA_8_HOME%\bin\java" -cp %FRACTALS4J_JAR% %FRACTALS4J_MAIN_CLASS% %X0% %X1% %Y0% %Y1% %WIDTH% %HEIGHT% %MAX_ITERATIONS% %WORKING_DIRECTORY%
