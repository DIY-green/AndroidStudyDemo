package com.cheng.utils;

/**
 * 根据经纬度查询地址信息和根据地址信息查询经纬度
 */
public final class GoogleMapLocationUtil {

    static {
        Logger.TAG = "GoogleMapLocationUtil";
    }
    /**
     * Don't let anyone instantiate this class.
     */
    private GoogleMapLocationUtil() {
        throw new Error("Do not need instantiate!");
    }

    /**
     * 根据地址获取对应的经纬度
     *
     * @param address 地址信息
     * @return 经纬度数组
     */
    /*public static double[] getLocationInfo(String address) {
        if (TextUtils.isEmpty(address)) {
            return null;
        }
        Logger.e("address : " + address);
        // 定义一个HttpClient，用于向指定地址发送请求
        HttpClient client = new DefaultHttpClient();
        // 向指定地址发送GET请求
        HttpGet httpGet = new HttpGet("http://maps.google."
                + "com/maps/api/geocode/json?address=" + address
                + "ka&sensor=false");
        StringBuilder sb = new StringBuilder();
        try {
            // 获取服务器的响应
            HttpResponse response = client.execute(httpGet);
            HttpEntity entity = response.getEntity();
            // 获取服务器响应的输入流
            InputStream stream = entity.getContent();
            int b;
            // 循环读取服务器响应
            while ((b = stream.read()) != -1) {
                sb.append((char) b);
            }
            // 将服务器返回的字符串转换为JSONObject对象
            JSONObject jsonObject = new JSONObject(sb.toString());
            // 从JSONObject对象中取出代表位置的location属性
            JSONObject location = jsonObject.getJSONArray("results")
                    .getJSONObject(0).getJSONObject("geometry")
                    .getJSONObject("location");
            // 获取经度信息
            double longitude = location.getDouble("lng");
            // 获取纬度信息
            double latitude = location.getDouble("lat");
            Logger.e("location : (" + longitude + "," + latitude + ")");
            // 将经度、纬度信息组成double[]数组
            return new double[]{longitude, latitude};
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }*/

    /**
     * 根据经纬度获取对应的地址
     *
     * @param longitude 经度
     * @param latitude  纬度
     * @param lang      语言 如果位空则默认en
     * @return 地址信息
     * @throws Exception
     */
    /*public static String getAddress(double longitude, double latitude,
                                    String lang) throws Exception {
        Logger.e("location : (" + longitude + "," + latitude + ")");
        if (lang == null) {
            lang = "en";
        }
        // 设定请求的超时时间
        HttpParams params = new BasicHttpParams();
        HttpConnectionParams.setConnectionTimeout(params, 10 * 1000);
        HttpConnectionParams.setSoTimeout(params, 10 * 1000);
        // 定义一个HttpClient，用于向指定地址发送请求
        HttpClient client = new DefaultHttpClient(params);
        // 向指定地址发送GET请求
        HttpGet httpGet = new HttpGet("https://maps.googleapis.com/maps/api/"
                + "geocode/json?latlng=" + latitude + "," + longitude
                + "&sensor=false&language=" + lang);
        if (DEBUG) {
            LogUtils.d(TAG,
                    "URL : " + httpGet.getURI());
        }
        StringBuilder sb = new StringBuilder();
        // 执行请求
        HttpResponse response = client.execute(httpGet);
        HttpEntity entity = response.getEntity();
        // 获取服务器响应的字符串
        InputStream stream = entity.getContent();
        int b;
        while ((b = stream.read()) != -1) {
            sb.append((char) b);
        }
        // 把服务器相应的字符串转换为JSONObject
        JSONObject jsonObj = new JSONObject(sb.toString());
        Log.d("ConvertUtil", "getAddress:" + sb.toString());
        // 解析出响应结果中的地址数据
        JSONObject addressObject = jsonObj.getJSONArray("results")
                .getJSONObject(0);
        String address = decodeLocationName(addressObject);
        Logger.e("address : " + address);
        return address;
    }*/

    /**
     * 根据Google API 解析出国家和城市名称
     * https://developers.google.com/maps/documentation/geocoding
     *
     * @param jsonObject 地址Json对象
     * @return 返回国家和城市
     */
    /*public static String decodeLocationName(JSONObject jsonObject) {
        JSONArray jsonArray;
        String country = "", city = "";
        String TYPE_COUNTRY = "country";
        String TYPE_LOCALITY = "locality";
        String TYPE_POLITICAL = "political";
        boolean hasCountry = false;
        try {
            jsonArray = jsonObject.getJSONArray("address_components");
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jo = jsonArray.getJSONObject(i);
                JSONArray types = jo.getJSONArray("types");
                boolean hasLocality = false, hasPolicical = false;

                for (int j = 0; j < types.length(); j++) {
                    String type = types.getString(j);
                    if (type.equals(TYPE_COUNTRY) && !hasCountry) {
                        country = jo.getString("long_name");
                    } else {
                        if (type.equals(TYPE_POLITICAL)) {
                            hasPolicical = true;
                        }
                        if (type.equals(TYPE_LOCALITY)) {
                            hasLocality = true;
                        }
                        if (hasPolicical && hasLocality) {
                            city = jo.getString("long_name");
                        }
                    }
                }
            }
            return city + "," + country;
        } catch (JSONException e) {
            Logger.e(e);
        }
        if (jsonObject.has("formatted_address")) {
            try {
                return jsonObject.getString("formatted_address");
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return null;
    }*/
}
