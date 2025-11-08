package com.poc.core.domain.utils

import kotlin.random.Random

fun generateReceipt(): Int {
    return Random.nextInt(100, 999)

}
