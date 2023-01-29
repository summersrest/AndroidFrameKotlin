package com.sum.framekt.base.utils

import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import java.util.concurrent.TimeUnit

/**
 * @author  liujiang
 * created  at: 2023/1/20 15:08
 * Desc:
 */
class RxTimerUtil {
    companion object {
        private var mDisposable: Disposable? = null

        /**
         * milliseconds毫秒后执行next操作
         *
         * @param milliseconds
         * @param next
         */
        fun timer(milliseconds: Long, next: () -> Unit) {
            Observable.timer(milliseconds, TimeUnit.MILLISECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : Observer<Long> {
                    override fun onSubscribe(disposable: Disposable) {
                        mDisposable = disposable
                    }

                    override fun onNext(number: Long) {
                        next()
                    }

                    override fun onError(e: Throwable) {
                        //取消订阅
                        cancel()
                    }

                    override fun onComplete() {
                        //取消订阅
                        cancel()
                    }
                })
        }


        /**
         * 每隔milliseconds毫秒后执行next操作
         *
         * @param milliseconds
         * @param next
         */
        fun interval(milliseconds: Long, next: (Int) -> Unit) {
            Observable.interval(milliseconds, TimeUnit.MILLISECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : Observer<Long> {
                    override fun onSubscribe(disposable: Disposable) {
                        mDisposable = disposable
                    }

                    override fun onNext(number: Long) {
                        next(number.toInt())
                    }

                    override fun onError(e: Throwable) {}
                    override fun onComplete() {}
                })
        }


        /**
         * 取消订阅
         */
        fun cancel() {
            mDisposable?.takeUnless {
                it.isDisposed
            }?.dispose()
        }
    }
}

