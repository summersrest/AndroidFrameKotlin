package com.sum.frame.http

import com.alibaba.fastjson2.JSON
import com.sum.frame.entity.HttpException
import com.sum.frame.http.callback.OriginCallback
import com.sum.frame.mvp.BaseActivity
import com.sum.frame.mvp.BaseFragment
import com.sum.frame.utils.L
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Observer
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers
import okhttp3.Interceptor
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody.Companion.toRequestBody
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import java.io.IOException
import java.util.concurrent.TimeUnit

/**
 * @author  LiuJiang
 * Desc:    网络请求工具类
 */
class HttpUtils {
    /**
     * OkHttpClient
     */
    private var client: OkHttpClient? = null

    /**
     * Parameter参数
     */
    private val parameter: MutableMap<String, Any?> = HashMap()

    /**
     * body参数
     */
    private var body: Any? = null

    /**
     * tag
     */
    private var tag: Any? = null

    /**
     * 网络请求完整的Url
     */
    private var url = ""


    companion object {
        private var httpBuilder: HttpBuilder? = null

        /**
         * 单例
         */
        val instance: HttpUtils by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED) { HttpUtils() }

        /**
         * HttpUtils实例构造器
         */
        class HttpBuilder {
            /**
             * BaseUrl
             */
            var baseUrl = ""

            /**
             * 拦截器
             */
            val interceptors = mutableListOf<Interceptor>()

            /**
             * 日志打印拦截器
             */
            var printerInterceptor: Interceptor? = null

            /**
             * 是否打印Http请求日志，若SFrame设置不打印，则此值无效。
             */
            var showLog = true

            /**
             * Http日志tag，若未设置，则使用SFrame的tag
             */
            var tag: String? = null

            /**
             * 超时时间
             */
            var connectTimeout = 1000 * 10L

            /**
             * 超时时间
             */
            var readTimeout = 1000 * 10L

            /**
             * 超时时间
             */
            var writeTimeout = 1000 * 10L


            fun baseUrl(baseUrl: String): HttpBuilder {
                this.baseUrl = baseUrl
                return this
            }

            fun interceptor(interceptor: Interceptor): HttpBuilder {
                interceptors.add(interceptor)
                return this
            }

            /**
             * 是否打印Http请求日志，若SFrame设置不打印，则此值无效。
             * 仅默认日志打印拦截器生效
             */
            fun showLog(showLog: Boolean): HttpBuilder {
                this.showLog = showLog
                return this
            }

            /**
             * Http日志tag，若未设置，则使用SFrame的tag
             * 仅默认日志打印拦截器生效
             */
            fun tag(tag: String?): HttpBuilder {
                this.tag = tag
                return this
            }

            /**
             * 自定义日志打印拦截器
             * 若未传入则使用默认的日志打印拦截器，showLog属性、tag属性生效
             * 若传入则使用自定义日志打印拦截器，showLog属性、tag属性设置后无效
             */
            fun printerInterceptor(printerInterceptor: Interceptor): HttpBuilder {
                this.printerInterceptor = printerInterceptor
                return this
            }

            fun connectTimeout(connectTimeout: Long): HttpBuilder {
                this.connectTimeout = connectTimeout
                return this
            }

            fun readTimeout(readTimeout: Long): HttpBuilder {
                this.readTimeout = readTimeout
                return this
            }

            fun writeTimeout(writeTimeout: Long): HttpBuilder {
                this.writeTimeout = writeTimeout
                return this
            }

            fun build(): HttpUtils {
                return instance.build()
            }
        }

        /**
         * 构造者
         */
        @JvmStatic
        fun builder(): HttpBuilder {
            if (null == httpBuilder) httpBuilder = HttpBuilder()
            return httpBuilder!!
        }

    }

    /**
     * 构造HttpClient
     */
    private fun build(): HttpUtils {
        httpBuilder?.run {
            val builder: OkHttpClient.Builder = OkHttpClient.Builder()
            builder.connectTimeout(connectTimeout, TimeUnit.MILLISECONDS)
            builder.readTimeout(readTimeout, TimeUnit.MILLISECONDS)
            builder.writeTimeout(writeTimeout, TimeUnit.MILLISECONDS)
            for (interceptor in interceptors) {
                builder.addInterceptor(interceptor)
            }
            if (showLog) {
                builder.addInterceptor(
                    if (null != printerInterceptor)
                        printerInterceptor!!
                    else
                        HttpLoggingInterceptor(
                            logger = {
                                L.showD(tag, it)
                            }
                        ).setLevel(HttpLoggingInterceptor.Level.BODY)
                )
            }
            client = builder.build()
        } ?: throw Exception("请在Application中调用HttpUtils.builder().build()完成初始化")
        return this
    }

    /**
     * 设置URL
     */
    fun url(url: String): HttpUtils {
        httpBuilder?.let {
            this.url = it.baseUrl + url
        } ?: throw Exception("请在Application中调用HttpUtils.builder().build()完成初始化")
        return this
    }

    /**
     * Parameter参数
     */
    fun param(key: String, value: Any?): HttpUtils {
        parameter[key] = value
        return this
    }

    /**
     * Body参数
     */
    fun body(body: Any?): HttpUtils {
        this.body = body
        return this
    }

    /**
     * tag
     */
    fun tag(tag: Any?): HttpUtils {
        this.tag = tag
        return this
    }

    /**
     * 清空参数
     */
    private fun clear() {
        parameter.clear()
        body = null
        tag = null
        url = ""
    }


    /**
     * Get请求
     */
    fun <T> get(callback: OriginCallback<T>) {
        val request: Request = Request.Builder()
            .get()
            .tag(tag)
            .url(joinUrl())
            .build()
        goRequest(request, callback)
        clear()
    }

    /**
     * Get同步请求
     */
    fun getSync(): Response? {
        val request: Request = Request.Builder()
            .get()
            .tag(tag)
            .url(joinUrl())
            .build()
        clear()
        return client?.newCall(request)?.execute()
    }

    /**
     * Post请求
     */
    fun <T> post(callback: OriginCallback<T>) {
        val request: Request = Request.Builder()
            .post(JSON.toJSONString(body).toRequestBody("application/json".toMediaType()))
            .url(joinUrl())
            .tag(tag)
            .build()
        goRequest(request, callback)
        clear()
    }

    /**
     * Post同步请求
     */
    fun postSync(): Response? {
        val request: Request = Request.Builder()
            .post(JSON.toJSONString(body).toRequestBody("application/json".toMediaType()))
            .url(joinUrl())
            .tag(tag)
            .build()
        clear()
        return client?.newCall(request)?.execute()
    }

    /**
     * 请求
     */
    private fun <T> goRequest(request: Request, callback: OriginCallback<T>) {
        client?.let {
            Observable.create { emitter ->
                val resp: Response = it.newCall(request).execute()
                emitter.onNext(resp)
            }
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : Observer<Response> {
                    override fun onSubscribe(d: Disposable) {}

                    override fun onError(e: Throwable) {
                        if (e is HttpException) {
                            callback.onFail(e)
                        } else {
                            callback.onFail(HttpException(-1, e.message))
                        }

                    }

                    override fun onComplete() {}

                    override fun onNext(response: Response) {
                        callback.convert(response)
                    }
                })
        } ?: throw Exception("请在Application中调用HttpUtils.builder().build()完成初始化")
    }

    /**
     * 取消网络请求
     */
    fun cancelAll() {
        client?.dispatcher?.cancelAll()
    }

    /**
     * 取消网络请求
     *
     * @param tag
     */
    fun cancelByTag(tag: Any) {
        if (null != client) {
            for (call in client!!.dispatcher.runningCalls()) {
                if (null != call.request().tag() && call.request().tag() == tag) {
                    call.cancel()
                }
            }
        }
    }

    /**
     * 拼接Parameter参数到Url
     */
    private fun joinUrl(): String {
        //https://mock.apifox.com/m1/2234660-0-default/login?a&b
        if (parameter.isNotEmpty()) {
            url = "$url?"
            parameter.forEach { (key, value) ->
                url = "$url$key=$value&"
            }
            url = url.substring(0, url.length - 1)
        }
        return url
    }
}