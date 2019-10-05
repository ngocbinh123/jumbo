package com.nnbinh.jumbo.helpers

import com.nnbinh.jumbo.event.Command
import retrofit2.HttpException
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ErrorParserHelper @Inject constructor() {
  fun parse(e: Throwable): Command {
    return when (e) {
      is HttpException -> Command.Snack(e.message())
      else -> Command.Snack(e.localizedMessage)
    }
  }
}