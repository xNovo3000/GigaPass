package com.github.xnovo3000.gigapass.core.crypto

class CryptoManagerException(
    override val message: String?,
    override val cause: Throwable?
) : Exception() {

    companion object {

        fun <T> failureResult(message: String?, cause: Throwable?): Result<T> {
            return Result.failure(CryptoManagerException(message, cause))
        }

    }

}