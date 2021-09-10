package com.pactera.frame.application

import android.os.Bundle
import com.pactera.frame.base.activity.BaseActivity
import com.pactera.frame.base.activity.BaseMvpActivity
import com.pactera.frame.databinding.ActivityMainBinding

class MainActivity : BaseMvpActivity<ActivityMainBinding, MainPresenter>(), MainView {
    override fun initViewBinding(): ActivityMainBinding {
        return ActivityMainBinding.inflate(layoutInflater)
    }


    override fun createPresenter(): MainPresenter {
        return MainPresenter(this, this)
    }

    override fun initView(savedInstanceState: Bundle?) {
        viewBinding.tvText.text = "我是测试TextView"

        presenter.getRequest()
    }


}