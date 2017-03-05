#!/bin/bash

INPUT=$1
RANDOM_NUMBER=$2
OUTPUT=$3
PFAIL=$4
SFAIL=$5
TIMEOUT=$6

javac -classpath ./commons-io-2.5.jar  *.java 
javah -jni InsertionSort

export LD_LIBRARY_PATH=$LD_LIBRARY_PATH:.

rm ./libInsertionSort.so

gcc -shared -fpic -o libInsertionSort.so -I/$JAVA_HOME/include -I/$JAVA_HOME/include/linux InsertionSort.c

java -classpath ./:./commons-io-2.5.jar  DataGenerator $INPUT $RANDOM_NUMBER

java -classpath ./:./commons-io-2.5.jar SortDriver $INPUT $OUTPUT $PFAIL $SFAIL $TIMEOUT
