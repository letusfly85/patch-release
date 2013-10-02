package com.jellyfish85.patchRelease.executor

abstract class AbstractPatchReleaseController {

  def run(properties: Map[String, String], args: Array[String])

}
