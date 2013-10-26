#!/bin/sh

PARENT_PATH=`pwd`
echo $PARENT_PATH

cd $PARENT_PATH/buildHome/"$1"
mvn compile package

cd $PARENT_PATH