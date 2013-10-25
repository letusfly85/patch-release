package com.jellyfish85.patchRelease.executor

import com.jellyfish85.dbaccessor.dao.erd.mainte.tool.MsTablesDao
import com.jellyfish85.dbaccessor.bean.erd.mainte.tool.MsTablesBean
import com.jellyfish85.dbaccessor.manager.DatabaseManager

import java.math.BigDecimal

class DownloadModules2Workspace extends AbstractPatchReleaseController {

  val db: DatabaseManager = new DatabaseManager

  def run(args: Array[String]) {

    if (args.isEmpty) {
      //TODO error message
      new RuntimeException("TODO")
    }
    db.connect

    val ticketNumber: BigDecimal = new BigDecimal(args(0))

    val dao: MsTablesDao = new MsTablesDao
    val bean: MsTablesBean = new MsTablesBean
    bean.physicalTableNameAttr.value = "T_KK_KOKYK_KHN"
    val list: List[MsTablesBean] = dao.find(db.conn, bean)

    println(list.head.physicalTableNameAttr.value)

  }

}
