package com.cheng.networkframestudy.retrofit.model;

/**
 * Created by Android on 2016/3/13.
 */
public class TelphoneOwnershipOfLand {

    /**
     * errNum : 0
     * errMsg : success
     * retData : {"telString":"15846530170","province":"黑龙江","carrier":"黑龙江移动"}
     */

    private int errNum;
    private String errMsg;
    /**
     * telString : 15846530170
     * province : 黑龙江
     * carrier : 黑龙江移动
     */

    private RetDataEntity retData;

    public void setErrNum(int errNum) {
        this.errNum = errNum;
    }

    public void setErrMsg(String errMsg) {
        this.errMsg = errMsg;
    }

    public void setRetData(RetDataEntity retData) {
        this.retData = retData;
    }

    public int getErrNum() {
        return errNum;
    }

    public String getErrMsg() {
        return errMsg;
    }

    public RetDataEntity getRetData() {
        return retData;
    }

    public static class RetDataEntity {
        private String telString;
        private String province;
        private String carrier;

        public void setTelString(String telString) {
            this.telString = telString;
        }

        public void setProvince(String province) {
            this.province = province;
        }

        public void setCarrier(String carrier) {
            this.carrier = carrier;
        }

        public String getTelString() {
            return telString;
        }

        public String getProvince() {
            return province;
        }

        public String getCarrier() {
            return carrier;
        }

        @Override
        public String toString() {
            return "RetDataEntity{" +
                    "telString='" + telString + '\'' +
                    ", province='" + province + '\'' +
                    ", carrier='" + carrier + '\'' +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "TelphoneOwnershipOfLand{" +
                "errNum=" + errNum +
                ", errMsg='" + errMsg + '\'' +
                ", retData=" + retData +
                '}';
    }
}
