package com.jellyfish85.patchRelease.utils

import java.util.Properties

object ApplicationProperties {

  val properties: Properties = new Properties()

  var _buildHome: String = _

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

  val buildHome: String = {
    init
    _buildHome = properties.getProperty("buildHome")
    _buildHome
  }
  def getBuildHome: String = {
    buildHome

    _buildHome
  }

  val appPrefix: String = {
    init
    properties.getProperty("appPrefix")
  }

}
