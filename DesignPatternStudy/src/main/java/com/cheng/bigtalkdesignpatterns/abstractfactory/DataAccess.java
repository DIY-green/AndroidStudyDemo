package com.cheng.bigtalkdesignpatterns.abstractfactory;

/**
 * Created by Administrator on 2015/12/21.
 */
public class DataAccess {
    private static final String db = "Sqlserver";
//    private static final String db = "Access";

    public static IUser createUser() {
        IUser result = null;
        switch (db) {
            case "Sqlserver":
                result = new SqlserverUser();
                break;
            case "Access":
                result = new AccessUser();
                break;
        }
        return result;
    }

    public static IDepartment createDepartment() {
        IDepartment result = null;
        switch (db) {
            case "Sqlserver":
                result = new SqlserverDepartment();
                break;
            case "Access":
                result = new AccessDepartment();
                break;
        }
        return result;
    }
}
