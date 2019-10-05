package com.nnbinh.jumbo.repo

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.nnbinh.jumbo.helpers.LogHelper
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.PublishSubject
import java.util.LinkedList
import java.util.Queue
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MissionRepo @Inject constructor(){
  @Inject
  lateinit var logHelper: LogHelper

  private val isRunning = MutableLiveData<Boolean>(false)
  fun isRunning() : LiveData<Boolean> = isRunning

  val missionQueue: Queue<Runnable> = LinkedList()
  var psRoot: PublishSubject<Runnable>? = null

  private fun subscribe(mission: Runnable) {
    psRoot = PublishSubject.create<Runnable>()
    psRoot!!.startWith(mission)
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .doOnSubscribe { logHelper.v("Mission Subscriber OnSubcribe") }
        .doOnComplete { logHelper.v("Mission Subscriber OnComplete") }
        .subscribe({ mission ->
          mission.run()
        }, { error ->
          logHelper.v("Mission Subscriber Error")
          logHelper.e(error)
          psRoot?.onComplete()
        }, { logHelper.v("Mission Subscriber Complete") })
  }

  fun onClear() {
    psRoot?.onComplete()
    psRoot = null
    missionQueue.clear()
    isRunning.value = false
  }

  fun clearAllMissions() = missionQueue.clear()
  fun pushMission(mission: Runnable) {
    missionQueue.add(mission)
    if (!isRunning.value!!) doNextMission()
  }
  fun doNextMission() {
    if (missionQueue.isNotEmpty()) {
      isRunning.value = true
      if (psRoot == null || psRoot!!.hasComplete()) {
        subscribe(missionQueue.remove())
      } else {
        psRoot!!.onNext(missionQueue.remove())
      }
    } else {
      isRunning.value = false
    }
  }
}