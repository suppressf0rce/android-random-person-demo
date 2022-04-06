package net.decodex.demo

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

/**
 * This is the main application class which extends android native [Application]
 * Used to annotate as [HiltAndroidApp] so it will have necessary Dagger / Hilt stuff
 * @author Dejan Radmanovic
 * @since 1.0.0
 */
@HiltAndroidApp
class UpworkApplication : Application()