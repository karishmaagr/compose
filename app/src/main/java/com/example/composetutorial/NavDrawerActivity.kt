package com.example.composetutorial

import ComposeTutorialTheme
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.core.view.WindowCompat
import androidx.lifecycle.compose.collectAsStateWithLifecycle

class NavDrawerActivity : ComponentActivity() {
    private val viewModel: DrawerViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)

        setContent {
            val drawerState = rememberDrawerState(initialValue = DrawerValue.Open)
            val drawerOpen by viewModel.drawerShouldBeOpened
                .collectAsStateWithLifecycle()

            if (drawerOpen) {
                // Open drawer and reset state in VM.
                LaunchedEffect(Unit) {
                    // wrap in try-finally to handle interruption whiles opening drawer
                    try {
                        drawerState.open()
                    } finally {
                        viewModel.resetOpenDrawerAction()
                    }
                }
            }
            ComposeTutorialTheme {

            }

        }
    }
}