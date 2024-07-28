package com.stoyanvuchev.systemuibarstweaker.demo.domain.manual_di

import com.stoyanvuchev.systemuibarstweaker.demo.domain.preferences.AppPreferences

/**
 * An abstraction of an Application module for a manual dependency injection.
 *
 * @property appPreferences the application preferences instance.
 *
 * @see <a href="https://github.com/philipplackner/ManualDependencyInjection/blob/master/app/src/main/java/com/plcoding/manualdependencyinjection/di/AppModule.kt">Original code on Philipp Lackner's repo.</a>
 **/
interface AppModule {
    val appPreferences: AppPreferences
}