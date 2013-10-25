package com.jellyfish85.patchRelease.executor

import com.jellyfish85.patchRelease.utils.ApplicationProperties$
import com.jellyfish85.svnaccessor.getter.SVNGetFiles
import com.jellyfish85.svnaccessor.manager.SVNManager
import org.apache.commons.io.FileUtils
import org.apache.commons.io.FilenameUtils

class DownloadFullSetsByReleaseTag {

    public static void main(String[] args) {

        def app = ApplicationProperties$.newInstance()
        def buildHome = new File(app.getBuildHome())

        FileUtils.forceDelete(buildHome)
        FileUtils.forceMkdir(buildHome)

        def svn = new SVNManager()
        //def repository = svn

        def getter = new SVNGetFiles()
        //getter.simpleGetFilesRecursive()

    }

}
