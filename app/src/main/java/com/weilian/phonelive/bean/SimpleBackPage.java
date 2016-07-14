package com.weilian.phonelive.bean;

import com.weilian.phonelive.R;
import com.weilian.phonelive.fragment.BlackListFragment;
import com.weilian.phonelive.fragment.HotFragment;
import com.weilian.phonelive.fragment.MessageDetailFragment;
import com.weilian.phonelive.fragment.PushManageFragment;
import com.weilian.phonelive.fragment.SearchFragment;
import com.weilian.phonelive.fragment.SearchMusicFragment;
import com.weilian.phonelive.fragment.SelectAreaFragment;
import com.weilian.phonelive.viewpagerfragment.PrivateChatCorePagerFragment;

public enum SimpleBackPage {
    USER_FAVORITE(1, 2, HotFragment.class),
    USER_PRIVATECORE(2, R.string.privatechat, PrivateChatCorePagerFragment.class),
    USER_PRIVATECORE_MESSAGE(3, R.string.privatechat, MessageDetailFragment.class),
    AREA_SELECT(4, R.string.area, SelectAreaFragment.class),
    INDEX_SECREEN(5, R.string.search, SearchFragment.class),
    USER_BLACK_LIST(6, R.string.blacklist, BlackListFragment.class),
    USER_PUSH_MANAGE(7, R.string.push, PushManageFragment.class),
    LIVE_START_MUSIC(8, R.string.diange, SearchMusicFragment.class);
    
    private Class<?> clz;
    private int title;
    private int value;

    private SimpleBackPage(int i, int i2, Class<?> cls) {
        this.value = i;
        this.title = i2;
        this.clz = cls;
    }

    public int getTitle() {
        return this.title;
    }

    public void setTitle(int i) {
        this.title = i;
    }

    public Class<?> getClz() {
        return this.clz;
    }

    public void setClz(Class<?> cls) {
        this.clz = cls;
    }

    public int getValue() {
        return this.value;
    }

    public void setValue(int i) {
        this.value = i;
    }

    public static SimpleBackPage getPageByValue(int i) {
        for (SimpleBackPage simpleBackPage : values()) {
            if (simpleBackPage.getValue() == i) {
                return simpleBackPage;
            }
        }
        return null;
    }
}
