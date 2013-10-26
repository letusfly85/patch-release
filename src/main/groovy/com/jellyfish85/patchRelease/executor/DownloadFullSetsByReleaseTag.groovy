package com.jellyfish85.patchRelease.executor

import com.jellyfish85.patchRelease.utils.ApplicationProperties$
import com.jellyfish85.svnaccessor.bean.SVNRequestBean
import com.jellyfish85.svnaccessor.getter.SVNGetFiles
import com.jellyfish85.svnaccessor.manager.SVNManager
import org.apache.commons.io.FileUtils

class DownloadFullSetsByReleaseTag {

    public static void main(String[] args) {

        def app = ApplicationProperties$.newInstance()
        def buildHome = new File(app.getBuildHome())

        FileUtils.forceDelete(buildHome)
        FileUtils.forceMkdir(buildHome)

        def svn = new SVNManager()
        def repository = svn.repository()

        def bean = new SVNRequestBean()

        bean.setPath(app.releaseTag() + app.appPrefix())

        def getter = new SVNGetFiles()
        getter.simpleGetFilesRecursive(repository, buildHome.getPath(), bean.path(), 0, true)

    }
}
