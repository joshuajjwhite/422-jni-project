#!/bin/bash

./make.sh output 10 sorted 0.02 0.001 6000

echo "Test 1"
./tester.pl sorted

echo "Test 2"
./make.sh output 1000 sorted 0.02 0.001 6000
./tester.pl sorted

echo "Test 3"
./make.sh output 10000000 sorted 0.02 0.001 6000
./tester.pl sorted

echo "Test 4"
./make.sh output 10 sorted 100.0 0.001 6000

echo "Test 5"
./make.sh output 10 sorted 0.02 100 6000

echo "Test 6"
./make.sh output 10 sorted 0.02 0.001 10

