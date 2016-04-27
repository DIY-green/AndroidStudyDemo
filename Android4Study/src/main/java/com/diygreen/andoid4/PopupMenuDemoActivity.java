package com.diygreen.andoid4;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.PopupMenu;
import android.widget.Toast;

public class PopupMenuDemoActivity extends AppCompatActivity implements PopupMenu.OnMenuItemClickListener {

    private static final int MENU_ITEM_COPY_ID = 1001;
    private static final int MENU_ITEM_PASTE_ID = 1002;

    private PopupMenu mPopupMenu1;
    private PopupMenu mPopupMenu2;
    private PopupMenu mPopupMenu3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_popupmenudemo);

        initPupupMenu();
        initListener();
    }

    private void initPupupMenu() {
        createPopupMenuByCode();
        createPopupMenuFromXML();
        createPopupMenuFromMixture();
    }

    private void initListener() {
        this.mPopupMenu1.setOnMenuItemClickListener(this);
        this.mPopupMenu2.setOnMenuItemClickListener(this);
        this.mPopupMenu3.setOnMenuItemClickListener(this);
    }

    private void createPopupMenuByCode() {
        mPopupMenu1 = new PopupMenu(this, findViewById(R.id.btn_popupmenu1));
        Menu menu = mPopupMenu1.getMenu();

        // 通过代码添加菜单项
        menu.add(Menu.NONE, MENU_ITEM_COPY_ID, 0, "唐僧");
        menu.add(Menu.NONE, MENU_ITEM_PASTE_ID, 1, "孙悟空");
    }

    private void createPopupMenuFromXML() {
        mPopupMenu2 = new PopupMenu(this, findViewById(R.id.btn_popupmenu2));
        Menu menu = mPopupMenu2.getMenu();

        // 通过XML文件添加菜单项
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.popupmenu, menu);
    }

    private void createPopupMenuFromMixture() {
        mPopupMenu3 = new PopupMenu(this, findViewById(R.id.btn_popupmenu3));
        Menu menu = mPopupMenu3.getMenu();
        // 通过代码添加菜单项
        menu.add(Menu.NONE, MENU_ITEM_COPY_ID, 0, "唐僧");
        menu.add(Menu.NONE, MENU_ITEM_PASTE_ID, 1, "孙悟空");
        // 通过XML文件添加菜单项
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.popupmenu, menu);
    }

    //==========点击事件处理==========
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_popupmenu1:
                showPopupMenu1();
                break;
            case R.id.btn_popupmenu2:
                showPopupMenu2();
                break;
            case R.id.btn_popupmenu3:
                showPopupMenu3();
                break;
        }
    }

    private void showPopupMenu1() {
        mPopupMenu1.show();
    }

    private void showPopupMenu2() {
        mPopupMenu2.show();
    }

    private void showPopupMenu3() {
        mPopupMenu3.show();
    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        toast(item.getTitle().toString() + item.getItemId());
        return false;
    }

    private void toast(String str) {
        Toast.makeText(this, str, Toast.LENGTH_LONG).show();
    }
}
