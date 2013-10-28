#!/bin/sh

PARENT_PATH=`pwd`
echo $PARENT_PATH
source   $PARENT_PATH/set_env

INPUT_DIR=$PARENT_PATH/input
if [ -f $INPUT_DIR/buildTargets ]; then
    cat $INPUT_DIR/buildTargets | while read appHome
    do
        echo $appHome
        cd $PARENT_PATH/buildHome/$appHome

        if test $appHome = "CL_WEB"; then
            echo pass
        else
            cp target/*jar $PARENT_PATH/$APP_LIB/
        fi
    done
    cd $PARENT_PATH
fi

cd $PARENT_PATH/buildHome/$webHome
if [ -f $INPUT_DIR/clTargetFileList ]; then
    cat $INPUT_DIR/clTargetFileList | while read webClass
    do
        export BASE_NAME=`basename "${webClass}"`
        export WEB_PATH=`echo "${webClass}" | sed -e s/"${BASE_NAME}"//`
        export DIST_PATH=$PARENT_PATH/$WEB_LIB/$WEB_PATH

        if [ ! -d $DIST_PATH ]; then
                mkdir -p $DIST_PATH
        fi
        echo $DIST_PATH
        cp  $PARENT_PATH/$WEB_CLASS/$webClass $DIST_PATH/
    done
    cd $PARENT_PATH
fi