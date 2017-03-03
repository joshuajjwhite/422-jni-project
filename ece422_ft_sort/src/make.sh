#!/bin/bash

javac -classpath ./commons-io-2.5.jar  *.java 
javah -jni InsertionSort

export LD_LIBRARY_PATH=$LD_LIBRARY_PATH:.

gcc -shared -fpic -o libInsertionSort.so -I/$JAVA_HOME/include -I/$JAVA_HOME/include/linux InsertionSort.c

java -classpath ./:./commons-io-2.5.jar SortDriver output sorted 0.001 0.001 6000
