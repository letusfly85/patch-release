package com.jellyfish85.patchRelease.executor

import com.jellyfish85.patchRelease.utils.ApplicationProperties
import com.jellyfish85.patchRelease.utils.SimplePomFilter
import com.jellyfish85.svnaccessor.bean.SVNRequestBean
import com.jellyfish85.svnaccessor.getter.SVNGetFiles
import com.jellyfish85.svnaccessor.manager.SVNManager
import com.jellyfish85.patchRelease.utils.SimpleJavaFilter

import org.apache.commons.io.FileUtils

class DownloadFullSetsByReleaseTag {

    public static void main(String[] args) {

        def app = new ApplicationProperties()
        def buildHome = new File(app.buildHome)

        buildHome.deleteOnExit()
        FileUtils.forceMkdir(buildHome)

        def svn = new SVNManager()
        def repository   = svn.repository()

        def bean = new SVNRequestBean()

        bean.setPath(app.releaseTag() + app.appPrefix())

        def getter = new SVNGetFiles()

        // get pom.xml files
        def filter00 = new SimplePomFilter()
        getter.simpleGetFilesRecursive(repository, buildHome.getPath(),
                bean.path(), 0, filter00)

        // get java files
        def filter01 = new SimpleJavaFilter()
        getter.simpleGetFilesRecursive(repository, buildHome.getPath(),
                bean.path(), 1, filter01)
    }
}
