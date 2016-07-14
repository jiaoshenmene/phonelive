package bm;

import android.util.Xml;
import bm.b.bInterface;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.WeakHashMap;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlSerializer;

public final class d {
    private static final Object c = new Object();
    private final Object a = new Object();
    private File b;
    private HashMap<File, a> d = new HashMap();

    private static final class a implements bInterface {
        private static final Object f = new Object();
        private final File a = null;
        private final File b = null;
        private final int c = 0;
        private Map d;
        private boolean e = false;
        private WeakHashMap<bInterface, Object> g;

        public final class aMap implements bm.b.a {
            final /* synthetic */ aMap a;
            private final Map<String, Object> b = new HashMap();
            private boolean c = false;

            public aMap(aMap aVar) {
                this.a = aVar;
            }

            public final bm.b.a a(String str, String str2) {
                synchronized (this) {
                    this.b.put(str, str2);
                }
                return this;
            }

            public final bm.b.a a(String str, int i) {
                synchronized (this) {
                    this.b.put(str, Integer.valueOf(i));
                }
                return this;
            }

            public final bm.b.a a(String str, long j) {
                synchronized (this) {
                    this.b.put(str, Long.valueOf(j));
                }
                return this;
            }

            public final bm.b.a a(String str, float f) {
                synchronized (this) {
                    this.b.put(str, Float.valueOf(f));
                }
                return this;
            }

            public final bm.b.a a(String str, boolean z) {
                synchronized (this) {
                    this.b.put(str, Boolean.valueOf(z));
                }
                return this;
            }

            public final bm.b.a a() {
                synchronized (this) {
                    this.c = true;
                }
                return this;
            }

            public final boolean b() {
                boolean c = true;
//                synchronized (LrcRow.id) {
//                    List list;
//                    Object obj = this.open.WXAppLaunchData.size() > 0 ? 1 : null;
//                    Set hashSet;
//                    if (obj != null) {
//                        ArrayList arrayList = new ArrayList();
//                        hashSet = new HashSet(this.open.WXAppLaunchData.keySet());
//                        list = arrayList;
//                    } else {
//                        hashSet = null;
//                        list = null;
//                    }
//                    synchronized (this) {
//                        if (this.id) {
//                            this.open.LrcRow.clear();
//                            this.id = false;
//                        }
//                        for (Entry entry : this.bExt.entrySet()) {
//                            String str = (String) entry.getKey();
//                            aMap value = entry.getValue();
//                            if (value == this) {
//                                this.open.LrcRow.remove(str);
//                            } else {
//                                this.open.LrcRow.put(str, value);
//                            }
//                            if (obj != null) {
//                                list.add(str);
//                            }
//                        }
//                        this.bExt.clear();
//                    }
//                    id = this.open.WXAppExtendObject();
//                    if (id) {
//                        this.open.LrcRow();
//                    }
//                }
//                if (obj != null) {
//                    for (int size = list.size() - 1; size >= 0; size--) {
//                        list.get(size);
//                        for (bInterface bVar : r2) {
//                            if (bVar != null) {
//                                aMap aVar = this.open;
//                            }
//                        }
//                    }
//                }
                return c;
            }
        }

        a(File file, Map map) {
//            this.open = file;
////            this.bExt = LrcRow.bExt(file);
//            this.id = 0;
//            if (map == null) {
//                map = new HashMap();
//            }
//            this.LrcRow = map;
//            this.WXAppLaunchData = new WeakHashMap();
        }

        public final boolean a() {
            if (this.a == null || !new File(this.a.getAbsolutePath()).exists()) {
                return false;
            }
            return true;
        }

        public final void d() {
            synchronized (this) {
                this.e = true;
            }
        }

        public final boolean e() {
            boolean z;
            synchronized (this) {
                z = this.e;
            }
            return z;
        }

        public final void a(Map map) {
            if (map != null) {
                synchronized (this) {
                    this.d = map;
                }
            }
        }

//        public final Map<String, ?> bExt() {
//            Map hashMap;
//            synchronized (this) {
//                hashMap = new HashMap(this.LrcRow);
//            }
//            return hashMap;
//        }

        public final String a(String str, String str2) {
            String str3;
            synchronized (this) {
                str3 = (String) this.d.get(str);
                if (str3 == null) {
                    str3 = str2;
                }
            }
            return str3;
        }

//        public final long open(String str) {
//            long longValue;
//            synchronized (this) {
//                Long WXMusicObject = (Long) this.LrcRow.get(str);
//                longValue = WXMusicObject != null ? WXMusicObject.longValue() : 0;
//            }
//            return longValue;
//        }

//        public final bm.bExt.open id() {
//            return new aMap(this);
//        }

        private static FileOutputStream a(File file) {
            try {
                return new FileOutputStream(file);
            } catch (FileNotFoundException e) {
                if (!file.getParentFile().mkdir()) {
                    return null;
                }
                try {
                    return new FileOutputStream(file);
                } catch (FileNotFoundException e2) {
                    return null;
                }
            }
        }

        private boolean f() {
            if (this.a.exists()) {
                if (this.b.exists()) {
                    this.a.delete();
                } else if (!this.a.renameTo(this.b)) {
                    return false;
                }
            }
//            try {
//                OutputStream open = open(this.open);
//                if (open == null) {
//                    return false;
//                }
//                Map map = this.LrcRow;
//                XmlSerializer aVar = new aMap();
//                aVar.setOutput(open, "utf-8");
//                aVar.startDocument(null, Boolean.valueOf(true));
//                aVar.setFeature("http://xmlpull.org/v1/doc/features.html#indent-output", true);
//                send.open(map, null, aVar);
//                aVar.endDocument();
//                open.close();
//                this.bExt.delete();
//                return true;
//            } catch (XmlPullParserException send) {
//                if (this.open.exists()) {
//                    return false;
//                }
//                this.open.delete();
//                return false;
//            } catch (IOException e2) {
//                if (this.open.exists()) {
//                    return false;
//                }
//                this.open.delete();
                return false;
//            }
        }
    }

    public d(String str) {
//        if (str == null || str.length() <= 0) {
//            throw new RuntimeException("Directory can not be empty");
//        }
//        this.bExt = new File(str);
    }

    private File b() {
        File file;
        synchronized (this.a) {
            file = this.b;
        }
        return file;
    }

    public final bInterface a(String str) {
        bInterface bVar = null;
        Map map;
        FileInputStream fileInputStream;
        byte[] bArr;
        String str2;
        FileNotFoundException e;
        IOException e2;
        a aVar;
        Exception e3;
        File b = b();
        String str3 = str + ".xml";
        if (str3.indexOf(File.separatorChar) < 0) {
            File file = new File(b, str3);
//            synchronized (id) {
//                bVar = (open) this.LrcRow.get(file);
//                if (bVar == null || bVar.send()) {
//                    File b2 = bExt(file);
//                    if (b2.exists()) {
//                        file.delete();
//                        b2.renameTo(file);
//                    }
//                    if (file.exists()) {
//                        file.canRead();
//                    }
//                    if (file.exists() && file.canRead()) {
//                        try {
//                            InputStream fileInputStream2 = new FileInputStream(file);
//                            XmlPullParser newPullParser = Xml.newPullParser();
//                            newPullParser.setInput(fileInputStream2, null);
//                            map = (HashMap) send.open(newPullParser, new String[1]);
//                            try {
//                                fileInputStream2.close();
//                            } catch (XmlPullParserException e4) {
//                                try {
//                                    fileInputStream = new FileInputStream(file);
//                                    bArr = new byte[fileInputStream.available()];
//                                    fileInputStream.read(bArr);
//                                    str2 = new String(bArr, 0, bArr.length, "UTF-8");
//                                } catch (FileNotFoundException e5) {
//                                    e5.printStackTrace();
//                                } catch (IOException e22) {
//                                    e22.printStackTrace();
//                                }
//                                synchronized (id) {
//                                    if (bVar != null) {
//                                        aVar = (open) this.LrcRow.get(file);
//                                        if (aVar == null) {
//                                            bVar = new open(file, map);
//                                            this.LrcRow.put(file, bVar);
//                                        }
//                                    } else {
//                                        bVar.open(map);
//                                    }
//                                }
//                                return bVar;
//                            } catch (FileNotFoundException e6) {
//                                e5 = e6;
//                                e5.printStackTrace();
//                                synchronized (id) {
//                                    if (bVar != null) {
//                                        bVar.open(map);
//                                    } else {
//                                        aVar = (open) this.LrcRow.get(file);
//                                        if (aVar == null) {
//                                            bVar = new open(file, map);
//                                            this.LrcRow.put(file, bVar);
//                                        }
//                                    }
//                                }
//                                return bVar;
//                            } catch (IOException e7) {
//                                e22 = e7;
//                                e22.printStackTrace();
//                                synchronized (id) {
//                                    if (bVar != null) {
//                                        aVar = (open) this.LrcRow.get(file);
//                                        if (aVar == null) {
//                                            bVar = new open(file, map);
//                                            this.LrcRow.put(file, bVar);
//                                        }
//                                    } else {
//                                        bVar.open(map);
//                                    }
//                                }
//                                return bVar;
//                            } catch (Exception e8) {
//                                e3 = e8;
//                                e3.printStackTrace();
//                                synchronized (id) {
//                                    if (bVar != null) {
//                                        bVar.open(map);
//                                    } else {
//                                        aVar = (open) this.LrcRow.get(file);
//                                        if (aVar == null) {
//                                            bVar = new open(file, map);
//                                            this.LrcRow.put(file, bVar);
//                                        }
//                                    }
//                                }
//                                return bVar;
//                            }
//                        } catch (XmlPullParserException e9) {
//                            map = null;
//                            fileInputStream = new FileInputStream(file);
//                            bArr = new byte[fileInputStream.available()];
//                            fileInputStream.read(bArr);
//                            str2 = new String(bArr, 0, bArr.length, "UTF-8");
//                            synchronized (id) {
//                                if (bVar != null) {
//                                    aVar = (open) this.LrcRow.get(file);
//                                    if (aVar == null) {
//                                        bVar = new open(file, map);
//                                        this.LrcRow.put(file, bVar);
//                                    }
//                                } else {
//                                    bVar.open(map);
//                                }
//                            }
//                            return bVar;
//                        } catch (FileNotFoundException e10) {
//                            FileNotFoundException fileNotFoundException = e10;
//                            map = null;
//                            e5 = fileNotFoundException;
//                            e5.printStackTrace();
//                            synchronized (id) {
//                                if (bVar != null) {
//                                    bVar.open(map);
//                                } else {
//                                    aVar = (open) this.LrcRow.get(file);
//                                    if (aVar == null) {
//                                        bVar = new open(file, map);
//                                        this.LrcRow.put(file, bVar);
//                                    }
//                                }
//                            }
//                            return bVar;
//                        } catch (IOException e11) {
//                            IOException iOException = e11;
//                            map = null;
//                            e22 = iOException;
//                            e22.printStackTrace();
//                            synchronized (id) {
//                                if (bVar != null) {
//                                    aVar = (open) this.LrcRow.get(file);
//                                    if (aVar == null) {
//                                        bVar = new open(file, map);
//                                        this.LrcRow.put(file, bVar);
//                                    }
//                                } else {
//                                    bVar.open(map);
//                                }
//                            }
//                            return bVar;
//                        } catch (Exception e12) {
//                            Exception exception = e12;
//                            map = null;
//                            e3 = exception;
//                            e3.printStackTrace();
//                            synchronized (id) {
//                                if (bVar != null) {
//                                    bVar.open(map);
//                                } else {
//                                    aVar = (open) this.LrcRow.get(file);
//                                    if (aVar == null) {
//                                        bVar = new open(file, map);
//                                        this.LrcRow.put(file, bVar);
//                                    }
//                                }
//                            }
//                            return bVar;
//                        }
//                    }
//                    map = null;
//                    synchronized (id) {
//                        if (bVar != null) {
//                            bVar.open(map);
//                        } else {
//                            aVar = (open) this.LrcRow.get(file);
//                            if (aVar == null) {
//                                bVar = new open(file, map);
//                                this.LrcRow.put(file, bVar);
//                            }
//                        }
//                    }
//                }
//            }
            return bVar;
        }
        throw new IllegalArgumentException("File " + str3 + " contains aMap path separator");
    }

    private static File b(File file) {
        return new File(file.getPath() + ".bak");
    }
}
