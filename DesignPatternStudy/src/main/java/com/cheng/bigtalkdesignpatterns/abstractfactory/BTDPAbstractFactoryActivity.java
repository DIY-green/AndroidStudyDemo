package com.cheng.bigtalkdesignpatterns.abstractfactory;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import com.cheng.designpatternstudy.R;

public class BTDPAbstractFactoryActivity extends AppCompatActivity {

    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_btdp_abstractfactory);

        sharedPreferences = getSharedPreferences("BTDPAbstractFactoryActivity", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("userdb", "com.cheng.bigtalkdesignpatterns.abstractfactory.AccessUser");
        editor.putString("departmentdb", "com.cheng.bigtalkdesignpatterns.abstractfactory.SqlserverDepartment");
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bti_test1_btn:
                User user = new User();
                Department department = new Department();
                // IFactory factory = new SqlServerFactory();
                IFactory factory = new AccessFactory();

                IUser iu = factory.createUser();
                iu.insert(user);
                iu.getUser(1);

                IDepartment id = factory.createDepartment();
                id.insert(department);
                id.getDepartment(1);
                break;
            case R.id.bti_test2_btn:
                User user1 = new User();
                Department department1 = new Department();

                IUser iUser = DataAccess.createUser();
                iUser.insert(user1);
                iUser.getUser(1);
                IDepartment iDepartment = DataAccess.createDepartment();
                iDepartment.insert(department1);
                iDepartment.getDepartment(1);
                break;
            case R.id.bti_test3_btn:
                User user2 = new User();
                Department department2 = new Department();

                String userdb = sharedPreferences.getString("userdb", "com.cheng.bigtalkdesignpatterns.abstractfactory.AccessUser");
                String departmentdb = sharedPreferences.getString("departmentdb", "com.cheng.bigtalkdesignpatterns.abstractfactory.SqlserverDepartment");

                IUser iUser2 = null;
                IDepartment iDepartment2 = null;
                try {
                    iUser2 = (IUser) Class.forName(userdb).newInstance();
                    iDepartment2 = (IDepartment) Class.forName(departmentdb).newInstance();
                } catch (InstantiationException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
                if (iUser2 == null) return;
                iUser2.insert(user2);
                iUser2.getUser(0);

                if (iDepartment2 == null) return;
                iDepartment2.insert(department2);
                iDepartment2.getDepartment(0);
                break;
        }
    }

}
