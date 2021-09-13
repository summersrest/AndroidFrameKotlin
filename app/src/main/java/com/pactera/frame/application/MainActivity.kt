package com.pactera.frame.application

import android.os.Bundle
import com.pactera.frame.base.activity.BaseActivity
import com.pactera.frame.databinding.ActivityMainBinding

class MainActivity : BaseActivity<ActivityMainBinding>() {
    override fun initViewBinding(): ActivityMainBinding {
        return ActivityMainBinding.inflate(layoutInflater)
    }

    override fun initView(savedInstanceState: Bundle?) {

    }
}