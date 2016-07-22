#!/bin/sh

if [ "$WORKSPACE" == "" ]
then
    echo WORKSPACE is missing
    pwd
else
    echo WORKSPACE is "$WORKSPACE"
    cd $WORKSPACE
    pwd
fi
J="target/mazeserver-1.0-jar-with-dependencies.jar"
ls -l "$J"
echo pkill -f "$J"
pkill -f $J
echo nohup java -jar "$J"
nohup java -jar "$J" >/tmp/mazeserver.out 2>&1 &
sleep 0.2
pgrep -f "$J"
