package com.jellyfish85.patchRelease.executor

import com.jellyfish85.dbaccessor.bean.src.mainte.tool.VChangesetsBean
import com.jellyfish85.dbaccessor.dao.src.mainte.tool.VChangesetsDao
import com.jellyfish85.dbaccessor.manager.DatabaseManager
import com.jellyfish85.patchRelease.utils.ApplicationProperties
import com.jellyfish85.patchRelease.utils.GenerateReleaseHome
import org.apache.commons.io.FileUtils
import org.apache.commons.io.FilenameUtils

/**
 *
 */
class GeneratePatchSetsWithoutJar {

    /**
     *
     * @param args
     */
    public static void main(String[] args) {
        def ticketNumber = args[0].toLong()
        println(args[0])

        def db = new DatabaseManager()
        db.connect()
        def dao = new VChangesetsDao()
        def list = dao.findByTicketNumber(db.conn(), ticketNumber)

        // generate directory for release
        def directoryGenerator = new GenerateReleaseHome()
        directoryGenerator.setup()

        def app = new ApplicationProperties()
        dao.convert(list).each {VChangesetsBean bean ->
        }
        VChangesetsBean[] sourceList = dao.convert(list).findAll {VChangesetsBean v ->
            (
                FilenameUtils.getExtension(v.fileNameAttr().value()) != "java"
            ) && (
                v.pathAttr().value().matches(".*" + app.appPrefix() + ".*")
            )
        }

        attachSources(sourceList)
    }

    /**
     *
     * @param list
     */
    public static void attachSources(VChangesetsBean[] list) {
        def app = new ApplicationProperties()

        def blList = list.findAll {VChangesetsBean v ->
            (v.pathAttr().value().matches(".*" + app.blHead() + ".*")) ||
            (v.pathAttr().value().matches(".*" + app.blCore() + ".*"))
        }
        attachBLSources(blList, app)

        def clWebList = list.findAll {VChangesetsBean v ->
            v.pathAttr().value().matches(".*" + app.clWebHome() + ".*")
        }
        attachCLSources(clWebList, app)

        def jobEnvList = list.findAll {VChangesetsBean v ->
            v.pathAttr().value().matches(".*" + app.jobEnvHome() + ".*")
        }
        attachJOBSources(jobEnvList, app)

    }

    /**
     *
     * @param list
     * @param app
     * @return
     */
    public static attachBLSources(ArrayList<VChangesetsBean> list, ApplicationProperties app) {
        def mwHome = new File(app.releaseHome(), app.mwHome())

        def removePathHead = app.trunk() + app.blHead()
        def removePathBody = app.blBasePath()

        def blRemovePath  = "(" + removePathHead + "/)([A-Z_]+)(" + removePathBody + ")"
        def xqlRemovePath = "(" + removePathHead + "/)([A-Z_]+)(" + removePathBody + ")"
        def xlsRemovePath = "(" + app.trunk() + ")(" + app.blCore() + ")"

        list.each {VChangesetsBean bean ->

            def ext = FilenameUtils.getExtension(bean.pathAttr().value())
            def src = new File(app.workspace(), bean.pathAttr().value())

            if (ext == "bl") {
                    def dist = new File(mwHome.getPath(), bean.pathAttr().value().
                            replaceAll(blRemovePath, "").replace(app.workspace(),""))
                    println(ext + "\t" + blRemovePath  + "\t" + dist)
                    FileUtils.copyFile(src, dist)

            } else if (ext == "xql") {
                    def dist = new File(mwHome.getPath(), bean.pathAttr().value().
                            replaceAll(xqlRemovePath, "").replace(app.workspace(),""))
                    println(ext + "\t" + xqlRemovePath  + "\t" + dist)
                    FileUtils.copyFile(src, dist)

            } else if (ext == "xls") {
                    def dist = new File(mwHome.getPath(), bean.pathAttr().value().
                            replaceAll(xlsRemovePath, "").replace(app.workspace(),""))
                    println(ext + "\t" + xlsRemovePath  + "\t" + dist)
                    FileUtils.copyFile(src, dist)

            } else {
                    print("other extension .. ")
                    println(src)

            }
        }
    }

    /**
     *
     * @param list
     * @param app
     * @return
     */
    public static attachCLSources(ArrayList<VChangesetsBean> list, ApplicationProperties app) {
        def clHome = new File(app.releaseHome(), app.webHome())

        def removePathHead = app.trunk() + app.clWebHome()

        def webRemovePath  = "(" + removePathHead + ")(" + app.webAppPath() + ")"
        list.each {VChangesetsBean bean ->

            def ext = FilenameUtils.getExtension(bean.pathAttr().value())
            def src = new File(app.workspace(), bean.pathAttr().value())

            if (ext == "page") {
                def dist = new File(clHome.getPath(), bean.pathAttr().value().
                        replaceAll(webRemovePath, "").replace(app.workspace(),""))
                println(ext + "\t" + webRemovePath  + "\t" + dist)
                FileUtils.copyFile(src, dist)

            } else if(ext == "js") {
                def dist = new File(clHome.getPath(), bean.pathAttr().value().
                        replaceAll(webRemovePath, "").replace(app.workspace(),""))
                println(ext + "\t" + webRemovePath  + "\t" + dist)
                FileUtils.copyFile(src, dist)

            } else if(ext == "lyt") {
                def dist = new File(clHome.getPath(), bean.pathAttr().value().
                        replaceAll(webRemovePath, "").replace(app.workspace(),""))
                println(ext + "\t" + webRemovePath  + "\t" + dist)
                FileUtils.copyFile(src, dist)

            } else if (ext == "xml") {
                def dist = new File(clHome.getPath(), bean.pathAttr().value().
                        replaceAll(webRemovePath, "").replace(app.workspace(),""))
                println(ext + "\t" + webRemovePath  + "\t" + dist)
                FileUtils.copyFile(src, dist)

            } else if(ext == "jsp") {
                def dist = new File(clHome.getPath(), bean.pathAttr().value().
                        replaceAll(webRemovePath, "").replace(app.workspace(),""))
                println(ext + "\t" + webRemovePath  + "\t" + dist)
                FileUtils.copyFile(src, dist)

            } else {
                print("other extension .. ")
                println(src)

            }

        }
    }

    /**
     *
     * @todo
     * @param bean
     */
    public static void attachJOBSources(ArrayList<VChangesetsBean> list, ApplicationProperties app) {
        def jobHome = new File(app.releaseHome(), app.jobHome())

        def removePathHead = app.trunk() + app.jobEnvHome()

        def jobBatchRemovePath  = "(" + removePathHead + ")(" + app.jobBatchPath() + ")"
        def jobShellRemovePath  = "(" + removePathHead + ")(" + app.jobShellPath() + ")"

        list.each {VChangesetsBean bean ->

            def ext = FilenameUtils.getExtension(bean.pathAttr().value())
            def src = new File(app.workspace(), bean.pathAttr().value())

            if (ext =="ctl") {
                    def dist = new File(jobHome.getPath(), bean.pathAttr().value().
                            replaceAll(jobBatchRemovePath, "").replace(app.workspace(),""))
                    println(ext + "\t" + jobBatchRemovePath  + "\t" + dist)
                    FileUtils.copyFile(src, dist)

            } else if (ext == "sql") {
                    def dist = new File(jobHome.getPath(), bean.pathAttr().value().
                            replaceAll(jobBatchRemovePath, "").replace(app.workspace(),""))
                    println(ext + "\t" + jobBatchRemovePath  + "\t" + dist)
                    FileUtils.copyFile(src, dist)

            } else if (ext == "sh") {
                    def dist = new File(jobHome.getPath() + "/script", bean.pathAttr().value().
                            replaceAll(jobShellRemovePath, "").replace(app.workspace(),""))
                    println(ext + "\t" + jobShellRemovePath  + "\t" + dist)
                    FileUtils.copyFile(src, dist)

            } else {
                print("other extension .. ")
                println(src)

            }
        }
    }
}
