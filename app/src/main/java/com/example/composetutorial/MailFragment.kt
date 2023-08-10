package com.example.composetutorial

import ComposeTutorialTheme
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.MoreVert
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels

class MailFragment : Fragment() {
    private val activityViewModel: DrawerViewModel by activityViewModels()

    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        val rootView: View = inflater.inflate(R.layout.fragment_profile, container, false)
        rootView.findViewById<ComposeView>(R.id.toolbar_compose_view).apply {
            setContent {
                ComposeTutorialTheme {
                    Spacer(modifier = Modifier.height(42.dp))
                    ComposeAppBar(
                        // Reset the minimum bounds that are passed to the root of a compose tree
                        modifier = Modifier.wrapContentSize(),
                        onNavIconPressed = { activityViewModel.openDrawer() },
                        title = {
                            Column(horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center) {
                                // Channel name
                                Text(
                                    text = "Compose Tutorial",
                                    style = MaterialTheme.typography.titleMedium
                                )
                            }
                        },
                        actions = {
                            // More icon
                            Icon(
                                imageVector = Icons.Outlined.MoreVert,
                                tint = MaterialTheme.colorScheme.onSurfaceVariant,
                                modifier = Modifier
                                    .clickable(onClick = {
                                    })
                                    .padding(horizontal = 12.dp, vertical = 16.dp)
                                    .height(24.dp),
                                contentDescription = "More Options"
                            )
                        }
                    )
                }
            }
        }

        rootView.findViewById<ComposeView>(R.id.profile_compose_view).apply {
            setContent {
                ComposeTutorialTheme {
                    FunctionalityNotAvailablePanel()
                }
            }
        }
        return rootView

    }
}