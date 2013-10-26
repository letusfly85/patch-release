package com.jellyfish85.patchRelease.utils

import com.jellyfish85.dbaccessor.bean.src.mainte.tool.VChangesetsBean

import java.util
import java.io._
import org.apache.commons.io.FileUtils

class GetProjectName {

  def getter(javaList: util.ArrayList[VChangesetsBean]) {

    val inputFolder: File = new File(ApplicationProperties.input)
    if (inputFolder.exists()) {
      FileUtils.forceDelete(inputFolder)
    }
    FileUtils.forceMkdir(inputFolder)

    var projectNameList: List[String] = List()
    for (i <- 0 to javaList.size()-1) {
      val bean: VChangesetsBean = javaList.get(i)
      val list = bean.pathAttr.value.split("/")

      val fifth  = list(5)
      val sixth  = list(6)

      fifth match {
        case "BL"=> projectNameList ::= sixth
        case _   => projectNameList ::= fifth
      }
    }

    val targetFile = new File(inputFolder.getPath, ApplicationProperties.buildTargets)
    val pw: PrintWriter = new PrintWriter(new BufferedWriter(
      new OutputStreamWriter(new FileOutputStream(targetFile),"UTF-8")))

    removeDuplicates(projectNameList).foreach {projectName: String =>
      println(projectName)
      pw.write(projectName)
      pw.write("\n")
    }

    pw.close()
  }

  def removeDuplicates[A](xs: List[A]): List[A] =
    if (xs.isEmpty) xs
    else xs.head :: removeDuplicates(xs.tail filter (x => x != xs.head))
}
