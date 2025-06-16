package com.example.studyingproject

import javax.inject.Inject

class Phone @Inject constructor(val battery: Battery, val logger: Logger) {
    fun checkState() {
        if (battery.getBatteryStatePhone() < 20) {
            logger.log("Low battery!")
        }
    }
}