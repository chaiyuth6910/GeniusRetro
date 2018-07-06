package th.co.itgenius.retro;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

public class ProfileActivity extends AppCompatActivity {

    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        // สร้าง Toolbar
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar); // ใช้ Toolbar แทน Action bar
        getSupportActionBar().setTitle("Profile");

        // แสดงปุ่มย้อนกลับ
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }

    @Override
    public boolean onSupportNavigateUp(){
        finish();
        return true;
    }
}
