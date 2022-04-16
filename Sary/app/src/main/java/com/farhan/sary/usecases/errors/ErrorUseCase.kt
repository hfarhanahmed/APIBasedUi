package com.farhan.sary.usecases.errors

import com.farhan.sary.data.error.Error

interface ErrorUseCase {
    fun getError(errorCode: Int): Error
}
