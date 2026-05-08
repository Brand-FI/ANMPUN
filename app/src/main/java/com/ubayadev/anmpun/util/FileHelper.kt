package com.ubayadev.anmpun.util

import android.content.Context
import java.io.File
import java.io.FileOutputStream
import java.io.IOException

class FileHelper(val context: Context) {
    val folderName = "habit"
    val fileName = "habitdata.txt"

    private fun getFile(): File {
        val dir = File(context.filesDir, folderName)
        if (!dir.exists()) {
            dir.mkdirs() // bikin folder jika folder belum ada
        }
        return File(dir, fileName)
    }


    // tulis string ke dalam file
    fun writeToFile(data: String) {
        try {
            val file = getFile()
            FileOutputStream(file, false).use {
                it.write(data.toByteArray())
            }
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }

    fun readFromFile(): String {
        return try {
            val file = getFile()
            file.bufferedReader().useLines { lines ->
                lines.joinToString("\n")
            }
        } catch (e: IOException) {
          ""
        }
    }
    fun deleteFile(): Boolean {
        return getFile().delete()
    }

    fun getFilePath(): String {
        return getFile().absolutePath
    }

}