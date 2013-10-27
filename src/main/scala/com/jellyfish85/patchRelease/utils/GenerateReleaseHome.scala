package com.jellyfish85.patchRelease.utils

import java.io.File
import org.apache.commons.io.FileUtils

class GenerateReleaseHome {

  val app = new ApplicationProperties

  val releaseHome: File = new File(app.releaseHome)
  val webHome:     File = new File(app.relesaseHome, app.webHome)
  val jobHome:     File = new File(app.relesaseHome, app.jobHome)
  val mwHome:      File = new File(app.relesaseHome, app.mwHome)

  def cleanup() {
      FileUtils.forceDeleteOnExit(releaseHome)
  }

  def setup() {
    FileUtils.forceMkdir(mwHome)
    FileUtils.forceMkdir(webHome)
    FileUtils.forceMkdir(jobHome)
  }
}
