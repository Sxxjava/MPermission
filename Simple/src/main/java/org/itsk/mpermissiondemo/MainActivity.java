package org.itsk.mpermissiondemo;

import android.Manifest;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import org.itsk.mpermission.MPermissions;
import org.itsk.mpermission.PermissionDenied;
import org.itsk.mpermission.PermissionGrant;


public class MainActivity extends BaseActivity {

    private android.widget.Button btnrequestsdcard;
    private android.widget.Button btnrequestcall;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.btnrequestcall = (Button) findViewById(R.id.btn_request_call);
        this.btnrequestsdcard = (Button) findViewById(R.id.btn_request_sdcard);

        btnrequestsdcard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!MPermissions.shouldShowRequestPermissionRationale(MainActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE, REQUECT_CODE_SDCARD))
                {
                    MPermissions.requestPermissions(MainActivity.this, REQUECT_CODE_SDCARD, Manifest.permission.WRITE_EXTERNAL_STORAGE);
                }
            }
        });

        btnrequestcall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!MPermissions.shouldShowRequestPermissionRationale(MainActivity.this, Manifest.permission.CALL_PHONE, REQUECT_CODE_CALL_PHONE))
                {
                    MPermissions.requestPermissions(MainActivity.this, REQUECT_CODE_CALL_PHONE, Manifest.permission.CALL_PHONE);
                }
            }
        });
    }

    @PermissionGrant(REQUECT_CODE_SDCARD)
    public void requestSdcardSuccess()
    {
        Toast.makeText(this, "读写存储卡权限已允许!", Toast.LENGTH_SHORT).show();
    }

    @PermissionDenied(REQUECT_CODE_SDCARD)
    public void requestSdcardFailed()
    {
        Toast.makeText(this, "用户拒绝访问存储卡!", Toast.LENGTH_SHORT).show();
    }
}
