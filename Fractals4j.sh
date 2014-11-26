#!/bin/sh

export FRACTALS4J_JAR=/Users/mathieu/.m2/repository/fr/mla/fractals4j/1.0-SNAPSHOT/fractals4j-1.0-SNAPSHOT.jar
export FRACTALS4J_MAIN_CLASS=fr.mla.fractals4j.Fractals4jFrame

#export X0=-2.0
#export X1=1.0
#export Y0=-1.0
#export Y1=1.0

#export WIDTH=800
#export HEIGHT=600

#export MAX_ITERATIONS=1000

# DO NOT APPEND '/'
#export WORKING_DIRECTORY="C:/Users/MHDB4820/Desktop/fractals4jWorkshop"

"$JAVA_HOME/bin/java" -cp $FRACTALS4J_JAR $FRACTALS4J_MAIN_CLASS
