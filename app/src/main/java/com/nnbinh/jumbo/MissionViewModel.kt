package com.nnbinh.jumbo

import androidx.lifecycle.ViewModel
import com.nnbinh.jumbo.event.Command
import com.nnbinh.jumbo.event.SingleLiveEvent
import com.nnbinh.jumbo.helpers.ErrorParserHelper
import com.nnbinh.jumbo.helpers.LogHelper
import com.nnbinh.jumbo.repo.MissionRepo
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

abstract class MissionViewModel : ViewModel() {
  @Inject lateinit var logHelper: LogHelper
  @Inject lateinit var errorHelper: ErrorParserHelper
  @Inject lateinit var missionRepo: MissionRepo

  val command: SingleLiveEvent<Command> = SingleLiveEvent() //has only 1 observer
  val rxDispose = CompositeDisposable()

  override fun onCleared() {
    rxDispose.clear()
    super.onCleared()
  }
}