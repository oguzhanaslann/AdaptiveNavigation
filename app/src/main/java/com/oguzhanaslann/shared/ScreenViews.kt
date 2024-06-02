package com.oguzhanaslann.shared

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
fun AccountScreen(
    onDetailClicked : () -> Unit = {}
) {
    Surface {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Button(onClick = onDetailClicked) {
                Text("Account Screen")
            }

        }
    }
}

@Composable
fun AccountScreenDetail() {
    Surface {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Text("Account Detail Screen")
        }
    }
}

@Composable
fun SearchScreen(
    onDetailClicked : () -> Unit = {}
) {
    Surface {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Button(onClick = onDetailClicked) {
                Text("Search Screen")
            }
        }
    }
}

@Composable
fun SearchScreenDetail() {
    Surface {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Text("Search Detail Screen")
        }
    }
}
