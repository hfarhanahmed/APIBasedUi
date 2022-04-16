package com.farhan.sary.usecases.errors

import com.farhan.sary.data.error.Error
import com.farhan.sary.data.error.mapper.ErrorMapper
import javax.inject.Inject



class ErrorManager @Inject constructor(private val errorMapper: ErrorMapper) : ErrorUseCase {
    override fun getError(errorCode: Int): Error {
        return Error(code = errorCode, description = errorMapper.errorsMap.getValue(errorCode))
    }
}
