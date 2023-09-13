package com.stoyanvuchev.systemuibarstweaker.demo.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.stoyanvuchev.systemuibarstweaker.demo.presentation.ui.theme.ApplicationTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ApplicationTheme {

                /* To be implemented. */

            }
        }

    }

}