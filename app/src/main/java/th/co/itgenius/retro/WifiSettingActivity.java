package th.co.itgenius.retro;

import android.content.Context;
import android.net.wifi.WifiManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.CompoundButton;
import android.widget.Switch;


public class WifiSettingActivity extends AppCompatActivity {

    Toolbar toolbar;
    Switch aSwitch;
    WifiManager wifimanager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wifi_setting);

        // สร้าง Toolbar
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar); // ใช้ Toolbar แทน Action bar
        getSupportActionBar().setTitle("Setting Wifi");

        // แสดงปุ่มย้อนกลับ
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        aSwitch = findViewById(R.id.settingwifi);
        aSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                Log.v("Switch State=", ""+isChecked);

            }
        });

    }

    @Override
    public boolean onSupportNavigateUp(){
        finish();
        return true;
    }


}
