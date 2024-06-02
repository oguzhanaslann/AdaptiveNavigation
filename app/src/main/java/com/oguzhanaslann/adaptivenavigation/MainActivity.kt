package com.oguzhanaslann.adaptivenavigation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.adaptive.navigationsuite.NavigationSuiteScaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.PreviewScreenSizes
import com.oguzhanaslann.adaptivenavigation.ui.theme.AdaptiveNavigationTheme

enum class Destination(
    val label: String, val icon: ImageVector, val contentDescription: String? = null
) {
    Account("Account", Icons.Filled.AccountCircle), Search("Search", Icons.Filled.Search);

    companion object {
        val all = values()
    }
}

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AdaptiveNavigationTheme {
                MainScreen()
            }
        }
    }
}

@Composable
fun MainScreen(
    modifier: Modifier = Modifier
) {
    val selectedItem = remember { mutableStateOf(Destination.Account) }
    NavigationSuiteScaffold(navigationSuiteItems = {
        Destination.all.forEach {
            item(
                selected = selectedItem.value == it,
                onClick = { selectedItem.value = it },
                label = { Text(it.label) },
                icon = {
                    Icon(
                        imageVector = it.icon, contentDescription = it.contentDescription
                    )
                },
                alwaysShowLabel = true,
            )
        }
    }) {
        Box(
            modifier = Modifier.fillMaxSize()
        ) {
            Surface(
                modifier = Modifier
                    .align(Alignment.Center)
            ) {
                when (selectedItem.value) {
                    Destination.Account -> Text("Account")
                    Destination.Search -> Text("Search")
                }

            }
        }
    }
}

@PreviewScreenSizes
@Composable
fun previewMainScreen() {
    MainScreen()
}