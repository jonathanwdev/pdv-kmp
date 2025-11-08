package com.poc.pocpdv

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform