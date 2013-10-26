package com.jellyfish85.patchRelease.executor

import com.jellyfish85.dbaccessor.bean.src.mainte.tool.VChangesetsBean
import com.jellyfish85.dbaccessor.dao.src.mainte.tool.VChangesetsDao
import com.jellyfish85.dbaccessor.manager.DatabaseManager

import com.jellyfish85.patchRelease.utils.ApplicationProperties
import com.jellyfish85.patchRelease.utils.GetProjectName
import org.apache.commons.io.FileUtils
import org.apache.commons.io.FilenameUtils

/**
 * == CopyJava2Repositories ==
 *
 * copy java files to buildHome
 *
 * @author  wada.shunsuke
 * @since   2013/10/26
 * @version 0.0.1
 *
 */
class CopyJava2Repositories  {

    /**
     *
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

        def javaList = dao.convert(list).findAll {VChangesetsBean v ->
            FilenameUtils.getExtension(v.fileNameAttr().value()) == "java"}


        def app = new ApplicationProperties()
        def buildHome = new File(app.getBuildHome())
        if (!buildHome.exists()) {
            FileUtils.forceMkdir(buildHome)
        }
        javaList.each {VChangesetsBean v ->
            println("copying...." + v.pathAttr().value())

            def header = app.trunk() + app.appPrefix()

            def src  = new File(app.workspace(), v.pathAttr().value())
            def dist = new File(app.getBuildHome(), v.pathAttr().value().replace(header, ""))
            FileUtils.copyFile(src, dist)
        }

        def getter = new GetProjectName()
        getter.getter(javaList)
    }
}