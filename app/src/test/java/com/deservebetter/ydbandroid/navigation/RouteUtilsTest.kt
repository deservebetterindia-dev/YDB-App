package com.deservebetter.ydbandroid.navigation

import org.junit.Assert.assertEquals
import org.junit.Test

class RouteUtilsTest {

    @Test
    fun testBuildProductDetailRoute() {
        val id = "123"
        val expectedRoute = "product/123"
        assertEquals(expectedRoute, RouteUtils.buildProductDetailRoute(id))
    }

    @Test
    fun testBuildCategoryRoute() {
        val id = "abc"
        val expectedRoute = "category/abc"
        assertEquals(expectedRoute, RouteUtils.buildCategoryRoute(id))
    }

    @Test
    fun testParseId() {
        val route = "product/123"
        val expectedId = "123"
        assertEquals(expectedId, RouteUtils.parseId(route))
    }

    @Test
    fun testParseIdWithNoId() {
        val route = "product"
        assertEquals(null, RouteUtils.parseId(route))
    }
}
