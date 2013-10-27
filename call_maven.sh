#!/bin/sh

PARENT_PATH=`pwd`
echo $PARENT_PATH

INPUT_DIR=$PARENT_PATH/input
if [ -f $INPUT_DIR/buildTargets ]; then
    cat $INPUT_DIR/buildTargets | while read appHome
    do
        echo $appHome
        cd $PARENT_PATH/buildHome/$appHome

        if test $appHome = "CL_WEB"; then
            mvn compile
        else
            mvn compile package
        fi
    done
    cd $PARENT_PATH
fi
