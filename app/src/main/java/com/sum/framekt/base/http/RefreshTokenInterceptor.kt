package com.sum.framekt.base.http

import android.text.TextUtils
//import com.alibaba.fastjson.JSON
//import com.alibaba.fastjson.TypeReference
import com.lzy.okgo.OkGo
import com.lzy.okgo.interceptor.HttpLoggingInterceptor
import com.lzy.okgo.utils.IOUtils
import com.lzy.okgo.utils.OkLogger
import com.sum.framekt.application.pojo.UserBean
import com.sum.framekt.base.pojo.BasePojo
import com.sum.framekt.base.utils.*
import okhttp3.*
import okhttp3.internal.http.HttpHeaders
import okio.Buffer
import java.io.IOException
import java.nio.charset.Charset
import java.util.*
import java.util.concurrent.TimeUnit
import java.util.logging.Level
import java.util.logging.Logger

/**
 * @author  LiuJiang
 * Desc:    token失效拦截器
 */
class RefreshTokenInterceptor(private val tag: String) : Interceptor {
    companion object {
        private val UTF8: Charset = Charset.forName("UTF-8")
    }

    private var printLevel = HttpLoggingInterceptor.Level.NONE
    private var colorLevel: Level = Level.INFO
    private var logger: Logger? = null

    init {
        logger = Logger.getLogger(tag)
    }

    fun setPrintLevel(level: HttpLoggingInterceptor.Level) {
        printLevel = level
    }

    fun setColorLevel(level: Level) {
        colorLevel = level
    }

    private fun log(message: String) {
        logger?.log(colorLevel, message)
    }

    override fun intercept(chain: Interceptor.Chain): Response {
        val request: Request = chain.request()
        /****************刷新token开始******************/
        //判断请求是否需要token
//        if (!request.url().toString().contains(Config.LOGIN)) {
//            //token
//            val token: String? = SPUtils.getString(Constant.ACCESS_TOKEN, null)
//            if (!TextUtils.isEmpty(token)) {
//                //上次token刷新时间
//                val lastRefreshTime: Long = SPUtils.getLong(Constant.TOKEN_REFRESH_TIME, 0)
//                //当前时间
//                val now = DateUtils.getCurrentStamps()
//                //获取token有效期
//                val validity = Constant.VALIDITY
//                //token已过期,重新请求登录接口
//                if (now - lastRefreshTime > validity) {
//                    //账号密码登录（同步网络请求,阻塞方法）
//                    val userName = SPUtils.getString(Constant.USERNAME, "")
//                    val pwd = SPUtils.getString(Constant.PWD, "")
//                    if (!TextUtils.isEmpty(userName) && !TextUtils.isEmpty(pwd)) {
//                        //进行登录网络请求
//                        val model = LoginParametersBean(userName, pwd)
//                        val result: Response? = OkGo.post<Any>(Config.LOGIN).upJson(JSON.toJSONString(model)).execute()
//                        result?.body()?.let {
//                            val pojo: BasePojo<UserBean> = JSON.parseObject(it.string(), object : TypeReference<BasePojo<UserBean>>() {})
//                            if (0 == pojo.error) {
//                                val tokenNew: String = pojo.data.getToken()
//                                Log.showD("tokenNew：$tokenNew")
//                                //存储token
//                                SPUtils.setString(Constant.ACCESS_TOKEN, tokenNew)
//                                //更新token刷新时间
//                                SPUtils.setLong(Constant.TOKEN_REFRESH_TIME, DateUtils.getCurrentStamps())
//                                //更新网络请求header中的token
//                                val newRequest = chain.request().newBuilder().header("X-Access-Token", tokenNew).build()
//                                /****************刷新token结束 */
//                                /****************定义日志开始 */
//                                if (printLevel == HttpLoggingInterceptor.Level.NONE) {
//                                    return chain.proceed(newRequest)
//                                }
//                                //请求日志拦截
//                                logForRequest(newRequest, chain.connection())
//                                //执行请求，计算请求时间
//                                val startNs = System.nanoTime()
//                                val response: Response = try {
//                                    chain.proceed(newRequest)
//                                } catch (e: java.lang.Exception) {
//                                    log("<-- HTTP FAILED: $e")
//                                    throw e
//                                }
//                                val tookMs = TimeUnit.NANOSECONDS.toMillis(System.nanoTime() - startNs)
//                                /****************定义日志结束 */
//
//                                //响应日志拦截
//                                return logForResponse(response, tookMs)!!
//                            }
//                        }
//
//                    }
//                }
//            }
//        }
        /****************刷新token结束******************/
        /****************定义日志开始******************/
        if (printLevel == HttpLoggingInterceptor.Level.NONE) {
            return chain.proceed(request)
        }
        //请求日志拦截
        //请求日志拦截
        logForRequest(request, chain.connection())
        //执行请求，计算请求时间
        //执行请求，计算请求时间
        val startNs = System.nanoTime()
        val response: Response = try {
            chain.proceed(request)
        } catch (e: java.lang.Exception) {
            log("<-- HTTP FAILED: $e")
            throw e
        }
        val tookMs = TimeUnit.NANOSECONDS.toMillis(System.nanoTime() - startNs)
        /****************定义日志结束******************/
        //响应日志拦截
        /****************定义日志结束 */
        //响应日志拦截
        return logForResponse(response, tookMs)!!
    }

    private fun logForResponse(response: Response, tookMs: Long): Response? {
        val builder = response.newBuilder()
        val clone = builder.build()
        var responseBody = clone.body()
        val logBody = printLevel == HttpLoggingInterceptor.Level.BODY
        val logHeaders = printLevel == HttpLoggingInterceptor.Level.BODY || printLevel == HttpLoggingInterceptor.Level.HEADERS
        try {
            log("<-- " + clone.code() + ' ' + clone.message() + ' ' + clone.request().url() + " (" + tookMs + "ms）")
            if (logHeaders) {
                val headers = clone.headers()
                var i = 0
                val count = headers.size()
                while (i < count) {
                    log("\t" + headers.name(i) + ": " + headers.value(i))
                    i++
                }
                log(" ")
                if (logBody && HttpHeaders.hasBody(clone)) {
                    if (responseBody == null) return response
                    if (isPlaintext(responseBody.contentType())) {
                        val bytes = IOUtils.toByteArray(responseBody.byteStream())
                        val contentType = responseBody.contentType()
                        val body = String(bytes, getCharset(contentType))
                        log("\tbody:$body")
                        responseBody = ResponseBody.create(responseBody.contentType(), bytes)
                        return response.newBuilder().body(responseBody).build()
                    } else {
                        log("\tbody: maybe [binary body], omitted!")
                    }
                }
            }
        } catch (e: java.lang.Exception) {
            OkLogger.printStackTrace(e)
        } finally {
            log("<-- END HTTP")
        }
        return response
    }

    @Throws(IOException::class)
    private fun logForRequest(request: Request, connection: Connection?) {
        val logBody = printLevel == HttpLoggingInterceptor.Level.BODY
        val logHeaders = printLevel == HttpLoggingInterceptor.Level.BODY || printLevel == HttpLoggingInterceptor.Level.HEADERS
        val requestBody = request.body()
        val hasRequestBody = requestBody != null
        val protocol = if (connection != null) connection.protocol() else Protocol.HTTP_1_1
        try {
            val requestStartMessage = "--> " + request.method() + ' ' + request.url() + ' ' + protocol
            log(requestStartMessage)
            if (logHeaders) {
                if (hasRequestBody) {
                    // Request body headers are only present when installed as a network interceptor. Force
                    // them to be included (when available) so there values are known.
                    if (requestBody!!.contentType() != null) {
                        log("\tContent-Type: " + requestBody.contentType())
                    }
                    if (requestBody.contentLength() != -1L) {
                        log("\tContent-Length: " + requestBody.contentLength())
                    }
                }
                val headers = request.headers()
                var i = 0
                val count = headers.size()
                while (i < count) {
                    val name = headers.name(i)
                    // Skip headers from the request body as they are explicitly logged above.
                    if (!"Content-Type".equals(name, ignoreCase = true) && !"Content-Length".equals(name, ignoreCase = true)) {
                        log("\t" + name + ": " + headers.value(i))
                    }
                    i++
                }
                log(" ")
                if (logBody && hasRequestBody) {
                    if (isPlaintext(requestBody!!.contentType())) {
                        bodyToString(request)
                    } else {
                        log("\tbody: maybe [binary body], omitted!")
                    }
                }
            }
        } catch (e: java.lang.Exception) {
            OkLogger.printStackTrace(e)
        } finally {
            log("--> END " + request.method())
        }
    }

    private fun isPlaintext(mediaType: MediaType?): Boolean {
        if (mediaType == null) return false
        if (mediaType.type() != null && (mediaType.type() == "text")) {
            return true
        }
        var subtype = mediaType.subtype()
        if (subtype != null) {
            subtype = subtype.lowercase(Locale.getDefault())
            if (subtype.contains("x-www-form-urlencoded") || subtype.contains("json") || subtype.contains("xml") || subtype.contains("html")) //
                return true
        }
        return false
    }

    private fun bodyToString(request: Request) {
        try {
            val copy = request.newBuilder().build()
            val body = copy.body() ?: return
            val buffer = Buffer()
            body.writeTo(buffer)
            val charset: Charset = getCharset(body.contentType())
            log("\tbody:" + buffer.readString(charset))
        } catch (e: Exception) {
            OkLogger.printStackTrace(e)
        }
    }

    private fun getCharset(contentType: MediaType?): Charset {
        var charset = if (contentType != null) contentType.charset(UTF8) else UTF8
        if (charset == null) charset = UTF8
        return charset
    }

}