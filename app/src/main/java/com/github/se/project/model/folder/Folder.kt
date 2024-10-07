package com.github.se.project.model.folder

import com.github.se.project.model.widget.Widget

class Folder(
    val pdfFiles: List<String>,
    val name: String,
    val timeTable: TimeTable
): Widget