package com.example.common.sharepreference

object SharedPreferenceConst {
    const val LOGIN_MODE = "login_mode"

    object LoginModeValue {
        const val USER_LOGIN = 1
        const val MANAGER_LOGIN = 2
    }

    const val LOGIN_STATE = "login_state"
    object LoginStateValue {
        const val NO_LOGIN = 0
        const val HAS_LOGIN = 1
    }

    const val REGISTER_STATE = "register_state"
    object RegisterStateValue {
        const val NO_REGISTER = 0
        const val HAS_REGISTER = 1
    }

    const val GLIDE_SIGN = "glide_sign"
}