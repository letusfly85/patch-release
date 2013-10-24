package com.jellyfish85.patchRelease.executor

import com.jellyfish85.dbaccessor.dao.erd.mainte.tool.MsTablesDao
import com.jellyfish85.dbaccessor.bean.erd.mainte.tool.MsTablesBean
import com.jellyfish85.dbaccessor.manager.DatabaseManager

class DownloadModules2Workspace extends AbstractPatchReleaseController {

  val db: DatabaseManager = new DatabaseManager

  def run(args: Array[String]) {

    if (args.isEmpty) {
      //TODO error message
      new RuntimeException("TODO")
    }
    db.connect

    val ticketNumber: BigDecimal = Integer.parseInt(args(0)).asInstanceOf[BigDecimal]

    val dao: MsTablesDao = new MsTablesDao
    val bean: MsTablesBean = new MsTablesBean
    val list: List[MsTablesBean] = dao.find(db.conn, bean)


  }

}
