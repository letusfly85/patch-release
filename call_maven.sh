#!/bin/sh

PARENT_PATH=`pwd`
echo $PARENT_PATH

INPUT_DIR=$PARENT_PATH/input
if [ -f $INPUT_DIR/buildTargets ]; then

    cat target | while readline appHome
    do
        echo $appHome
        cd $PARENT_PATH/buildHome/$appHome
        mvn compile package
    done

    cd $PARENT_PATH
fi
