package com.github.xnovo3000.gigapass.core.algorithm.obscurer

internal class ObscurationStrategyEmail(
    private val stringObscurerUsername: ObscurationStrategyUsername
) : ObscurationStrategy {

    override fun invoke(principal: String): String {
        // Strategy: add stars at the end of username and in any part of the host
        val v1 = principal.split("@")
        val obscuredUsername = stringObscurerUsername.invoke(v1[0])
        val url = v1[1].split(".")
            .joinToString(".") { stringObscurerUsername.invoke(it) }
        return "$obscuredUsername@$url"
    }

}