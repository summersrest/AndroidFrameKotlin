package com.sum.frame.dialog

import android.content.Context
import com.lxj.xpopup.XPopup

/**
 * @author  LiuJiang
 * Desc:    列表弹窗
 */
class SimpleListDialog<T>(var context: Context) {
    private var title = "请选择"
    private var align: DialogAlign = DialogAlign.ALIGN_CENTER
    private var data: MutableList<T> = mutableListOf()
    private var iconIds: MutableList<Int>? = null
    private var checkedPosition = -1
    private var formatter: ((item: T) -> String)? = null
    private var isDarkTheme = false
    private var isDismissOnBackPressed = true
    private var isDismissOnTouchOutside = true
    private var maxHeight = 0
    private var maxWidth = 0
    private var onSelectedCallBack: (position: Int, item: T) -> Unit = { _, _ -> }

    fun setTitle(title: String): SimpleListDialog<T> {
        this.title = title
        return this
    }

    fun setData(data: MutableList<T>?): SimpleListDialog<T> {
        data?.let { this.data = data }
        return this
    }

    fun setAlign(align: DialogAlign): SimpleListDialog<T> {
        this.align = align
        return this
    }

    fun setOnSelectCallback(callback: (Int, T) -> Unit): SimpleListDialog<T> {
        this.onSelectedCallBack = callback
        return this
    }

    fun setIconIds(iconIds: MutableList<Int>?): SimpleListDialog<T> {
        iconIds?.let { this.iconIds = iconIds }
        return this
    }

    fun setCheckedPosition(checkedPosition: Int): SimpleListDialog<T> {
        this.checkedPosition = checkedPosition
        return this
    }

    fun setFormatter(formatter: (item: T) -> String): SimpleListDialog<T> {
        this.formatter = formatter
        return this
    }


    fun isDarkTheme(isDarkTheme: Boolean): SimpleListDialog<T> {
        this.isDarkTheme = isDarkTheme
        return this
    }

    fun dismissOnBackPressed(isDismissOnBackPressed: Boolean): SimpleListDialog<T> {
        this.isDismissOnBackPressed = isDismissOnBackPressed
        return this
    }

    fun setDismissOnTouchOutside(dismissOnTouchOutside: Boolean): SimpleListDialog<T> {
        this.isDismissOnTouchOutside = dismissOnTouchOutside
        return this
    }

    fun setMaxHeight(maxHeight: Int): SimpleListDialog<T> {
        this.maxHeight = maxHeight
        return this
    }

    fun setMaxWidth(maxWidth: Int): SimpleListDialog<T> {
        this.maxWidth = maxWidth
        return this
    }

    fun show() {
        val itemList: List<String> = data.map {
            if (null != formatter)
                formatter!!(it)
            else
                it.toString()
        }

        checkedPosition = checkedPosition.takeUnless { checkedPosition >= data.size } ?: -1

        XPopup.Builder(context)
            .maxHeight(maxHeight)
            .maxWidth(maxWidth)
            .dismissOnBackPressed(isDismissOnBackPressed)
            .dismissOnTouchOutside(isDismissOnTouchOutside)
            .isDarkTheme(isDarkTheme)
            .run {
                if (align == DialogAlign.ALIGN_BOTTOM) {
                    this.asBottomList(
                        title, itemList.toTypedArray(), iconIds?.toIntArray(), checkedPosition
                    ) { position, _ -> onSelectedCallBack(position, data[position]) }.show()
                } else {
                    this.asCenterList(
                        title, itemList.toTypedArray(), iconIds?.toIntArray(), checkedPosition
                    ) { position, _ -> onSelectedCallBack(position, data[position]) }.show()
                }
            }

    }
}

enum class DialogAlign {
    //底部弹出
    ALIGN_BOTTOM,

    //中间弹出
    ALIGN_CENTER,
}