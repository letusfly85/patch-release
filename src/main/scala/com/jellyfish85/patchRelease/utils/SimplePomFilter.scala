package com.jellyfish85.patchRelease.utils

import com.jellyfish85.svnaccessor.getter.SVNFilter
import com.jellyfish85.svnaccessor.bean.SVNRequestBean

class SimplePomFilter extends SVNFilter {

  def filter(bean: SVNRequestBean): Boolean = {

    if (bean.fileName == "pom.xml") {
      true

    } else {
      false
    }
  }
}
