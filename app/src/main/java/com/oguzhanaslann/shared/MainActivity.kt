package com.oguzhanaslann.shared

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.oguzhanaslann.adaptivenavigation.AdaptiveNavigationView
import com.oguzhanaslann.shared.ui.theme.AdaptiveNavigationTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AdaptiveNavigationTheme {
                AdaptiveNavigationView()
            }
        }
    }
}
