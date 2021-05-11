package com.healthyryu.networktest

import android.util.Log
import androidx.lifecycle.ViewModel
import com.healthyryu.networktest.network.Network
import com.healthyryu.networktest.repo.ApiRepository
import com.healthyryu.networktest.utils.PROJECT_ID
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: ApiRepository,
    private val network: Network
) : ViewModel() {

    private val compositeDisposable = CompositeDisposable()

    fun getData() {
        if (network.isNetworkConnected()) {
            repository.getInfo(PROJECT_ID)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    if (it.isSuccessful) {
                        it.body()?.let { info ->
                            Log.d("MainViewModel", ">> $info")
                        }
                    } else {
                        // TODO : 응답이 없을때
                    }
                }, {
                    // TODO : 예외 처리
                }).addTo(compositeDisposable)
        } else {
            Log.d("MainViewModel", "No internet connection")
        }
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }
}