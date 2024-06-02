package com.oguzhanaslann.adaptiveOld

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationRail
import androidx.compose.material3.NavigationRailItem
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun NavigationRailScaffold(
    railContent: @Composable ColumnScope.() -> Unit = {},
    content: @Composable ColumnScope.() -> Unit
) {
   Surface {
       Row(
           modifier = Modifier.fillMaxSize(),
           horizontalArrangement = Arrangement.Start
       ) {
           NavigationRail(content = railContent)

           Column(
               modifier = Modifier
                   .weight(1f)
                   .fillMaxHeight(),
                content = content
           )
       }
   }
}

@Preview
@Composable
fun previewRAil() {
    NavigationRailScaffold(
        railContent = {
            NavigationRailItem(
                icon = { Icon(
                    imageVector = Icons.Filled.Home,
                    contentDescription = "Home"
                ) },
                selected = true,
                onClick = {  }
            )

            NavigationRailItem(
                icon = { Icon(
                    imageVector = Icons.Filled.Home,
                    contentDescription = "Home"
                ) },
                selected = false,
                onClick = {  }
            )

            NavigationRailItem(
                icon = { Icon(
                    imageVector = Icons.Filled.Home,
                    contentDescription = "Home"
                ) },
                selected = false,
                onClick = {  }
            )
        }
    ) {

    }
}