package com.jellyfish85.patchRelease.utils

import com.jellyfish85.svnaccessor.bean.SVNRequestBean
import org.apache.commons.io.FilenameUtils
import com.jellyfish85.svnaccessor.getter.SVNFilter

class SimpleJavaFilter extends SVNFilter {

    def filter(bean: SVNRequestBean): Boolean = {
        if (FilenameUtils.getExtension(bean.fileName) == "java") {
            true

        } else {
           false
        }
    }
}
