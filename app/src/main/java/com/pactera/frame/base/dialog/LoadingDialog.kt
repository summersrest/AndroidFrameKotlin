package com.pactera.frame.base.dialog

import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import com.pactera.frame.R
import com.pactera.frame.base.utils.WorkUtils
import com.pactera.frame.databinding.DialogBaseLoadingBinding

/**
 * @author liujiang
 * Desc: 加载弹窗
 */
class LoadingDialog : AlertDialog {
    private var message: String? = WorkUtils.getString(R.string.loading)
    private var viewBinding: DialogBaseLoadingBinding? = null

    constructor(context: Context, message: String) : super(context) {
        this.message = message
        this.setCancelable(false)
    }

    constructor(context: Context, themeResId: Int, message: String) : super(context, themeResId) {
        this.message = message
        this.setCancelable(false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = DialogBaseLoadingBinding.inflate(layoutInflater)
        setContentView(viewBinding!!.root)
        viewBinding?.tipsLoadingMsg?.text = message
    }

    fun setText(message: String?) {
        this.message = message
        viewBinding?.tipsLoadingMsg?.text = message
    }

    fun setText(resId: Int) {
        setText(context.resources.getString(resId))
    }
}