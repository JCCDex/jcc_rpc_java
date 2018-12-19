#!/bin/bash
cd ./rpc
if [ "$1" == "test" ];then
    mvn test
else
    mvn clean compile jar:jar
fi

