package com.deservebetter.ydbandroid.navigation

object AdminRoute {
    const val OVERVIEW = "admin/overview"
    const val PRODUCTS = "admin/products"
    const val ADD_PRODUCT = "admin/products/add"
    const val BLOG_POSTS = "admin/blog-posts"
    const val CREATE_BLOG_POST = "admin/blog-posts/create"
    const val USER_MANAGEMENT = "admin/user-management"
    const val RESEARCH_PAPERS = "admin/research-papers"
    const val SCIENCE_CONTENT = "admin/science-content"
    const val NEWSLETTER = "admin/newsletter"
    const val MODERATION = "admin/moderation"
    const val ANALYTICS = "admin/analytics"
}

sealed class AdminScreen(val route: String) {
    object Overview : AdminScreen(AdminRoute.OVERVIEW)
    object Products : AdminScreen(AdminRoute.PRODUCTS)
    object AddProduct : AdminScreen(AdminRoute.ADD_PRODUCT)
    object BlogPosts : AdminScreen(AdminRoute.BLOG_POSTS)
    object CreateBlogPost : AdminScreen(AdminRoute.CREATE_BLOG_POST)
    object UserManagement : AdminScreen(AdminRoute.USER_MANAGEMENT)
    object ResearchPapers : AdminScreen(AdminRoute.RESEARCH_PAPERS)
    object ScienceContent : AdminScreen(AdminRoute.SCIENCE_CONTENT)
    object Newsletter : AdminScreen(AdminRoute.NEWSLETTER)
    object Moderation : AdminScreen(AdminRoute.MODERATION)
    object Analytics : AdminScreen(AdminRoute.ANALYTICS)
}