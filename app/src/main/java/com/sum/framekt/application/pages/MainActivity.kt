package com.sum.framekt.application.pages

import android.os.Bundle
import com.sum.framekt.base.activity.BaseActivity
import com.sum.framekt.databinding.ActivityMainBinding

class MainActivity : BaseActivity<ActivityMainBinding>(){

    override fun getBinding(): ActivityMainBinding = ActivityMainBinding.inflate(layoutInflater)

    override fun initView(savedInstanceState: Bundle?) {

    }

}