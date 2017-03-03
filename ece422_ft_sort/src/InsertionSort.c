#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <jni.h>
#include "InsertionSort.h"

JNIEXPORT jintArray JNICALL Java_InsertionSort_insertionSort(JNIEnv *env, jobject obj, jintArray ints){
    printf("Hello World!");
}