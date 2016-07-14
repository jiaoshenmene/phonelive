package ch;

import com.weilian.phonelive.bean.ChatBean;
import com.weilian.phonelive.bean.SendGiftBean;
import com.weilian.phonelive.bean.UserBean;
import java.util.List;
import org.json.JSONObject;

public interface c {
    void a();

    void a(int i);

    void a(ChatBean chatBean);

    void a(ChatBean chatBean, JSONObject jSONObject);

    void a(SendGiftBean sendGiftBean, ChatBean chatBean);

    void a(UserBean userBean, boolean z);

    void a(String str);

    void a(List<UserBean> list, String str);

    void a(JSONObject jSONObject, ChatBean chatBean);

    void a(boolean z);
}
