package com.weilian.phonelive.widget.music;

import java.util.List;

public interface ILrcView {

    public interface a {
        void a(int i, LrcRow dVar);
    }

    void a(long j);

    void setListener(a aVar);

    void setLrc(List<LrcRow> list);
}
