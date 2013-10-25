package com.jellyfish85.patchRelease.executor

import com.jellyfish85.dbaccessor.bean.src.mainte.tool.VChangesetsBean
import com.jellyfish85.dbaccessor.dao.src.mainte.tool.VChangesetsDao
import com.jellyfish85.dbaccessor.manager.DatabaseManager

class CopyJava2Repositories  {

    public static void main(String[] args) {

        def ticketNumber = args[0].toLong()
        println(args[0])

        def db = new DatabaseManager()
        db.connect()

        def dao = new VChangesetsDao()
        def list = dao.findByTicketNumber(db.conn(), ticketNumber)
        //println(list.toList())

        def myList = dao.convert(list)
        println(myList[0])
        println(myList.size())
        println(myList[0].fileNameAttr().value())
        myList.each {VChangesetsBean bean ->
            println(bean.fileNameAttr().value())
        }
    }
}
