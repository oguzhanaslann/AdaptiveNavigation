package com.oguzhanaslann.adaptiveOld

import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationRailItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.oguzhanaslann.shared.MainScreen
import com.oguzhanaslann.shared.MainScreenNavigationHost

@Composable
fun OldAdaptiveNavigationView() {

    val navHost = rememberNavController()
    val navBackStackEntry by navHost.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    BoxWithConstraints(
        modifier = Modifier.fillMaxSize()
    ) {
        val minWidth = constraints.minWidth.dp
        val minHeight = constraints.minHeight.dp

        if (minWidth >= 600.dp || minHeight >= 900.dp) {
            NavigationRailScaffold(
                railContent = navigationRailContent(currentDestination, navHost)
            ) {
                MainScreenNavigationHost(
                    modifier = Modifier.fillMaxSize(), navHost = navHost
                )
            }
        } else {
            Scaffold(modifier = Modifier.fillMaxSize(), bottomBar = {
                NavigationBarView(currentDestination, navHost)
            }) {
                MainScreenNavigationHost(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(it), navHost = navHost
                )
            }
        }

    }
}

@Composable
private fun NavigationBarView(
    currentDestination: NavDestination?, navHost: NavHostController
) {
    NavigationBar {
        MainScreen.navigationScreens()
            .forEach { screen ->
                NavigationBarItem(
                    selected = currentDestination?.hierarchy?.any { it.route == it.route } == true,
                    onClick = {
                        navHost.navigate(screen.route) {
                            popUpTo(navHost.graph.findStartDestination().id) {
                                saveState = true
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    },
                    icon = {
                        Icon(
                            imageVector = screen.icon,
                            contentDescription = screen.contentDescription
                        )
                    },
                    label = { Text(screen.label) },
                    alwaysShowLabel = true,
                )
            }
    }
}

@Composable
private fun navigationRailContent(
    currentDestination: NavDestination?, navHost: NavHostController
): @Composable() (ColumnScope.() -> Unit) = {
    MainScreen.navigationScreens()
        .forEach { screen ->
            NavigationRailItem(selected = currentDestination?.hierarchy?.any { it.route == it.route } == true,
                onClick = {
                    navHost.navigate(screen.route) {
                        popUpTo(navHost.graph.findStartDestination().id) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                },
                icon = {
                    Icon(
                        imageVector = screen.icon, contentDescription = screen.contentDescription
                    )
                },
                label = { Text(screen.label) })
        }
}

@Preview
@Composable
fun previewOldAdaptiveNavigationView() {
    OldAdaptiveNavigationView()
}