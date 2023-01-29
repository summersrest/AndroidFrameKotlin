package com.sum.framekt.base.dialog

import android.content.Context
import androidx.lifecycle.viewmodel.CreationExtras
import com.lxj.xpopup.XPopup
import com.lxj.xpopup.interfaces.OnCancelListener
import com.lxj.xpopup.interfaces.OnConfirmListener

/**
 * @author  LiuJiang
 * Desc:    简单的Dialog弹窗
 */
class SimpleDialog(private var context: Context) {
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
    fun setTitle(title: String): SimpleDialog {
        this.title = title
        return this
    }

    /**
     * 设置内容
     */
    fun setMessage(content: String): SimpleDialog {
        this.message = content
        return this
    }

    /**
     * 确定按钮文字
     */
    fun setPositiveText(positiveText: String) : SimpleDialog {
        this.positiveText = positiveText
        return this
    }

    /**
     * 确定按钮
     */
    fun setPositiveButton(positiveCallBack: () -> Unit): SimpleDialog {
        this.positiveCallBack = positiveCallBack
        return this
    }

    /**
     * 取消按钮文字
     */
    fun setNegativeText(negativeText: String) : SimpleDialog {
        this.negativeText = negativeText
        return this
    }

    /**
     * 取消按钮
     */
    fun setNegativeButton(negativeCallBack: () -> Unit): SimpleDialog {
        this.negativeText = negativeText
        this.negativeCallBack = negativeCallBack
        return this
    }

    /**
     * 是否隐藏取消按钮
     */
    fun isHideCancel(isHideCancel: Boolean): SimpleDialog {
        this.isHideCancel = isHideCancel
        return this
    }

    /**
     * 点击谈窗外关闭弹窗
     */
    fun setDismissOnTouchOutside(isDismissOnTouchOutside: Boolean): SimpleDialog {
        this.isDismissOnTouchOutside = isDismissOnTouchOutside
        return this
    }

    /**
     * 操作结束后是否自动关闭弹窗
     */
    fun autoDismiss(autoDismiss: Boolean): SimpleDialog {
        this.autoDismiss = autoDismiss
        return this
    }

    /**
     * 是否深色主题
     */
    fun isDarkTheme(isDarkTheme: Boolean): SimpleDialog {
        this.isDarkTheme = isDarkTheme
        return this
    }

    /**
     * 返回按钮是否可以关闭弹窗
     */
    fun dismissOnBackPressed(isDismissOnBackPressed: Boolean): SimpleDialog {
        this.isDismissOnBackPressed = isDismissOnBackPressed
        return this
    }

    fun show() {
        XPopup.Builder(context).autoDismiss(autoDismiss).isDarkTheme(isDarkTheme).dismissOnBackPressed(isDismissOnBackPressed)
            .dismissOnTouchOutside(isDismissOnTouchOutside)
            .asConfirm(title, message, negativeText, positiveText, { positiveCallBack() }, { negativeCallBack() }, isHideCancel).show()
    }
}