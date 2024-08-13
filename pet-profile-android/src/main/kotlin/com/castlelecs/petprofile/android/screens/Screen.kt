package com.castlelecs.petprofile.android.screens

import androidx.annotation.StringRes
import com.castlelecs.petprofile.android.R

enum class Screen(@StringRes val topBarTitle: Int) {
    PETS_LIST(R.string.app_name),
    PROFILE(R.string.profile),
    CREATE_PROFILE(R.string.profile_creation);

    val route: String = name

    companion object {
        fun fromRoute(route: String?): Screen? {
            return if (route != null) {
                Screen.valueOf(route)
            } else {
                null
            }
        }

        fun fromRouteOrDefault(route: String?): Screen {
            return fromRoute(route) ?: PETS_LIST
        }
    }
}
