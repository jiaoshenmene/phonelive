package bq;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;

import com.ksyun.media.player.stats.StatConstant;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public final class a implements SharedPreferences {
    private final ContentResolver a;
    private final String[] b = new String[]{StatConstant.PLAYER_ID, "key", StatConstant.LOG_TYPE, "value"};
    private final HashMap<String, Object> c = new HashMap();
    private aEditor d = null;

    private static class aEditor implements Editor {
        private Map<String, Object> a = new HashMap();
        private Set<String> b = new HashSet();
        private boolean c = false;
        private ContentResolver d;

        public aEditor(ContentResolver contentResolver) {
            this.d = contentResolver;
        }

        public final void apply() {
        }

        public final Editor clear() {
            this.c = true;
            return this;
        }

        public final boolean commit() {
            ContentValues contentValues = new ContentValues();
            if (this.c) {
                this.d.delete(bu.a.b.a, null, null);
                this.c = false;
            }
            for (String str : this.b) {
                this.d.delete(bu.a.b.a, "key = ?", new String[]{str});
            }
            for (Entry value : this.a.entrySet()) {
                int i;
                boolean z;
                Object value2 = value.getValue();
                if (value2 == null) {
                    bt.a.a("MicroMsg.SDK.PluginProvider.Resolver", "unresolve failed, null value");
                    i = 0;
                } else if (value2 instanceof Integer) {
                    i = 1;
                } else if (value2 instanceof Long) {
                    i = 2;
                } else if (value2 instanceof String) {
                    i = 3;
                } else if (value2 instanceof Boolean) {
                    i = 4;
                } else if (value2 instanceof Float) {
                    i = 5;
                } else if (value2 instanceof Double) {
                    i = 6;
                } else {
                    bt.a.a("MicroMsg.SDK.PluginProvider.Resolver", "unresolve failed, unknown type=" + value2.getClass().toString());
                    i = 0;
                }
                if (i == 0) {
                    z = false;
                } else {
                    contentValues.put(StatConstant.LOG_TYPE, Integer.valueOf(i));
                    contentValues.put("value", value2.toString());
                    z = true;
                }
                if (z) {
                    this.d.update(bu.a.b.a, contentValues, "key = ?", new String[]{(String) value.getKey()});
                }
            }
            return true;
        }

        public final Editor putBoolean(String str, boolean z) {
            this.a.put(str, Boolean.valueOf(z));
            this.b.remove(str);
            return this;
        }

        public final Editor putFloat(String str, float f) {
            this.a.put(str, Float.valueOf(f));
            this.b.remove(str);
            return this;
        }

        public final Editor putInt(String str, int i) {
            this.a.put(str, Integer.valueOf(i));
            this.b.remove(str);
            return this;
        }

        public final Editor putLong(String str, long j) {
            this.a.put(str, Long.valueOf(j));
            this.b.remove(str);
            return this;
        }

        public final Editor putString(String str, String str2) {
            this.a.put(str, str2);
            this.b.remove(str);
            return this;
        }

        public final Editor putStringSet(String str, Set<String> set) {
            return null;
        }

        public final Editor remove(String str) {
            this.b.add(str);
            return this;
        }
    }

    public a(Context context) {
        this.a = context.getContentResolver();
    }

    private Object a(String str) {
        try {
            Cursor query = this.a.query(bu.a.b.a, this.b, "key = ?", new String[]{str}, null);
            if (query == null) {
                return null;
            }
            Object a = query.moveToFirst() ? bu.a.aResovler.a(query.getInt(query.getColumnIndex(StatConstant.LOG_TYPE)), query.getString(query.getColumnIndex("value"))) : null;
            query.close();
            return a;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public final boolean contains(String str) {
        return a(str) != null;
    }

    public final Editor edit() {
        if (this.d == null) {
            this.d = new aEditor(this.a);
        }
        return this.d;
    }

    public final Map<String, ?> getAll() {
        try {
            Cursor query = this.a.query(bu.a.b.a, this.b, null, null, null);
            if (query == null) {
                return null;
            }
            int columnIndex = query.getColumnIndex("key");
            int columnIndex2 = query.getColumnIndex(StatConstant.LOG_TYPE);
            int columnIndex3 = query.getColumnIndex("value");
            while (query.moveToNext()) {
                this.c.put(query.getString(columnIndex), bu.a.aResovler.a(query.getInt(columnIndex2), query.getString(columnIndex3)));
            }
            query.close();
            return this.c;
        } catch (Exception e) {
            e.printStackTrace();
            return this.c;
        }
    }

    public final boolean getBoolean(String str, boolean z) {
        Object a = a(str);
        return (a == null || !(a instanceof Boolean)) ? z : ((Boolean) a).booleanValue();
    }

    public final float getFloat(String str, float f) {
        Object a = a(str);
        return (a == null || !(a instanceof Float)) ? f : ((Float) a).floatValue();
    }

    public final int getInt(String str, int i) {
        Object a = a(str);
        return (a == null || !(a instanceof Integer)) ? i : ((Integer) a).intValue();
    }

    public final long getLong(String str, long j) {
        Object a = a(str);
        return (a == null || !(a instanceof Long)) ? j : ((Long) a).longValue();
    }

    public final String getString(String str, String str2) {
        Object a = a(str);
        return (a == null || !(a instanceof String)) ? str2 : (String) a;
    }

    public final Set<String> getStringSet(String str, Set<String> set) {
        return null;
    }

    public final void registerOnSharedPreferenceChangeListener(OnSharedPreferenceChangeListener onSharedPreferenceChangeListener) {
    }

    public final void unregisterOnSharedPreferenceChangeListener(OnSharedPreferenceChangeListener onSharedPreferenceChangeListener) {
    }
}
