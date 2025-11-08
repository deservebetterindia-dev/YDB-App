package com.deservebetter.ydbandroid.navigation

object Route {
    const val HOME = "home"
    const val PRODUCT_DETAIL = "product/{id}"
    const val CATEGORY = "category/{id}"
    const val CART = "cart"
    const val SHOP = "shop"
    const val CONSULT = "consult"
    const val FORUM = "forum"
    const val PROFILE = "profile"
    const val ADMIN_PORTAL = "admin"
}

sealed class Screen(val route: String) {
    object Home : Screen(Route.HOME)
    object ProductDetail : Screen(Route.PRODUCT_DETAIL) {
        fun createRoute(id: String) = "product/$id"
    }
    object Category : Screen(Route.CATEGORY) {
        fun createRoute(id: String) = "category/$id"
    }
    object Cart : Screen(Route.CART)
    object Shop : Screen(Route.SHOP)
    object Consult : Screen(Route.CONSULT)
    object Forum : Screen(Route.FORUM)
    object Profile : Screen(Route.PROFILE)
    object AdminPortal : Screen(Route.ADMIN_PORTAL)
}