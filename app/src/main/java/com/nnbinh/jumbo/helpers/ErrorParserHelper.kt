package com.nnbinh.jumbo.helpers

import com.nnbinh.jumbo.api.CustomException
import com.nnbinh.jumbo.event.Command
import okhttp3.ResponseBody
import org.json.JSONObject
import retrofit2.HttpException
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ErrorParserHelper @Inject constructor() {

  @Throws(CustomException::class)
  fun parseResponse(response: ResponseBody) : String {
    val jsonStr = response.string()
    val jObj = JSONObject(jsonStr)
    if (jObj.has("error")) throw CustomException(jsonStr)
    else if (jObj.has("ErrorMessage")) {
      val message = jObj.getString("ErrorMessage")
      if (message.isNotEmpty()) {
        throw CustomException(message)
      }
    }
    return jsonStr
  }

  fun parse(e: Throwable): Command {
    return when (e) {
      is HttpException -> Command.Snack(e.message())
      else -> Command.Snack(e.localizedMessage)
    }
  }
}