package com.sum.frame.dialog

import android.content.Context
import com.lxj.xpopup.XPopup

/**
 * @author  LiuJiang
 * Desc:    确认弹窗
 */
class SimpleAlertDialog(var context: Context) {
    /**
     * 标题
     */
    private var title = "⚠警告"

    /**
     * 内容
     */
    private var message = ""

    /**
     * 确定按钮
     */
    private var positiveText = "确定"
    private var positiveCallBack: () -> Unit = { }

    /**
     * 取消按钮
     */
    private var negativeText = "取消"
    private var negativeCallBack: () -> Unit? = { }

    /**
     * 触摸弹窗外关闭弹窗
     */
    private var isDismissOnTouchOutside = true

    /**
     * 隐藏取消按钮
     */
    private var isHideCancel = false

    /**
     * 是否自动关闭
     */
    private var autoDismiss = true

    /**
     * 是否深色主题
     */
    private var isDarkTheme = false

    /**
     * 返回按钮是否可关闭
     */
    private var isDismissOnBackPressed = true

    /**
     * 设置标题
     */
    fun setTitle(title: String): SimpleAlertDialog {
        this.title = title
        return this
    }

    /**
     * 设置内容
     */
    fun setMessage(content: String): SimpleAlertDialog {
        this.message = content
        return this
    }

    /**
     * 确定按钮文字
     */
    fun setPositiveText(positiveText: String): SimpleAlertDialog {
        this.positiveText = positiveText
        return this
    }

    /**
     * 确定按钮
     */
    fun setPositiveButton(positiveCallBack: () -> Unit): SimpleAlertDialog {
        this.positiveCallBack = positiveCallBack
        return this
    }

    /**
     * 取消按钮文字
     */
    fun setNegativeText(negativeText: String): SimpleAlertDialog {
        this.negativeText = negativeText
        return this
    }

    /**
     * 取消按钮
     */
    fun setNegativeButton(negativeCallBack: () -> Unit): SimpleAlertDialog {
        this.negativeCallBack = negativeCallBack
        return this
    }

    /**
     * 是否隐藏取消按钮
     */
    fun isHideCancel(isHideCancel: Boolean): SimpleAlertDialog {
        this.isHideCancel = isHideCancel
        return this
    }

    /**
     * 点击谈窗外关闭弹窗
     */
    fun setDismissOnTouchOutside(isDismissOnTouchOutside: Boolean): SimpleAlertDialog {
        this.isDismissOnTouchOutside = isDismissOnTouchOutside
        return this
    }

    /**
     * 操作结束后是否自动关闭弹窗
     */
    fun autoDismiss(autoDismiss: Boolean): SimpleAlertDialog {
        this.autoDismiss = autoDismiss
        return this
    }

    /**
     * 是否深色主题
     */
    fun isDarkTheme(isDarkTheme: Boolean): SimpleAlertDialog {
        this.isDarkTheme = isDarkTheme
        return this
    }

    /**
     * 返回按钮是否可以关闭弹窗
     */
    fun dismissOnBackPressed(isDismissOnBackPressed: Boolean): SimpleAlertDialog {
        this.isDismissOnBackPressed = isDismissOnBackPressed
        return this
    }

    fun show() {
        XPopup.Builder(context)
            .autoDismiss(autoDismiss)
            .isDarkTheme(isDarkTheme)
            .dismissOnBackPressed(isDismissOnBackPressed)
            .dismissOnTouchOutside(isDismissOnTouchOutside)
            .asConfirm(
                title,
                message,
                negativeText,
                positiveText,
                { positiveCallBack() },
                { negativeCallBack() },
                isHideCancel
            ).show()
    }
}