package com.weilian.phonelive.widget.music;

import android.util.Log;
import com.lzfutil.util.t;
import java.util.ArrayList;
import java.util.List;

public class LrcRow implements Comparable<LrcRow> {
    public static final String a = "LrcRow";
    public long b;
    public String c;
    public String d;

    public LrcRow(String str, long j, String str2) {
        this.d = str;
        this.b = j;
        this.c = str2;
        Log.d(a, "strTime:" + str + " time:" + j + " content:" + str2);
    }

    public static List<LrcRow> createRows(String str) {
        try {
            if (str.indexOf("[") != 0 || str.indexOf("]") != 9) {
                return null;
            }
            int lastIndexOf = str.lastIndexOf("]");
            String substring = str.substring(lastIndexOf + 1, str.length());
            String[] split = str.substring(0, lastIndexOf + 1).replace("[", "-").replace("]", "-").split("-");
            List<LrcRow> arrayList = new ArrayList();
            for (String str2 : split) {
                if (str2.trim().length() != 0) {
                    LrcRow dVar = new LrcRow(str2, b(str2), substring);
                    t.c(dVar.b + "\u521d\u59cb\u5316");
                    arrayList.add(dVar);
                }
            }
            return arrayList;
        } catch (Exception e) {
            Log.e(a, "createRows exception:" + e.getMessage());
            return null;
        }
    }

    private static long b(String str) {
        String[] split = str.replace('.', ':').split(":");
        return (long) (Integer.valueOf(split[2]).intValue() + (((Integer.valueOf(split[0]).intValue() * 60) * 1000) + (Integer.valueOf(split[1]).intValue() * 1000)));
    }

    public int createRows(LrcRow dVar) {
        return (int) (this.b - dVar.b);
    }

    @Override
    public int compareTo(LrcRow another) {
        return createRows((LrcRow) another);
    }
}
