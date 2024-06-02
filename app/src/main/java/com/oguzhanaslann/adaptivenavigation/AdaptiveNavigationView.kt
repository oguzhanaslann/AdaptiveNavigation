package com.oguzhanaslann.adaptivenavigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.adaptive.navigationsuite.NavigationSuiteScaffold
import androidx.compose.material3.adaptive.navigationsuite.NavigationSuiteScope
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.PreviewScreenSizes
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.oguzhanaslann.shared.MainScreenNavigationHost
import com.oguzhanaslann.shared.MainScreen

@Composable
fun AdaptiveNavigationView(
    modifier: Modifier = Modifier
) {
    val navHost = rememberNavController()
    val navBackStackEntry by navHost.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination
    NavigationSuiteScaffold(
        modifier = modifier.fillMaxSize(),
        navigationSuiteItems = navigationSuiteItems(currentDestination, navHost)
    ) {
        MainScreenNavigationHost(navHost = navHost)
    }
}



@Composable
private fun navigationSuiteItems(
    currentDestination: NavDestination?, navHost: NavHostController
): NavigationSuiteScope.() -> Unit = {
    MainScreen.navigationScreens()
        .forEach {
            item(
                selected = currentDestination?.hierarchy?.any { it.route == it.route } == true,
                onClick = {
                    val screen = it
                    navHost.navigate(screen.route) {
                        popUpTo(navHost.graph.findStartDestination().id) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }

                },
                label = { Text(it.label) },
                icon = {
                    Icon(
                        imageVector = it.icon, contentDescription = it.contentDescription
                    )
                },
                alwaysShowLabel = true,
            )
        }
}

@PreviewScreenSizes
@Composable
fun previewMainScreen() {
    AdaptiveNavigationView()
}
