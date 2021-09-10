package com.pactera.frame.base.activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.pactera.frame.R

/**
 * @author liujiang
 * Desc: 权限申请基类
 */
open class PermissionActivity : AppCompatActivity(), EasyPermissions.PermissionCallbacks {
    private val RC_PERM = 123
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onStart() {
        super.onStart()
    }

    override fun onResume() {
        super.onResume()
    }

    override fun onPause() {
        super.onPause()
    }

    override fun onStop() {
        super.onStop()
    }

    override fun onDestroy() {
        super.onDestroy()
    }

    /**
     * 权限回调接口
     */
    private var mListener: CheckPermListener? = null

    interface CheckPermListener {
        //权限通过后的回调方法
        fun superPermission()

        //权限拒绝后的回调方法
        fun refusePermission()

        //设置权限后返回的回调方法
        fun settingPermission()
    }

    /**
     * 权限申请
     * @param listener
     * @param resString
     * @param mPerms
     */
    fun checkPermission(listener: CheckPermListener?, resString: Int, vararg mPerms: String?) {
        mListener = listener
        if (EasyPermissions.hasPermissions(this, *mPerms)) {
            if (mListener != null) mListener!!.superPermission()
        } else {
            EasyPermissions.requestPermissions(
                this, getString(resString),
                RC_PERM, *mPerms
            )
        }
    }

    /**
     * 权限申请
     * @param listener
     * @param mPerms
     */
    fun checkPermission(listener: CheckPermListener?, vararg mPerms: String?) {
        mListener = listener
        if (EasyPermissions.hasPermissions(this, *mPerms)) {
            if (mListener != null) mListener!!.superPermission()
        } else {
            EasyPermissions.requestPermissions(
                this, getString(R.string.ask_again),
                RC_PERM, *mPerms
            )
        }
    }

    /**
     * 用户权限处理,
     * 如果全部获取, 则直接过.
     * 如果权限缺失, 则提示Dialog.
     *
     * @param requestCode  请求码
     * @param permissions  权限
     * @param grantResults 结果
     */
    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this)
    }

    public override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == EasyPermissions.SETTINGS_REQ_CODE) {
            //设置返回
            if (mListener != null) mListener!!.settingPermission()
        }
    }

    override fun onPermissionsGranted(requestCode: Int, perms: List<String>?) {
//        L.showD("同意了某些权限可能不是全部");
        //同意了某些权限可能不是全部
//        if (mListener != null)
//            mListener.refusePermission();
    }

    override fun onPermissionsAllGranted() {
        if (mListener != null) mListener!!.superPermission() //同意了全部权限的回调
    }

    override fun onPermissionsDenied(requestCode: Int, perms: List<String>) {
        //拒绝了某些权限
        if (mListener != null) mListener!!.refusePermission()
        EasyPermissions.checkDeniedPermissionsNeverAskAgain(this,
            getString(R.string.perm_tip),
            R.string.setting, R.string.cancel, { dialogInterface, i -> if (mListener != null) mListener!!.refusePermission() }, perms
        )
    }

    abstract class SimpleCheckPermListener : CheckPermListener {
        override fun refusePermission() {}
        override fun settingPermission() {}
    }
}