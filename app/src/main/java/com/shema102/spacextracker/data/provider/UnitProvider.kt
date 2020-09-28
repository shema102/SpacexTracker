package com.shema102.spacextracker.data.provider

import com.shema102.spacextracker.internal.UnitSystem

interface UnitProvider {
    fun getUnitSystem(): UnitSystem
}