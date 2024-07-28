package com.stoyanvuchev.systemuibarstweaker.demo.presentation.common

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

/**
 * A helper function for creating a [ViewModel] factory.
 *
 * @param initializer takes the provided [ViewModel] to create an instance.
 *
 * @see <a href="https://github.com/philipplackner/ManualDependencyInjection/blob/master/app/src/main/java/com/plcoding/manualdependencyinjection/presentation/ViewModelFactoryHelper.kt">Original code on Philipp Lackner's repo.</a>
 **/
@Suppress("UNCHECKED_CAST")
fun <VM : ViewModel> viewModelFactory(initializer: () -> VM): ViewModelProvider.Factory {
    return object : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return initializer() as T
        }
    }
}