package com.lzfutil.util;

import com.amap.api.location.AMapLocation;

public class AmapUtil {
    public static final int a = 0;
    public static final int b = 1;
    public static final int c = 2;
    public static final String d = "URL";
    public static final String e = "file:///android_asset/location.html";

    public static synchronized String a(AMapLocation aMapLocation) {
        String str;
        synchronized (AmapUtil.class) {
            if (aMapLocation == null) {
                str = null;
            } else {
                StringBuffer stringBuffer = new StringBuffer();
                if (aMapLocation.getErrorCode() == 0) {
                    stringBuffer.append("\u5b9a\u4f4d\u6210\u529f\n");
                    stringBuffer.append("\u5b9a\u4f4d\u7c7b\u578b: " + aMapLocation.getLocationType() + "\n");
                    stringBuffer.append("\u7ecf    \u5ea6    : " + aMapLocation.getLongitude() + "\n");
                    stringBuffer.append("\u7eac    \u5ea6    : " + aMapLocation.getLatitude() + "\n");
                    stringBuffer.append("\u7cbe    \u5ea6    : " + aMapLocation.getAccuracy() + "\u7c73" + "\n");
                    stringBuffer.append("\u63d0\u4f9b\u8005    : " + aMapLocation.getProvider() + "\n");
                    if (aMapLocation.getProvider().equalsIgnoreCase("gps")) {
                        stringBuffer.append("\u901f    \u5ea6    : " + aMapLocation.getSpeed() + "\u7c73/\u79d2" + "\n");
                        stringBuffer.append("\u89d2    \u5ea6    : " + aMapLocation.getBearing() + "\n");
                        stringBuffer.append("\u661f    \u6570    : " + aMapLocation.getSatellites() + "\n");
                    } else {
                        stringBuffer.append("\u56fd    \u5bb6    : " + aMapLocation.getCountry() + "\n");
                        stringBuffer.append("\u7701            : " + aMapLocation.getProvince() + "\n");
                        stringBuffer.append("\u5e02            : " + aMapLocation.getCity() + "\n");
                        stringBuffer.append("\u57ce\u5e02\u7f16\u7801 : " + aMapLocation.getCityCode() + "\n");
                        stringBuffer.append("\u533a            : " + aMapLocation.getDistrict() + "\n");
                        stringBuffer.append("\u533a\u57df \u7801   : " + aMapLocation.getAdCode() + "\n");
                        stringBuffer.append("\u5730    \u5740    : " + aMapLocation.getAddress() + "\n");
                        stringBuffer.append("\u5174\u8da3\u70b9    : " + aMapLocation.getPoiName() + "\n");
                    }
                } else {
                    stringBuffer.append("\u5b9a\u4f4d\u5931\u8d25\n");
                    stringBuffer.append("\u9519\u8bef\u7801:" + aMapLocation.getErrorCode() + "\n");
                    stringBuffer.append("\u9519\u8bef\u4fe1\u606f:" + aMapLocation.getErrorInfo() + "\n");
                    stringBuffer.append("\u9519\u8bef\u63cf\u8ff0:" + aMapLocation.getLocationDetail() + "\n");
                }
                str = stringBuffer.toString();
            }
        }
        return str;
    }
}
