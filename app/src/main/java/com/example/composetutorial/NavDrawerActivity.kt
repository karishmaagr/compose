package com.example.composetutorial

import android.os.Bundle
import androidx.activity.compose.BackHandler
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.viewinterop.AndroidViewBinding
import androidx.core.view.WindowCompat
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.example.composetutorial.databinding.ContentMainBinding
import kotlinx.coroutines.launch


class NavDrawerActivity : AppCompatActivity() {
    private val viewModel: DrawerViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)
        setContentView(ComposeView(this).apply {
            consumeWindowInsets = false
            setContent {
                val drawerState = rememberDrawerState(initialValue = DrawerValue.Open)
                val drawerOpen by viewModel.drawerShouldBeOpened.collectAsStateWithLifecycle()

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
                val scope = rememberCoroutineScope()
                if (drawerState.isOpen) {
                    BackHandler {
                        scope.launch {
                            drawerState.close()
                        }
                    }
                }

                SampleDrawer(drawerState = drawerState, onItemClicked = {
                    //Mov to selected fragment
                    if (it == "inbox") {
                        findNavController().popBackStack(R.id.nav_home, false)
                    }
                    scope.launch {
                        drawerState.close()
                    }
                }, onConversationCLick = {
                    findNavController().navigate(R.id.nav_conversation)
                    scope.launch {
                        drawerState.close()
                    }
                }, content = { AndroidViewBinding(ContentMainBinding::inflate) })

            }
        })
    }

    private fun findNavController(): NavController {
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        return navHostFragment.navController
    }
}

