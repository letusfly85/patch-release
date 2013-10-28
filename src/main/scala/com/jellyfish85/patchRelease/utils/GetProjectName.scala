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

    val clTargetFileList = new File(inputFolder.getPath, ApplicationProperties.clTargetFileList)
    val clPw: PrintWriter = new PrintWriter(new BufferedWriter(
      new OutputStreamWriter(new FileOutputStream(clTargetFileList),"UTF-8")))
    val replaceCLPath = ApplicationProperties.trunk + ApplicationProperties.clWebHome + "/src/main/java"

    var projectNameList: List[String] = List()
    for (i <- 0 to javaList.size()-1) {
      val bean: VChangesetsBean = javaList.get(i)
      val list = bean.pathAttr.value.split("/")

      val fifth  = list(5)
      val sixth  = list(6)

      fifth match {
        case "BL"       =>
          projectNameList ::= (fifth + "/" + sixth)

        case "CL_WEB"   =>
          projectNameList ::= fifth
          clPw.write(bean.pathAttr.value.replace(replaceCLPath, "").replace(".java", ".class"))
          clPw.write("\n")

        case _          =>
          projectNameList ::= fifth

      }
    }
    clPw.close()



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


  def getRootNames: util.ArrayList[String] = {
    val list: util.ArrayList[String] = new util.ArrayList[String]()

    try {
      val inputStream: InputStream = getClass().getResourceAsStream("/projectnames.properties")

      val reader: BufferedReader =
        new BufferedReader(new InputStreamReader(inputStream, "UTF-8"))

      var switch: Boolean = true
      var content: String = ""
      while (switch) {
        content = reader.readLine()
        if (content.eq(null)) {
          switch = false

        } else {
          list.add(content)
        }
      }
    }

    list
  }
}
