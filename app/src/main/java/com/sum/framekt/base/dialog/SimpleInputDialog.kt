package com.sum.framekt.base.dialog

import android.content.Context
import com.lxj.xpopup.XPopup

/**
 * @author  LiuJiang
 * Desc:    带输入框的dialog
 */
class SimpleInputDialog(private var context: Context) {
    /**
     * 标题
     */
    private var title: String = "请填写"

    /**
     * 确定按钮
     */
    private var onResultCallback: (String) -> Unit = {}

    /**
     * 触摸弹窗外关闭弹窗
     */
    private var isDismissOnTouchOutside = true

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
     * 是否自动打开输入法
     */
    private var autoOpenSoftInput = true

    /**
     * 输入框默认内容
     */
    private var content: String? = null

    /**
     * 提示文字
     */
    private var hint: String = "请输入"

    /**
     * 设置标题
     */
    fun setTitle(title: String): SimpleInputDialog {
        this.title = title
        return this
    }

    /**
     * 设置点击监听
     */
    fun setOnConfirmCallback(onResultCallback: (String) -> Unit): SimpleInputDialog {
        this.onResultCallback = onResultCallback
        return this
    }

    /**
     * 点击谈窗外关闭弹窗
     */
    fun setDismissOnTouchOutside(dismissOnTouchOutside: Boolean): SimpleInputDialog {
        this.isDismissOnTouchOutside = dismissOnTouchOutside
        return this
    }

    /**
     * 操作结束后是否自动关闭弹窗
     */
    fun autoDismiss(autoDismiss: Boolean): SimpleInputDialog {
        this.autoDismiss = autoDismiss
        return this
    }

    /**
     * 是否深色主题
     */
    fun isDarkTheme(isDarkTheme: Boolean): SimpleInputDialog {
        this.isDarkTheme = isDarkTheme
        return this
    }

    /**
     * 返回按钮是否可以关闭弹窗
     */
    fun dismissOnBackPressed(isDismissOnBackPressed: Boolean): SimpleInputDialog {
        this.isDismissOnBackPressed = isDismissOnBackPressed
        return this
    }

    /**
     * 是否自动打开输入法
     */
    fun autoOpenSoftInput(autoOpenSoftInput: Boolean): SimpleInputDialog {
        this.autoOpenSoftInput = autoOpenSoftInput
        return this
    }

    /**
     * 输入框默认内容
     */
    fun setContent(content: String): SimpleInputDialog {
        this.content = content
        return this
    }

    /**
     * 输入框默认hint
     */
    fun setHint(hint: String): SimpleInputDialog {
        this.hint = hint
        return this
    }

    /**
     * 显示弹窗
     */
    fun show() {
        XPopup.Builder(context).autoDismiss(autoDismiss).isDarkTheme(isDarkTheme).autoOpenSoftInput(autoOpenSoftInput).dismissOnBackPressed(isDismissOnBackPressed)
            .dismissOnTouchOutside(isDismissOnTouchOutside).asInputConfirm(title, content, hint) { text: String? ->
                onResultCallback(text ?: "")
            }.show()
    }
}