package com.stoyanvuchev.systemuibarstweaker.demo

import android.app.Application
import com.stoyanvuchev.systemuibarstweaker.demo.domain.manual_di.AppModule
import com.stoyanvuchev.systemuibarstweaker.demo.manual_di.AppModuleImpl

class SystemUIBarsTweakerDemoApp : Application() {

    companion object {

        /**
         * The application module property of the manual dependency injection.
         *
         * @see <a href="https://github.com/philipplackner/ManualDependencyInjection/blob/master/app/src/main/java/com/plcoding/manualdependencyinjection/MyApp.kt">Original code on Philipp Lackner's repo.</a>
         **/
        lateinit var appModule: AppModule

    }

    override fun onCreate() {
        super.onCreate()
        appModule = AppModuleImpl(this)
    }

}