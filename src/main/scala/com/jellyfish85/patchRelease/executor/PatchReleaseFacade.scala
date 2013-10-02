package com.jellyfish85.patchRelease.executor

import com.jellyfish85.patchRelease.utils.ApplicationPropertiesUtils

object PatchReleaseFacade {

  def main(args: Array[String]) {

    val properties: Map[String, String] = ApplicationPropertiesUtils.applicationProperties

    val className: String = args(0)
    val obj = Class.forName(className).newInstance().asInstanceOf[{
      def run(properties: Map[String, String], args: Array[String])
    }]

    if (args.length > 2) {
      obj.run(properties, args.tail)

    } else {
      val ary = Array("")
      obj.run(properties, ary)
    }

  }

}
