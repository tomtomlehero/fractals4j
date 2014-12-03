#!/bin/sh

export FRACTALS4J_JAR=/Users/mathieu/.m2/repository/fr/mla/fractals4j/1.0-SNAPSHOT/fractals4j-1.0-SNAPSHOT.jar
export FRACTALS4J_MAIN_CLASS=fr.mla.fractals4j.Fractals4jFrame

export WIDTH=800
export HEIGHT=600

# DO NOT APPEND '/'
export WORKING_DIRECTORY=/Users/mathieu/Desktop/fractals4jWorkshop

"$JAVA_HOME/bin/java" -cp $FRACTALS4J_JAR $FRACTALS4J_MAIN_CLASS $WIDTH $HEIGHT $WORKING_DIRECTORY





#export FRACTALS4J_HSV_TO_RGB_CLASS=fr.mla.fractals4j.color.HSVtoRGB

#export HUE=258.0
#export SATURATION=0.850
#export VALUE=0.37

#"$JAVA_HOME/bin/java" -cp $FRACTALS4J_JAR $FRACTALS4J_HSV_TO_RGB_CLASS $HUE $SATURATION $VALUE
