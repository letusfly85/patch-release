package com.jellyfish85.patchRelease.converter

import com.jellyfish85.dbaccessor.bean.src.mainte.tool.VChangesetsBean
import com.jellyfish85.svnaccessor.bean.SVNRequestBean

class VChangeSetsBean2SVNRequestBeanConv {

  def convert(list: List[VChangesetsBean]): List[SVNRequestBean] = {
    var targetList: List[SVNRequestBean] = List()

    list.foreach {bean: VChangesetsBean =>
      val targetBean: SVNRequestBean = new SVNRequestBean

      targetBean.fileName = bean.fileNameAttr.value
      targetBean.path     = bean.pathAttr.value
      targetBean.revision = bean.revisionAttr.value.longValue()

      targetList ::= targetBean
    }

    targetList
  }

}
