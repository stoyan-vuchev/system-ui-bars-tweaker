package com.stoyanvuchev.systemuibarstweaker

import android.view.Window
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.core.view.WindowInsetsControllerCompat
import androidx.test.ext.junit.runners.AndroidJUnit4
import assertk.assertThat
import assertk.assertions.isEqualTo
import assertk.assertions.isFalse
import assertk.assertions.isTrue
import com.stoyanvuchev.systemuibarstweaker.utils.WindowUtils
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.Job
import kotlinx.coroutines.test.TestCoroutineScheduler
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.withContext
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import kotlin.coroutines.CoroutineContext

@OptIn(ExperimentalCoroutinesApi::class)
@RunWith(AndroidJUnit4::class)
class SystemUIBarsTweakerImplTest {

    @get:Rule
    val composeRule = createComposeRule()

    private lateinit var coroutineScheduler: CoroutineContext
    private lateinit var tweaker: SystemUIBarsTweaker
    private var window: Window? = null

    @Before
    fun setUp() {
        coroutineScheduler = TestCoroutineScheduler() + Job()
        composeRule.setContent {
            val view = LocalView.current
            window = WindowUtils.findWindow(view)
            tweaker = rememberSystemUIBarsTweaker(view, window)
        }
    }

    @Test
    fun assertThatEdgeToEdgeIsEnabled() = runTest(coroutineScheduler) {
        withContext(Dispatchers.Main) {
            composeRule.awaitIdle()
            tweaker.enableEdgeToEdge(true)
            composeRule.awaitIdle()
            window?.let {
                assertThat(it.decorView.fitsSystemWindows).isFalse()
            }
        }
    }

    @Test
    fun assertThatEdgeToEdgeIsDisabled() = runTest(coroutineScheduler) {
        withContext(Dispatchers.Main) {
            composeRule.awaitIdle()
            tweaker.enableEdgeToEdge(false)
            composeRule.awaitIdle()
            window?.let {
                assertThat(it.decorView.fitsSystemWindows).isFalse()
            }
        }
    }

    @Test
    fun assertThatStatusBarColorMatchesTheGivenColor() = runTest(coroutineScheduler) {
        withContext(Dispatchers.Main) {
            val color = Color.Blue
            composeRule.awaitIdle()
            tweaker.tweakStatusBarStyle(tweaker.statusBarStyle.copy(color = color))
            window?.let {
                assertThat(Color(it.statusBarColor)).isEqualTo(color)
            }
        }
    }

    @Test
    fun assertThatNavigationBarColorMatchesTheGivenColor() = runTest(coroutineScheduler) {
        withContext(Dispatchers.Main) {
            val color = Color.Blue
            composeRule.awaitIdle()
            tweaker.tweakNavigationBarStyle(tweaker.navigationBarStyle.copy(color = color))
            window?.let {
                assertThat(Color(it.navigationBarColor)).isEqualTo(color)
            }
        }
    }

    @Test
    fun assertThatBothSystemBarsColorMatchesTheGivenColor() = runTest(coroutineScheduler) {
        withContext(Dispatchers.Main) {
            val color = Color.Blue
            composeRule.awaitIdle()
            tweaker.tweakSystemBarsStyle(
                statusBarStyle = tweaker.statusBarStyle.copy(color = color),
                navigationBarStyle = tweaker.navigationBarStyle.copy(color = color)
            )
            window?.let {
                assertThat(Color(it.statusBarColor)).isEqualTo(color)
                assertThat(Color(it.navigationBarColor)).isEqualTo(color)
            }
        }
    }

    @Test
    fun assertThatStatusBarIsVisible() = runTest(coroutineScheduler) {
        withContext(Dispatchers.Main) {
            composeRule.awaitIdle()
            tweaker.tweakStatusBarVisibility(true)
            composeRule.awaitIdle()
            window?.let {
                assertThat(tweaker.isStatusBarVisible).isTrue()
            }
        }
    }

    @Test
    fun assertThatStatusBarIsNotVisible() = runTest(coroutineScheduler) {
        withContext(Dispatchers.Main) {
            composeRule.awaitIdle()
            tweaker.tweakStatusBarVisibility(false)
            composeRule.awaitIdle()
            window?.let {
                assertThat(tweaker.isStatusBarVisible).isFalse()
            }
        }
    }

    @Test
    fun assertThatNavigationBarIsVisible() = runTest(coroutineScheduler) {
        withContext(Dispatchers.Main) {
            composeRule.awaitIdle()
            tweaker.tweakNavigationBarVisibility(true)
            composeRule.awaitIdle()
            window?.let {
                assertThat(tweaker.isNavigationBarVisible).isTrue()
            }
        }
    }

    @Test
    fun assertThatNavigationBarIsNotVisible() = runTest(coroutineScheduler) {
        withContext(Dispatchers.Main) {
            composeRule.awaitIdle()
            tweaker.tweakNavigationBarVisibility(false)
            composeRule.awaitIdle()
            window?.let {
                assertThat(tweaker.isNavigationBarVisible).isFalse()
            }
        }
    }

    @Test
    fun assertThatSystemBarsBehaviorIsDefault() = runTest(coroutineScheduler) {
        withContext(Dispatchers.Main) {
            composeRule.awaitIdle()
            val behavior = WindowInsetsControllerCompat.BEHAVIOR_DEFAULT
            tweaker.tweakSystemBarsBehavior(behavior)
            composeRule.awaitIdle()
            window?.let {
                assertThat(tweaker.systemBarsBehavior).isEqualTo(behavior)
            }
        }
    }

    @Test
    fun assertThatSystemBarsBehaviorIsShowBySwipe() = runTest(coroutineScheduler) {
        withContext(Dispatchers.Main) {
            composeRule.awaitIdle()
            val behavior = WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
            tweaker.tweakSystemBarsBehavior(behavior)
            composeRule.awaitIdle()
            window?.let {
                assertThat(tweaker.systemBarsBehavior).isEqualTo(behavior)
            }
        }
    }

}