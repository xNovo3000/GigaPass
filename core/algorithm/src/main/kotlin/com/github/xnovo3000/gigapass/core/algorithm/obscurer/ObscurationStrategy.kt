package com.github.xnovo3000.gigapass.core.algorithm.obscurer

internal interface ObscurationStrategy {
    operator fun invoke(principal: String): String
}