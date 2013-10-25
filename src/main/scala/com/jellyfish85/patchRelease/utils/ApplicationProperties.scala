package com.jellyfish85.patchRelease.utils

import java.util.Properties

object ApplicationProperties {

  val properties: Properties = new Properties()

  def init = properties.load(getClass().getResourceAsStream("/workspace.properties"))


  val workspace: String = {
    init
    properties.getProperty("workspace")
  }

  val tempPath: String = {
    init
    properties.getProperty("tempPath")
  }

  val mwHome: String = {
    init
    properties.getProperty("mwHome")
  }

  val webHome: String = {
    init
    properties.getProperty("webHome")
  }

  val jobHome: String = {
    init
    properties.getProperty("jobHome")
  }

}
