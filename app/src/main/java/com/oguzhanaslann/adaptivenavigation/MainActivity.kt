package com.oguzhanaslann.adaptivenavigation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.adaptive.navigationsuite.NavigationSuiteScaffold
import androidx.compose.material3.adaptive.navigationsuite.NavigationSuiteScope
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.PreviewScreenSizes
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.base.MainScreen
import com.base.composable
import com.oguzhanaslann.adaptivenavigation.ui.theme.AdaptiveNavigationTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AdaptiveNavigationTheme {
                MainScreenView()
            }
        }
    }
}

@Composable
fun MainScreenView(
    modifier: Modifier = Modifier
) {
    val navHost = rememberNavController()
    val navBackStackEntry by navHost.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination
    NavigationSuiteScaffold(
        modifier = modifier.fillMaxSize(),
        navigationSuiteItems = navigationSuiteItems(currentDestination, navHost)
    ) {
        NavHost(
            modifier = Modifier.fillMaxSize(),
            navController = navHost,
            startDestination = MainScreen.AccountScreen.route
        ) {
            composable(screen = MainScreen.AccountScreen) {
                AccountScreen(
                    onDetailClicked = {
                        navHost.navigate(MainScreen.AccountDetailScreen.route)
                    }
                )
            }

            composable(screen = MainScreen.AccountDetailScreen) {
                AccountScreenDetail()
            }

            composable(screen = MainScreen.SearchScreen) {
                SearchScreen(
                    onDetailClicked = {
                        navHost.navigate(MainScreen.SearchDetailScreen.route)
                    }
                )
            }

            composable(screen = MainScreen.SearchDetailScreen) {
                SearchScreenDetail()
            }
        }
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

@PreviewScreenSizes
@Composable
fun previewMainScreen() {
    MainScreenView()
}