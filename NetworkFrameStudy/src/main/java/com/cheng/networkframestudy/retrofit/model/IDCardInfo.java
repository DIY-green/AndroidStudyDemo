package com.cheng.networkframestudy.retrofit.model;

/**
 * Created by Android on 2016/3/20.
 */
public class IDCardInfo {

    /**
     * errNum : 0
     * retMsg : success
     * retData : {"sex":"M","birthday":"1987-04-20","address":"湖北省孝感市汉川市"}
     */

    private int errNum;
    private String retMsg;
    /**
     * sex : M
     * birthday : 1987-04-20
     * address : 湖北省孝感市汉川市
     */

    private RetDataEntity retData;

    public void setErrNum(int errNum) {
        this.errNum = errNum;
    }

    public void setRetMsg(String retMsg) {
        this.retMsg = retMsg;
    }

    public void setRetData(RetDataEntity retData) {
        this.retData = retData;
    }

    public int getErrNum() {
        return errNum;
    }

    public String getRetMsg() {
        return retMsg;
    }

    public RetDataEntity getRetData() {
        return retData;
    }

    public static class RetDataEntity {
        private String sex;
        private String birthday;
        private String address;

        public void setSex(String sex) {
            this.sex = sex;
        }

        public void setBirthday(String birthday) {
            this.birthday = birthday;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getSex() {
            return sex;
        }

        public String getBirthday() {
            return birthday;
        }

        public String getAddress() {
            return address;
        }
    }
}
