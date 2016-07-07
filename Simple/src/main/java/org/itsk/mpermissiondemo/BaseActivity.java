package org.itsk.mpermissiondemo;

import android.Manifest;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import org.itsk.mpermission.MPermissions;
import org.itsk.mpermission.PermissionDenied;
import org.itsk.mpermission.PermissionGrant;
import org.itsk.mpermission.ShowRequestPermissionRationale;

/**
 * Created by Ewen on 2016/7/6.
 */
public class BaseActivity extends AppCompatActivity {
    protected static final int REQUECT_CODE_SDCARD = 2;
    protected static final int REQUECT_CODE_CALL_PHONE = 3;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }


    /**
     * 如果用户拒绝读写存储卡权限后再次申请需要向用户解释为什么我们需要申请该权限
     */
    @ShowRequestPermissionRationale(REQUECT_CODE_SDCARD)
    public void whyNeedSdCard()
    {
        Toast.makeText(this, "App需要将一些缓存数据写到SD卡!", Toast.LENGTH_SHORT).show();
        MPermissions.requestPermissions(this, REQUECT_CODE_SDCARD, Manifest.permission.WRITE_EXTERNAL_STORAGE);

    }

    @ShowRequestPermissionRationale(REQUECT_CODE_CALL_PHONE)
    public void whyNeedCall()
    {
        Toast.makeText(this, "App在用户需要打电话联系商家时拨打电话!", Toast.LENGTH_SHORT).show();
        MPermissions.requestPermissions(this, REQUECT_CODE_SDCARD, Manifest.permission.WRITE_EXTERNAL_STORAGE);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults)
    {
        MPermissions.onRequestPermissionsResult(this, requestCode, permissions, grantResults);
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }



    @PermissionGrant(REQUECT_CODE_CALL_PHONE)
    public void requestCallSuccess()
    {
        Toast.makeText(this, "拨打电话权限已允许!", Toast.LENGTH_SHORT).show();
    }

    @PermissionDenied(REQUECT_CODE_CALL_PHONE)
    public void requestCallFailed()
    {
        Toast.makeText(this, "用户拒绝拨打电话!", Toast.LENGTH_SHORT).show();
    }
}
