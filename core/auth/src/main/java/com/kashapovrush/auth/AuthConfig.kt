package com.kashapovrush.auth

import net.openid.appauth.ResponseTypeValues


object AuthConfig {

    const val AUTH_URI = "https://github.com/login/oauth/authorize"
    const val TOKEN_URI = "https://github.com/login/oauth/access_token"
    const val END_SESSION_URI = "https://github.com/logout"
    const val RESPONSE_TYPE = ResponseTypeValues.CODE
    const val SCOPE = "user,repo"

    const val CLIENT_ID = "97c50d3a5e8bed040332"
    const val CLIENT_SECRET = "1ec1ac5975c66c10b67d6910b846375515065539"
    const val CALLBACK_URL = "com.kashapovrush.searchuserstest://github.com/callback"
    const val LOGOUT_CALLBACK_URL = "com.kashapovrush.searchuserstest://github.com/logout_callback"

}
