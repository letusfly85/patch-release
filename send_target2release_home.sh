#!/bin/sh

PARENT_PATH=`pwd`
echo $PARENT_PATH
./set_env

INPUT_DIR=$PARENT_PATH/input
if [ -f $INPUT_DIR/buildTargets ]; then
    cat $INPUT_DIR/buildTargets | while read appHome
    do
        echo $appHome
        cd $PARENT_PATH/buildHome/$appHome

        if test $appHome = "CL_WEB"; then
            #echo
        else
            mv target/*jar $PARENT_PATH/$APP_LIB/
        fi
    done
    cd $PARENT_PATH
fi

cd $PARENT_PATH/buildHome/$webHome
if [ -f $INPUT_DIR/clTargetFileList ]; then
    cat $INPUT_DIR/clTargetFileList | while read webClass
    do
        echo $webClass
        mv  $PARENT_PATH/$WEB_CLASS/$webClass $PARENT_PATH/$WEB_LIB/
    done
    cd $PARENT_PATH
fi

