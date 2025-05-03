package com.guilherme.furiatalk

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform