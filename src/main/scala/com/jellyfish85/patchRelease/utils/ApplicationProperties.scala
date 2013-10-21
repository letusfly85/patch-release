package com.jellyfish85.patchRelease.utils

import java.util.Properties

object ApplicationProperties {

  val properties: Properties = new Properties()

  properties.load(getClass().getResourceAsStream("/subversion.properties"))
  val subversionUser = properties.getProperty("user")
  val subversionPass = properties.getProperty("pass")
  val subversionUrl  = properties.getProperty("baseUrl")

  properties.load(getClass().getResourceAsStream("/workspace.properties"))
  val workspace = properties.getProperty("workspace")
  val tempPath  = properties.getProperty("tempPath")
  val mwHome    = properties.getProperty("mwHome")
  val webHome   = properties.getProperty("webHome")
  val jobHome   = properties.getProperty("jobHome")

}
