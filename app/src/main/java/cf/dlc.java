package cf;

import android.support.v4.view.accessibility.AccessibilityNodeInfoCompat;
import com.hyphenate.util.HanziToPinyin.Token;
import java.io.BufferedInputStream;
import java.io.BufferedWriter;
import java.io.Closeable;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.FilterOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.StringWriter;
import java.io.Writer;
import java.lang.reflect.Array;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public final class dlc implements Closeable {
    static final String a = "journal";
    static final String b = "journal.tmp";
    static final String c = "libcore.io.DiskLruCache";
    static final String d = "1";
    static final long e = -1;
    private static final String f = "CLEAN";
    private static final String g = "DIRTY";
    private static final String h = "REMOVE";
    private static final String i = "READ";
    private static final Charset j = Charset.forName("UTF-8");
    private static final int k = 8192;
    private final File l;
    private final File m;
    private final File n;
    private final int o;
    private final long p;
    private final int q;
    private long r = 0;
    private Writer s;
    private final LinkedHashMap<String, Entry> t = new LinkedHashMap(0, 0.75f, true);
    private int u;
    private long v = 0;
    private final ExecutorService w = new ThreadPoolExecutor(0, 1, 60, TimeUnit.SECONDS, new LinkedBlockingQueue());
    private final Callable<Void> x = new Callable<Void>() {
        public  Void call() throws Exception {
            return a();
        }

        public Void a() throws Exception {
            synchronized (dlc.this) {
                if (dlc.this.s == null) {
                } else {
                    dlc.this.m();
                    if (dlc.this.k()) {
                        dlc.this.j();
                        dlc.this.u = 0;
                    }
                }
            }
            return null;
        }
    };

    public final class Editor {
        private final Entry b;
        private boolean c;

        private class FaultOutput extends FilterOutputStream {

            private FaultOutput( OutputStream outputStream) {
                super(outputStream);
            }

            public void write(int i) {
                try {
                    this.out.write(i);
                } catch (IOException e) {
                    c = true;
                }
            }

            public void write(byte[] bArr, int i, int i2) {
                try {
                    this.out.write(bArr, i, i2);
                } catch (IOException e) {
                    c = true;
                }
            }

            public void close() {
                try {
                    this.out.close();
                } catch (IOException e) {
                    c = true;
                }
            }

            public void flush() {
                try {
                    this.out.flush();
                } catch (IOException e) {
                    c = true;
                }
            }
        }

        private Editor( Entry bVar) {
            this.b = bVar;
        }

        public InputStream a(int i) throws IOException {
            InputStream fileInputStream;
            synchronized (dlc.this) {
                if (this.b.e != this) {
                    throw new IllegalStateException();
                } else if (this.b.d) {
                    fileInputStream = new FileInputStream(this.b.a(i));
                } else {
                    fileInputStream = null;
                }
            }
            return fileInputStream;
        }

        public String b(int i) throws IOException {
            InputStream a = a(i);
            return a != null ? dlc.c(a) : null;
        }

        public OutputStream c(int i) throws IOException {
            OutputStream aVar;
            synchronized (dlc.this) {
                if (this.b.e != this) {
                    throw new IllegalStateException();
                }
                aVar = new FaultOutput(new FileOutputStream(this.b.b(i)));
            }
            return aVar;
        }

        public void a(int i, String str) throws IOException {
            OutputStreamWriter outputStreamWriter = null;
            try {
                outputStreamWriter = new OutputStreamWriter(c(i),  Charset.forName("UTF-8"));
                outputStreamWriter.write(str);
            } finally {
                dlc.a(outputStreamWriter);
            }
        }

        public void a() throws IOException {
            if (c) {
                dlc.this.a(this, false);
                dlc.this.c(this.b.b);
                return;
            }
            dlc.this.a(this, true);
        }

        public void b() throws IOException {
            dlc.this.a(this, false);
        }
    }

    private final class Entry {
        private final String b;
        private final long[] c;
        private boolean d;
        private Editor e;
        private long f;

        private Entry( String str) {
            this.b = str;
            this.c = new long[dlc.this.q];
        }

        public String a() throws IOException {
            StringBuilder stringBuilder = new StringBuilder();
            for (long append : this.c) {
                stringBuilder.append(' ').append(append);
            }
            return stringBuilder.toString();
        }

        private void a(String[] strArr) throws IOException {
            if (strArr.length != dlc.this.q) {
                throw b(strArr);
            }
            int i = 0;
            while (i < strArr.length) {
                try {
                    this.c[i] = Long.parseLong(strArr[i]);
                    i++;
                } catch (NumberFormatException e) {
                    throw b(strArr);
                }
            }
        }

        private IOException b(String[] strArr) throws IOException {
            throw new IOException("unexpected journal line: " + Arrays.toString(strArr));
        }

        public File a(int i) {
            return new File(dlc.this.l, this.b + "." + i);
        }

        public File b(int i) {
            return new File(dlc.this.l, this.b + "." + i + ".tmp");
        }
    }

    public final class Snapshot implements Closeable {
        private final String b;
        private final long c;
        private final InputStream[] d;

        private Snapshot( String str, long j, InputStream[] inputStreamArr) {
            this.b = str;
            this.c = j;
            this.d = inputStreamArr;
        }

        public Editor a() throws IOException {
            return dlc.this.a(this.b, this.c);
        }

        public InputStream a(int i) {
            return this.d[i];
        }

        public String b(int i) throws IOException {
            return dlc.this.c(a(i));
        }

        public void close() {
            for (Closeable a : this.d) {
                dlc.this.a(a);
            }
        }
    }

    private static <T> T[] a(T[] tArr, int i, int i2) {
        int length = tArr.length;
        if (i > i2) {
            throw new IllegalArgumentException();
        } else if (i < 0 || i > length) {
            throw new ArrayIndexOutOfBoundsException();
        } else {
            int i3 = i2 - i;
            int min = Math.min(i3, length - i);
            T[] objArr = (T[]) Array.newInstance(tArr.getClass().getComponentType(), i3);
            System.arraycopy(tArr, i, objArr, 0, min);
            return objArr;
        }
    }

    public static String a(Reader reader) throws IOException {
        try {
            StringWriter stringWriter = new StringWriter();
            char[] cArr = new char[AccessibilityNodeInfoCompat.ACTION_NEXT_HTML_ELEMENT];
            while (true) {
                int read = reader.read(cArr);
                if (read == -1) {
                    break;
                }
                stringWriter.write(cArr, 0, read);
            }
            String stringWriter2 = stringWriter.toString();
            return stringWriter2;
        } finally {
            reader.close();
        }
    }

    public static String a(InputStream inputStream) throws IOException {
        int read;
        StringBuilder stringBuilder = new StringBuilder(80);
        while (true) {
            read = inputStream.read();
            if (read == -1) {
                throw new EOFException();
            } else if (read == 10) {
                break;
            } else {
                stringBuilder.append((char) read);
            }
        }
        read = stringBuilder.length();
        if (read > 0 && stringBuilder.charAt(read - 1) == '\r') {
            stringBuilder.setLength(read - 1);
        }
        return stringBuilder.toString();
    }

    public static void a(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (RuntimeException e) {
                throw e;
            } catch (Exception e2) {
            }
        }
    }

    public static void a(File file) throws IOException {
        File[] listFiles = file.listFiles();
        if (listFiles == null) {
            throw new IllegalArgumentException("not FaultOutput directory: " + file);
        }
        int length = listFiles.length;
        int i = 0;
        while (i < length) {
            File file2 = listFiles[i];
            if (file2.isDirectory()) {
                a(file2);
            }
            if (file2.delete()) {
                i++;
            } else {
                throw new IOException("failed to delete file: " + file2);
            }
        }
    }

    private dlc(File file, int i, int i2, long j) {
        this.l = file;
        this.o = i;
        this.m = new File(file, a);
        this.n = new File(file, b);
        this.q = i2;
        this.p = j;
    }

    public static dlc a(File file, int i, int i2, long j) throws IOException {
        if (j <= 0) {
            throw new IllegalArgumentException("maxSize <= 0");
        } else if (i2 <= 0) {
            throw new IllegalArgumentException("valueCount <= 0");
        } else {
            dlc cVar = new dlc(file, i, i2, j);
            if (cVar.m.exists()) {
                try {
                    cVar.h();
                    cVar.i();
                    cVar.s = new BufferedWriter(new FileWriter(cVar.m, true), k);
                    return cVar;
                } catch (IOException e) {
                    cVar.f();
                }
            }
            file.mkdirs();
            cVar = new dlc(file, i, i2, j);
            cVar.j();
            return cVar;
        }
    }

    private void h() throws IOException {
        Closeable bufferedInputStream = new BufferedInputStream(new FileInputStream(this.m), k);
        try {
            String a = a((InputStream) bufferedInputStream);
            String a2 = a((InputStream) bufferedInputStream);
            String a3 = a((InputStream) bufferedInputStream);
            String a4 = a((InputStream) bufferedInputStream);
            String a5 = a((InputStream) bufferedInputStream);
            if (c.equals(a) && d.equals(a2) && Integer.toString(this.o).equals(a3) && Integer.toString(this.q).equals(a4)
                    && "".equals(a5)) {
                while (true) {
                    try {
                        d(a((InputStream) bufferedInputStream));
                    } catch (EOFException e) {
                        a(bufferedInputStream);
                        return;
                    }
                }
            }
            throw new IOException("unexpected journal header: [" + a + ", " + a2 + ", " + a4 + ", " + a5 + "]");
        } catch (Throwable th) {
            a(bufferedInputStream);
        }
    }

    private void d(String str) throws IOException {
        String[] split = str.split(Token.SEPARATOR);
        if (split.length < 2) {
            throw new IOException("unexpected journal line: " + str);
        }
        String str2 = split[1];
        if (split[0].equals(h) && split.length == 2) {
            this.t.remove(str2);
            return;
        }
        Entry bVar;
        Entry bVar2 = (Entry) this.t.get(str2);
        if (bVar2 == null) {
            bVar2 = new Entry( str2);
            this.t.put(str2, bVar2);
            bVar = bVar2;
        } else {
            bVar = bVar2;
        }
        if (split[0].equals(f) && split.length == this.q + 2) {
            bVar.d = true;
            bVar.e = null;
            bVar.a((String[]) a(split, 2, split.length));
        } else if (split[0].equals(g) && split.length == 2) {
            bVar.e = new Editor(bVar);
        } else if (!split[0].equals(i) || split.length != 2) {
            throw new IOException("unexpected journal line: " + str);
        }
    }

    private void i() throws IOException {
        b(this.n);
        Iterator it = this.t.values().iterator();
        while (it.hasNext()) {
            Entry bVar = (Entry) it.next();
            int i;
            if (bVar.e == null) {
                for (i = 0; i < this.q; i++) {
                    this.r += bVar.c[i];
                }
            } else {
                bVar.e = null;
                for (i = 0; i < this.q; i++) {
                    b(bVar.a(i));
                    b(bVar.b(i));
                }
                it.remove();
            }
        }
    }

    private synchronized void j() throws IOException {
        if (this.s != null) {
            this.s.close();
        }
        Writer bufferedWriter = new BufferedWriter(new FileWriter(this.n), k);
        bufferedWriter.write(c);
        bufferedWriter.write("\n");
        bufferedWriter.write(d);
        bufferedWriter.write("\n");
        bufferedWriter.write(Integer.toString(this.o));
        bufferedWriter.write("\n");
        bufferedWriter.write(Integer.toString(this.q));
        bufferedWriter.write("\n");
        bufferedWriter.write("\n");
        for (Entry bVar : this.t.values()) {
            if (bVar.e != null) {
                bufferedWriter.write("DIRTY " + bVar.b + '\n');
            } else {
                bufferedWriter.write("CLEAN " + bVar.b + bVar.a() + '\n');
            }
        }
        bufferedWriter.close();
        this.n.renameTo(this.m);
        this.s = new BufferedWriter(new FileWriter(this.m, true), k);
    }

    private static void b(File file) throws IOException {
        if (file.exists() && !file.delete()) {
            throw new IOException();
        }
    }

    public synchronized Snapshot a(String str) throws IOException {
        Snapshot cVar = null;
        synchronized (this) {
            l();
            e(str);
            Entry bVar = (Entry) this.t.get(str);
            if (bVar != null) {
                if (bVar.d) {
                    InputStream[] inputStreamArr = new InputStream[this.q];
                    int i = 0;
                    while (i < this.q) {
                        try {
                            inputStreamArr[i] = new FileInputStream(bVar.a(i));
                            i++;
                        } catch (FileNotFoundException e) {
                        }
                    }
                    this.u++;
                    this.s.append("READ " + str + '\n');
                    if (k()) {
                        this.w.submit(this.x);
                    }
                    cVar = new Snapshot(str, bVar.f, inputStreamArr);
                }
            }
        }
        return cVar;
    }

    public Editor b(String str) throws IOException {
        return a(str, (long) e);
    }

    private synchronized Editor a(String str, long j) throws IOException {
        Editor aVar;
        l();
        e(str);
        Entry bVar = (Entry) this.t.get(str);
        if (j == e || (bVar != null && bVar.f == j)) {
            if (bVar == null) {
                bVar = new Entry(str);
                this.t.put(str, bVar);
            } else if (bVar.e != null) {
                return null;
            }
            aVar = new Editor(bVar);
            bVar.e = aVar;
            this.s.write("DIRTY " + str + '\n');
            this.s.flush();
        } else {
            aVar = null;
        }
        return aVar;
    }

    public File a() {
        return this.l;
    }

    public long b() {
        return this.p;
    }

    public synchronized long c() {
        return this.r;
    }

    private synchronized void a(Editor aVar, boolean z) throws IOException {
        int i = 0;
        synchronized (this) {
            Entry a = aVar.b;
            if (a.e != aVar) {
                throw new IllegalStateException();
            }
            if (z) {
                if (!a.d) {
                    int i2 = 0;
                    while (i2 < this.q) {
                        if (a.b(i2).exists()) {
                            i2++;
                        } else {
                            aVar.b();
                            throw new IllegalStateException("edit didn'readyState create file " + i2);
                        }
                    }
                }
            }
            while (i < this.q) {
                File b = a.b(i);
                if (!z) {
                    b(b);
                } else if (b.exists()) {
                    File a2 = a.a(i);
                    b.renameTo(a2);
                    long j = a.c[i];
                    long length = a2.length();
                    a.c[i] = length;
                    this.r = (this.r - j) + length;
                }
                i++;
            }
            this.u++;
            a.e = null;
            if (a.d | z) {
                a.d = true;
                this.s.write("CLEAN " + a.b + a.a() + '\n');
                if (z) {
                    long j2 = this.v;
                    this.v = 1 + j2;
                    a.f = j2;
                }
            } else {
                this.t.remove(a.b);
                this.s.write("REMOVE " + a.b + '\n');
            }
            if (this.r > this.p || k()) {
                this.w.submit(this.x);
            }
        }
    }

    private boolean k() {
        return this.u >= 2000 && this.u >= this.t.size();
    }

    public synchronized boolean c(String str) throws IOException {
        boolean z;
        int i = 0;
        synchronized (this) {
            l();
            e(str);
            Entry bVar = (Entry) this.t.get(str);
            if (bVar == null || bVar.e != null) {
                z = false;
            } else {
                while (i < this.q) {
                    File a = bVar.a(i);
                    if (a.delete()) {
                        this.r -= bVar.c[i];
                        bVar.c[i] = 0;
                        i++;
                    } else {
                        throw new IOException("failed to delete " + a);
                    }
                }
                this.u++;
                this.s.append("REMOVE " + str + '\n');
                this.t.remove(str);
                if (k()) {
                    this.w.submit(this.x);
                }
                z = true;
            }
        }
        return z;
    }

    public boolean d() {
        return this.s == null;
    }

    private void l() {
        if (this.s == null) {
            throw new IllegalStateException("cache is closed");
        }
    }

    public synchronized void e() throws IOException {
        l();
        m();
        this.s.flush();
    }

    public synchronized void close() throws IOException {
        if (this.s != null) {
            Iterator it = new ArrayList(this.t.values()).iterator();
            while (it.hasNext()) {
                Entry bVar = (Entry) it.next();
                if (bVar.e != null) {
                    bVar.e.b();
                }
            }
            m();
            this.s.close();
            this.s = null;
        }
    }

    private void m() throws IOException {
        while (this.r > this.p) {
            c((String) ((java.util.Map.Entry) this.t.entrySet().iterator().next()).getKey());
        }
    }

    public void f() throws IOException {
        close();
        a(this.l);
    }

    private void e(String str) {
        if (str.contains(Token.SEPARATOR) || str.contains("\n") || str.contains("\r")) {
            throw new IllegalArgumentException("keys must not contain spaces or newlines: \"" + str + "\"");
        }
    }

    private static String c(InputStream inputStream) throws IOException {
        return a(new InputStreamReader(inputStream, j));
    }
}
