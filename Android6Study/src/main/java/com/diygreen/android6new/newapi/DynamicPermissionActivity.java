package com.diygreen.android6new.newapi;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Switch;
import android.widget.Toast;

import com.diygreen.android6new.R;

public class DynamicPermissionActivity extends AppCompatActivity implements LocationListener {

    static private final String TAG = "DynamicPermission";
    private static final String PACKAGE_URL_SCHEME = "package:"; // 方案
    private static final int REQUEST_CODE_ASK_EXTERNAL_STORAGE_PERMISSON = 111;
    static private final int REQUEST_CODE_ASK_AUDIO_PERMISSION = 0; // 请求码
    static final String[] EXTERNAL_STORAGE_PERMISSIONS = new String[]{ // 所需的全部权限
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
    };
    static final String[] AUDIO_PERMISSIONS = new String[]{ // 所需的全部权限
            Manifest.permission.RECORD_AUDIO,
            Manifest.permission.MODIFY_AUDIO_SETTINGS
    };

    private PermissionsChecker mPermissionsChecker; // 权限检测器

    private Switch mCheckSwitch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dynamicpermission);

        initView();
        initData();
    }

    private void initView() {
        this.mCheckSwitch = (Switch) findViewById(R.id.switch_checkpermission);
    }

    private void initData() {
        mPermissionsChecker = new PermissionsChecker(this);
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_getlocation:
                getLocation();
                break;
            case R.id.btn_testsdcardpermission:
                testSDCardPermission();
                break;
            case R.id.btn_testaudiopermission:
                testAudioPermission();
                break;
        }
    }

    public void getLocation() {
        LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        if (ActivityCompat.checkSelfPermission(this,
                    Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                &&
                ActivityCompat.checkSelfPermission(this,
                        Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, this);
    }

    private void testSDCardPermission() {
        if (mCheckSwitch.isChecked()) {
            // 1. 判断是否有权限
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                // 在弹出权限选择的对话框前给用户show一个dialog，用于引导用户进行选择
                if (!ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                    // 解释为什么要申请权限
                    showMessage("测试一下对SD卡进行读写操作",
                            new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog,
                                                    int which) {
                                    startAppSettings();
                                }
                            });
                    return;
                }
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                        REQUEST_CODE_ASK_EXTERNAL_STORAGE_PERMISSON);
            } else {
                Toast.makeText(DynamicPermissionActivity.this, "有权限了开始读写SD卡吧！", Toast.LENGTH_LONG).show();
            }
        } else {
            // 1. 判断是否有权限
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED
                    &&
                    ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                // 2. 弹出对话框申请权限给用户选择
                // 第二个参数code与onRequestPermissionResult()方法中的code对应
                ActivityCompat.requestPermissions(this, EXTERNAL_STORAGE_PERMISSIONS, REQUEST_CODE_ASK_EXTERNAL_STORAGE_PERMISSON);
            }  else {
                Toast.makeText(DynamicPermissionActivity.this, "有权限了开始读写SD卡吧！", Toast.LENGTH_LONG).show();
            }
        }
    }

    private void testAudioPermission() {
        // 缺少权限时, 进入权限配置页面
        if (mPermissionsChecker.lacksPermissions(AUDIO_PERMISSIONS)) {
            startPermissionsActivity();
        }
    }

    private void showMessage(String message,
                             DialogInterface.OnClickListener okListener) {
        new AlertDialog.Builder(this)
                .setMessage(message)
                .setNegativeButton("取消", null)
                .setPositiveButton("设置", okListener).create().show();
    }

    // 启动应用的设置
    private void startAppSettings() {
        Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
        intent.setData(Uri.parse(PACKAGE_URL_SCHEME + getPackageName()));
        startActivity(intent);
    }

    private void startPermissionsActivity() {
        PermissionsActivity.startActivityForResult(this, REQUEST_CODE_ASK_AUDIO_PERMISSION, AUDIO_PERMISSIONS);
    }

    //==========申请权限结果回调==========
    // 判断用户是否确认了权限
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case REQUEST_CODE_ASK_EXTERNAL_STORAGE_PERMISSON:
                // Permission Granted
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(DynamicPermissionActivity.this, "用户确认授权操作SD卡权限", Toast.LENGTH_SHORT).show();
                } else { // Permission Denied
                    Toast.makeText(DynamicPermissionActivity.this, "用户拒绝授权操作SD卡权限", Toast.LENGTH_SHORT).show();
                }
                break;
            default:
                super.onRequestPermissionsResult(requestCode, permissions, grantResults);
                break;
        }
    }

    //==========获取位置信息回调==========
    @Override
    public void onLocationChanged(Location location) {

    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }

    //==========其他生命周期方法==========
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // 拒绝时, 关闭页面, 缺少主要权限, 无法运行
        if (requestCode == REQUEST_CODE_ASK_AUDIO_PERMISSION && resultCode == PermissionsActivity.PERMISSIONS_DENIED) {
            Log.e(TAG, "缺少主要权限, 无法运行");
        }
    }

}
