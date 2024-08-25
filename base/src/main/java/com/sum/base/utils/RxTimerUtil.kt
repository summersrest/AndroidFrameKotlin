package com.sum.base.utils


import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Observer
import io.reactivex.rxjava3.disposables.Disposable
import java.util.concurrent.TimeUnit

/**
 * @author  LiuJiang
 * Desc:    倒计时工具类
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

