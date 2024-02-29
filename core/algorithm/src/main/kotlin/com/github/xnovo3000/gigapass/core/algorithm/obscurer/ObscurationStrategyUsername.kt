package com.github.xnovo3000.gigapass.core.algorithm.obscurer

internal class ObscurationStrategyUsername : ObscurationStrategy {

    override fun invoke(principal: String): String {
        // Strategy: add stars at the end
        return when (principal.length) {
            in 0 until 4 -> principal
            in 4 until 7 -> "${principal.take(principal.length - 2)}**"
            else -> "${principal.take(principal.length - 4)}****"
        }
    }

}