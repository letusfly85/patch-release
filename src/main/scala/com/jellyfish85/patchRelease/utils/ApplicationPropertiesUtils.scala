package com.jellyfish85.patchRelease.utils

import java.util.Properties

object ApplicationPropertiesUtils {

  /**
   *
   * @todo   code
   * @return map of application properties
   */
  def applicationProperties: Map[String, String] = {
    var map: Map[String, String] = Map()

    val properties: Properties = new Properties()
    properties.load(getClass().getResourceAsStream("/subversion.properties"))

    map ++= Map("subversionUser" -> properties.getProperty("user"))
    map ++= Map("subversionPass" -> properties.getProperty("pass"))
    map ++= Map("subversionUrl"  -> properties.getProperty("baseUrl"))

    map
  }

}
