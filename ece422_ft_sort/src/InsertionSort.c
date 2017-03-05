#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <jni.h>
#include "InsertionSort.h"

JNIEXPORT jintArray JNICALL Java_InsertionSort_insertionSort
  (JNIEnv *env, jobject obj, jintArray ints){

    jsize len = (*env)->GetArrayLength(env, ints);
    //first element is memory accesses
    //Rest is the array
    jint *body = (*env)->GetIntArrayElements(env, ints, 0);
    jint memoryAccesses = body[0];
    memoryAccesses += 6;

    int i, j, temp;

    //source for algorithm
    //https://en.wikipedia.org/wiki/Insertion_sort
    //Sort the array leaving the number of memory access untouched
    for (i=2; i<len; i++) {
        memoryAccesses += 3;
        j = i;
        memoryAccesses += 2;

        while((j > 1) && (body[j-1] > body[j])){
            memoryAccesses += 3;

            temp = body[j];
            memoryAccesses += 2;

            body[j] = body[j-1];
            memoryAccesses += 2;

            body[j-1] = temp;
            memoryAccesses += 2;

            j--;
            memoryAccesses++;
        }
    }

    body[0] = memoryAccesses;
    (*env)->SetIntArrayRegion(env, ints, 0, len, body);
    (*env)->ReleaseIntArrayElements(env, ints, body, 0);

    return ints;
}