package com.example.studyingproject

import javax.inject.Inject


class Battery @Inject constructor(val logger: Logger) {
    val batteryState = 90

    fun getBatteryStatePhone(): Int{
        logger.log("$batteryState")
        return batteryState
    }
}