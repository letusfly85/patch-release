package com.jellyfish85.patchRelease.executor

import com.jellyfish85.dbaccessor.manager.DatabaseManager

import java.math.BigDecimal
import com.jellyfish85.dbaccessor.dao.src.mainte.tool.VChangesetsDao
import com.jellyfish85.dbaccessor.bean.src.mainte.tool.VChangesetsBean
import com.jellyfish85.svnaccessor.getter.SVNGetFiles
import com.jellyfish85.patchRelease.converter.VChangeSetsBean2SVNRequestBeanConv
import com.jellyfish85.svnaccessor.bean.SVNRequestBean
import java.io.File
import org.apache.commons.io.FileUtils
import com.jellyfish85.patchRelease.utils.ApplicationProperties

class DownloadModules2Workspace extends AbstractPatchReleaseController {

  val db: DatabaseManager = new DatabaseManager

  def run(args: Array[String]) {

    if (args.isEmpty) {
      new RuntimeException("arguments have to have a classname and its arguments.")
    }
    db.connect

    val ticketNumber: BigDecimal = new BigDecimal(args(0))

    val dao: VChangesetsDao = new VChangesetsDao
    val changesets: List[VChangesetsBean] = dao.findByTicketNumber(db.conn, ticketNumber)
    changesets.foreach {bean: VChangesetsBean =>
      println(bean.pathAttr.value)
    }

    val converter: VChangeSetsBean2SVNRequestBeanConv = new VChangeSetsBean2SVNRequestBeanConv
    val list: List[SVNRequestBean] = converter.convert(changesets)
    val getter: SVNGetFiles = new SVNGetFiles

    val workspace: File = new File(ApplicationProperties.workspace)

    FileUtils.forceMkdir(workspace)
    getter.simpleGetFilesWithDirectory(list, workspace)

  }
}