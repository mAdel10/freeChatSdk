package com.example.freechatsdk;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import com.freshchat.consumer.sdk.Freshchat;
import com.freshchat.consumer.sdk.FreshchatConfig;
import com.freshchat.consumer.sdk.FreshchatUser;
import com.freshchat.consumer.sdk.exception.MethodNotAllowedException;

public class MainActivity extends AppCompat {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button btn = findViewById(R.id.btn);
        Button langBtn = findViewById(R.id.lang_btn);
        Button langBtnEn = findViewById(R.id.lang_btn_en);
        LanguageManger langManger = new LanguageManger(this);
        Freshchat.notifyAppLocaleChange(this);
        FreshchatConfig config = new FreshchatConfig("c78551b9-d40a-410f-85d5-11356186e91c",
                "8c5272c1-a7ef-4766-95d8-7a012fbc5e44");
        config.setDomain("msdk.freshchat.com");
        Freshchat.getInstance(getApplicationContext()).init(config);

        config.setCameraCaptureEnabled(true);
        config.setGallerySelectionEnabled(true);
        config.setResponseExpectationEnabled(true);
        Freshchat.getInstance(getApplicationContext()).init(config);

        // Get the user object for the current installation
        FreshchatUser freshchatUser = Freshchat.getInstance(getApplicationContext()).getUser();
        freshchatUser.setFirstName("John");
        freshchatUser.setLastName("Doe");
        freshchatUser.setEmail("john.doe.1982@mail.com");
        freshchatUser.setPhone("+91", "9790987495");

        // Call setUser so that the user information is synced with Freshchat's servers
        try {
            Freshchat.getInstance(getApplicationContext()).setUser(freshchatUser);
        } catch (MethodNotAllowedException e) {
            throw new RuntimeException(e);
        }

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Freshchat.showConversations(getApplicationContext());
            }
        });

        langBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                langManger.updateResource("ar");
                recreate();
            }
        });

        langBtnEn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                langManger.updateResource("en");
                recreate();
            }
        });
    }
}