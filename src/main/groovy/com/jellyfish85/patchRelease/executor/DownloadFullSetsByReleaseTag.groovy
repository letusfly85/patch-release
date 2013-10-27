package com.jellyfish85.patchRelease.executor

import com.jellyfish85.patchRelease.utils.ApplicationProperties
import com.jellyfish85.patchRelease.utils.GetProjectName
import com.jellyfish85.patchRelease.utils.SimpleJavaFilter
import com.jellyfish85.svnaccessor.bean.SVNRequestBean
import com.jellyfish85.svnaccessor.getter.SVNGetFiles
import com.jellyfish85.svnaccessor.manager.SVNManager
import org.apache.commons.io.FileUtils
import org.tmatesoft.svn.core.io.SVNRepository

class DownloadFullSetsByReleaseTag {

    public static void main(String[] args) {

        def app = new ApplicationProperties()
        def buildHome = new File(app.buildHome)

        buildHome.deleteOnExit()
        FileUtils.forceMkdir(buildHome)

        def bean = new SVNRequestBean()

        bean.setPath(app.releaseTag() + app.appPrefix())

        def getter = new SVNGetFiles()
        def pjNmGetter = new GetProjectName()

        if (buildHome.exists()) {
            FileUtils.cleanDirectory(buildHome)
        }

        SimpleJavaFilter filter = new SimpleJavaFilter()

        def removePath = app.releaseTag() + app.appPrefix()
        def list = pjNmGetter.rootNames
        list.each {String projectName ->
            def bean0 = new SVNRequestBean()

            bean0.setPath(app.releaseTag() + app.appPrefix() + projectName + "/pom.xml")
            bean0.setFileName("pom.xml")
            getter.simpleGetFile(bean0, buildHome, removePath)

            bean0.setPath(app.releaseTag() + app.appPrefix() + projectName)
            getter.simpleGetFilesRecursive(
                    buildHome.path.toString(),
                    bean0.path().toString(),
                    1,
                    filter,
                    removePath
            )
        }
    }
}