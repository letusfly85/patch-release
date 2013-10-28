package com.jellyfish85.patchRelease.executor

import com.jellyfish85.dbaccessor.bean.src.mainte.tool.VChangesetsBean
import com.jellyfish85.dbaccessor.dao.src.mainte.tool.VChangesetsDao
import com.jellyfish85.dbaccessor.manager.DatabaseManager
import com.jellyfish85.patchRelease.utils.ApplicationProperties
import com.jellyfish85.patchRelease.utils.GenerateReleaseHome
import org.apache.commons.io.FileUtils
import org.apache.commons.io.FilenameUtils

class GeneratePatchSetsWithoutJar {

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
        def sourceList = dao.convert(list).findAll {VChangesetsBean v ->
            (
                FilenameUtils.getExtension(v.fileNameAttr().value()) != "java"
            ) && (
                v.fileNameAttr().value().matches(".*" + app.appPrefix() + ".*")
            )
        }

        attachSources(sourceList)
    }

    def attachSources(ArrayList<VChangesetsBean> list) {
        def app = new ApplicationProperties()

        def blList = list.findAll {VChangesetsBean v ->
            v.fileNameAttr().value().matches(".*" + app.blHead() + ".*")
        }
        attachBLSources(blList, app)

        /*def clWebList = list.findAll {VChangesetsBean v ->
            v.fileNameAttr().value().matches(".*" + app.clWebHome() + ".*")
        }

        def jobEnvList = list.findAll {VChangesetsBean v ->
            v.fileNameAttr().value().matches(".*" + app.jobEnvHome() + ".*")
        } */

    }

    /**
     *
     * @todo
     * @param bean
     */
    def attachBLSources(ArrayList<VChangesetsBean> list, ApplicationProperties app) {
        def mwHome = new File(app.releaseHome(), app.mwHome())

        def removePathHead = app.trunk() + app.blHead()
        def removePathBody = app.blBasePath()
        def removePath = "(" + removePathHead + ")([A-Z_]+)(" + removePathBody + ")"

        list.each {VChangesetsBean bean ->
            println(bean.pathAttr().value())

            def ext = FilenameUtils.getExtension(bean.pathAttr().value())
            def src = new File(app.workspace(), bean.pathAttr().value())

            switch (ext) {
                case "bl":

                    def dist = new File(mwHome.getPath(), src.getPath().replaceAll(removePath, ""))
                    println(dist)
                    FileUtils.copyFile(src, dist)

                case "xql":
                    //TODO
                    assert 1 == 1;

                case "xls":
                    //TODO
                    assert 1 == 1;
            }
        }
    }

    /**
     *
     * @todo
     * @param bean
     */
    def attachCLSources(VChangesetsBean bean) {

        def ext = FilenameUtils.getExtension(bean.pathAttr().value())
        switch (ext) {
            case "page":
                //TODO
                assert 1 == 1;

            case "js":
                //TODO
                assert 1 == 1;

            case "lyt":
                //TODO
                assert 1 == 1;

            case "xml":
                //TODO
                assert 1 == 1;
        }
    }

    /**
     *
     * @todo
     * @param bean
     */
    def attachJOBSources(VChangesetsBean bean) {

        def ext = FilenameUtils.getExtension(bean.pathAttr().value())
        switch (ext) {
            case "ctl":
                //TODO
                assert 1 == 1;

            case "sql":
                //TODO
                assert 1 == 1;

            case "sh":
                //TODO
                assert 1 == 1;
        }
    }
}
