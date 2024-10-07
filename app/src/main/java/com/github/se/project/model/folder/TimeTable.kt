package com.github.se.project.model.folder

import androidx.compose.runtime.Composable

private val daysInWeek = 7
private val firstHour = 7
private val lastHour = 21
private val hoursInDay = lastHour - firstHour


class TimeTable {
    private val selected: MutableList<MutableList<Boolean>> = MutableList(daysInWeek) {
        MutableList(hoursInDay) {false} }


    /**
     * Select an hour in the time table if it isn't selected already.
     * Otherwise, do nothing.
     * @param day the day in which we want to select. Monday=1, sunday=7
     * @param hour the hour to select. Go from 7 to 20
     * @throws IllegalArgumentException arguments are not in required range
     */
    fun selectSlot(day: Int, hour: Int) {
        checkValidTime(day, hour)
        selected[day][hour-firstHour] = true
    }


    /**
     * Unselect an hour in the time table if it is selected.
     * Otherwise, do nothing.
     * @param day the day in which we want to unselect. Monday=1, sunday=7
     * @param hour the hour to unselect. Go from 7 to 20
     * @throws IllegalArgumentException arguments are not in required range
     */
    fun unselectSlot(day: Int, hour: Int) {
        checkValidTime(day, hour)
        selected[day][hour-firstHour] = false
    }


    @Composable
    fun TimeTableComposable() {

    }
}


private fun checkValidTime(day: Int, hour: Int) {
    if (day !in 1..7) throw IllegalArgumentException("Invalid day: $day")
    if (hour !in firstHour until lastHour) throw IllegalArgumentException(
        "Invalid hour: $hour not in range $firstHour - $lastHour")
}