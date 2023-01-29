package com.sum.framekt.application.pages

import android.content.Intent
import android.os.Bundle
import android.view.View
import com.sum.framekt.base.activity.BaseMvpActivity
import com.sum.framekt.base.utils.ActivityUtils
import com.sum.framekt.base.utils.Log
import com.sum.framekt.base.utils.isEmpty
import com.sum.framekt.databinding.ActivityMainBinding

class MainActivity : BaseMvpActivity<ActivityMainBinding, MainPresenter>(), MainView {
    override fun createPresenter(): MainPresenter = MainPresenter(this, this)

    override fun getBinding(): ActivityMainBinding = ActivityMainBinding.inflate(layoutInflater)

    override fun initView(savedInstanceState: Bundle?) {
        viewBinding.btnSend.setOnClickListener(this )
    }

    override fun onClickEvent(v: View?) {
        super.onClickEvent(v)
        when (v) {
            viewBinding.btnSend -> {
                viewBinding.etUserName.isEmpty("用户名为空") ?: return

                Log.showD("流程完成")
            }
        }
    }

}