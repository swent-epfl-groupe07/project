package com.github.se.project.model.folder

private val daysInWeek = 7
private val hoursInDay = 14


class TimeTable {
    val selected: List<List<Boolean>> = List(daysInWeek) {
        List(hoursInDay) {false} }
}