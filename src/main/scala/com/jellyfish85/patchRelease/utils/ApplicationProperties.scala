package com.jellyfish85.patchRelease.utils

import java.util.Properties

class ApplicationProperties {
  val app = ApplicationProperties

  val getBuildHome: String = app.getBuildHome
}

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

  val trunk: String = {
    init
    properties.getProperty("trunk")
  }

  val releaseTag: String = {
    init
    properties.getProperty("releaseTag")
  }

  val appPrefix: String = {
    init
    properties.getProperty("appPrefix")
  }

  val input: String = {
    init
    properties.getProperty("input")
  }

  val buildTargets: String = {
    init
    properties.getProperty("buildTargets")
  }

  val blBasePath: String = {
    init
    properties.getProperty("blBasePath")
  }

  val blHead: String = {
    init
    properties.getProperty("blHead")
  }

  val blCore: String = {
    init
    properties.getProperty("blCore")
  }

  val clWebHome: String = {
    init
    properties.getProperty("clWebHome")
  }

  val jobEnvHome: String = {
    init
    properties.getProperty("jobEnvHome")
  }

  val releaseHome: String = {
    init
    properties.getProperty("releaseHome")
  }

  val webAppPath: String = {
    init
    properties.getProperty("webAppPath")
  }

  val jobBatchPath: String = {
    init
    properties.getProperty("jobBatchPath")
  }

  val jobShellPath: String = {
    init
    properties.getProperty("jobShellPath")
  }
}
