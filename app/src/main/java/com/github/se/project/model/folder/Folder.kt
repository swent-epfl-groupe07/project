package com.github.se.project.model.folder

import com.github.se.project.model.widget.Widget
import kotlinx.coroutines.flow.MutableStateFlow
import java.util.Calendar

class Folder(
    val pdfFiles: MutableStateFlow<MutableList<MyFile>>,
    val name: String,
    val timeTable: TimeTable
): Widget


class MyFile(
    val name: String,
    val creationTime: Calendar,
    val lastAccess: Calendar,
    val numberAccess: Int
)


enum class FilterTypes {
    NAME,
    CREATION_UP,
    CREATION_DOWN,
    ACCESS_RECENT,
    ACCESS_OLD,
    ACCESS_MOST,
    ACCESS_LEAST
}