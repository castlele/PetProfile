package com.castlelecs.petprofile.android

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.castlelecs.petprofile.android.screens.PetsViewModel
import com.castlelecs.petprofile.android.screens.Screen
import com.castlelecs.petprofile.android.screens.petlist.PetsListView
import com.castlelecs.petprofile.android.screens.profile.ProfileView
import com.castlelecs.petprofile.android.screens.profile.ProfileViewState
import com.castlelecs.petprofile.android.views.TopBarView

@Composable
fun AppView(
    viewModel: PetsViewModel,
    navController: NavHostController = rememberNavController(),
) {
    val state = viewModel.state.collectAsState().value
    val backStackEntry by navController.currentBackStackEntryAsState()

    val isNavigationBackEnabled = navController.previousBackStackEntry != null
    val currentScreen = Screen.fromRouteOrDefault(
        route = backStackEntry?.destination?.route
    )

    Scaffold(
        topBar = {
            TopBarView(
                currentScreen = currentScreen,
                isNavigationBackEnabled = isNavigationBackEnabled,
                onBackButtonClicked = navController::navigateUp
            )
        },
        floatingActionButton = {
            if (viewModel.isFloatingButtonEnabled(currentScreen)) {
                // TODO: Move to separate view
                FloatingActionButton(
                    onClick = {
                        viewModel.floatingButtonPressed(currentScreen)?.let {
                            navController.navigate(it.route)
                        }
                    }
                ) {
                    Icon(Icons.Filled.Add, contentDescription = null)
                }
            }
        },
        floatingActionButtonPosition = FabPosition.End,
    ) { innerPadding ->
        val directionStart = AnimatedContentTransitionScope.SlideDirection.Start
        val directionEnd = AnimatedContentTransitionScope.SlideDirection.End

        NavHost(
            navController = navController,
            startDestination = Screen.PETS_LIST.route,
            enterTransition = { enterContainer(directionStart) },
            exitTransition = { exitContainer(directionStart) },
            popEnterTransition = { enterContainer(directionEnd) },
            popExitTransition = { exitContainer(directionEnd) },
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(Screen.PETS_LIST.route) {
                PetsListView(
                    pets = viewModel.pets,
                    modifier = Modifier.fillMaxSize()
                )
            }

            composable(Screen.CREATE_PROFILE.route) {
                ProfileView(
                    state = ProfileViewState.CREATING,
                    modifier = Modifier.fillMaxSize()
                )
            }
        }
    }
}

private fun AnimatedContentTransitionScope<NavBackStackEntry>.enterContainer(
    direction: AnimatedContentTransitionScope.SlideDirection,
    durationMillis: Int = 350
): EnterTransition {
    return slideIntoContainer(direction, tween(durationMillis))
}

private fun AnimatedContentTransitionScope<NavBackStackEntry>.exitContainer(
    direction: AnimatedContentTransitionScope.SlideDirection,
    durationMillis: Int = 350
): ExitTransition {
    return slideOutOfContainer(direction, tween(durationMillis))
}
