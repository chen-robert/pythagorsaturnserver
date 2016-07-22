#!/bin/sh

J="target/mazeserver-1.0-jar-with-dependencies.jar"
echo pkill -f $J
pkill -f $J
echo nohup java -jar $J
nohup java -jar $J &
