package com.github.xnovo3000.gigapass.core.algorithm.obscurer

import java.util.regex.Pattern
import javax.inject.Inject

class StringObscurerAndroid @Inject constructor() : StringObscurer {

    private val emailPattern = Pattern.compile("^[a-zA-Z0-9-.]+@[a-zA-Z0-9.]+\$")

    override fun obscure(value: String): String {
        val usernameStrategy = ObscurationStrategyUsername()
        val finalStrategy = when (emailPattern.matcher(value).matches()) {
            true -> ObscurationStrategyEmail(stringObscurerUsername = usernameStrategy)
            else -> usernameStrategy
        }
        return finalStrategy(value)
    }

}