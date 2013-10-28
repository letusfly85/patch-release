package com.jellyfish85.patchRelease.utils

import java.io.File
import org.apache.commons.io.FileUtils

class GenerateReleaseHome {


  val releaseHome: File = new File(ApplicationProperties.releaseHome)
  val webHome:     File = new File(ApplicationProperties.releaseHome, ApplicationProperties.webHome)
  val jobHome:     File = new File(ApplicationProperties.releaseHome, ApplicationProperties.jobHome)
  val mwHome:      File = new File(ApplicationProperties.releaseHome, ApplicationProperties.mwHome)

  def cleanup() {
      FileUtils.forceDeleteOnExit(releaseHome)
  }

  def setup() {
    FileUtils.forceMkdir(mwHome)
    FileUtils.forceMkdir(webHome)
    FileUtils.forceMkdir(jobHome)
  }
}
