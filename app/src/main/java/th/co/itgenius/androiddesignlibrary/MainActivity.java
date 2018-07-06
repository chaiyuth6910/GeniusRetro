package th.co.itgenius.androiddesignlibrary;

import android.content.Intent;
import android.content.res.Configuration;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.transition.Slide;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    DrawerLayout drawerLayout;
    ActionBarDrawerToggle actionBarDrawerToggle;
    NavigationView navigationView;
    TabLayout tabLayout;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // สร้าง Toolbar
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar); // ใช้ Toolbar แทน Action bar
        getSupportActionBar().setTitle(R.string.home_title);

        // NavigationView
        navigationView = findViewById(R.id.navigation_view);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.navprofile:

                        Toast.makeText(MainActivity.this,"OK",Toast.LENGTH_SHORT).show();
                        Intent intentprofile = new Intent(MainActivity.this, ProfileActivity.class);
                        startActivity(intentprofile);

                        drawerLayout.closeDrawer(Gravity.LEFT);

                        break;
                }
                return true;
            }
        });

        // Drawer Menu
        drawerLayout = findViewById(R.id.drawerLayout);
        actionBarDrawerToggle = new ActionBarDrawerToggle(MainActivity.this, drawerLayout, 0, 0);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);

        // เปิดแสดงปุ่ม Home
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // การใส่แท็บลงไป
        tabLayout = findViewById(R.id.tab_layout);
        tabLayout.addTab(tabLayout.newTab().setText(R.string.home_title));
        tabLayout.addTab(tabLayout.newTab().setText(R.string.course_title));
        tabLayout.addTab(tabLayout.newTab().setText(R.string.product_title));
        tabLayout.addTab(tabLayout.newTab().setText(R.string.article_title));
        tabLayout.addTab(tabLayout.newTab().setText(R.string.contact_title));

        // กำหนดให้แสดง tabs เต็มหน้าจอ
        tabLayout.setTabGravity(tabLayout.GRAVITY_FILL);

        // Custom Tabs
        setupTabIcons();

        // เรียกใช้งาน ViewPager เพื่อทำการโหลด fragment
        final ViewPager viewPager = findViewById(R.id.pager);
        final PagerAdapter adapter = new PagerAdapter(getSupportFragmentManager(),tabLayout.getTabCount());

        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

        // เมือคลิ๊กเลือก tab ให้ทำการเปลี่ยน fragment
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {

                switch (tab.getPosition()) {
                    case 0:
                        viewPager.setCurrentItem(0);
                        toolbar.setTitle(R.string.home_title);
                        break;
                    case 1:
                        viewPager.setCurrentItem(1);
                        toolbar.setTitle(R.string.course_title);
                        break;
                    case 2:
                        viewPager.setCurrentItem(2);
                        toolbar.setTitle(R.string.product_title);
                        break;
                    case 3:
                        viewPager.setCurrentItem(3);
                        toolbar.setTitle(R.string.article_title);
                        break;
                    case 4:
                        viewPager.setCurrentItem(4);
                        toolbar.setTitle(R.string.contact_title);
                        break;
                    default:
                        viewPager.setCurrentItem(tab.getPosition());
                        toolbar.setTitle(R.string.home_title);
                        break;
                }

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

    } // onCreate()

    // Event  ในการกดปุ่ม home เพื่อเรียกตัว drawer menu ด้านข้าง
    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        actionBarDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        actionBarDrawerToggle.onConfigurationChanged(newConfig);
    }

    // ฟังก์ชันในการเรียกใช้เมนูที่เป็น option menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        // menu hamberger
        if(actionBarDrawerToggle.onOptionsItemSelected(item)){
            return  true;
        }

        // Handle menu (event)
        switch (item.getItemId()){
            case R.id.search :
                Toast.makeText(MainActivity.this,"Click Search",Toast.LENGTH_SHORT).show();
                break;
            case R.id.wifi :
                //Toast.makeText(MainActivity.this,"Click Setting Wifi",Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(MainActivity.this, WifiSettingActivity.class);
                startActivity(intent);
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    // สร้างฟังก์ชันในการเพิ่ม icon ลงใน tabs
    public void  setupTabIcons()
    {
        // Tab 1
        TextView tabOne = (TextView) LayoutInflater.from(this).inflate(R.layout.custom_tab, null);
        tabOne.setText(R.string.home_title);
        tabOne.setCompoundDrawablesWithIntrinsicBounds(0,R.drawable.ic_place,0,0); // ตำแหน่งของรูปไอคอน
        tabLayout.getTabAt(0).setCustomView(tabOne);

        // Tab 2
        TextView tabTwo = (TextView) LayoutInflater.from(this).inflate(R.layout.custom_tab, null);
        tabTwo.setText(R.string.course_title);
        tabTwo.setCompoundDrawablesWithIntrinsicBounds(0,R.drawable.ic_attachment,0,0); // ตำแหน่งของรูปไอคอน
        tabLayout.getTabAt(1).setCustomView(tabTwo);

        // Tab 3
        TextView tabThree = (TextView) LayoutInflater.from(this).inflate(R.layout.custom_tab, null);
        tabThree.setText(R.string.product_title);
        tabThree.setCompoundDrawablesWithIntrinsicBounds(0,R.drawable.ic_emoticon,0,0); // ตำแหน่งของรูปไอคอน
        tabLayout.getTabAt(2).setCustomView(tabThree);

        // Tab 4
        TextView tabFour = (TextView) LayoutInflater.from(this).inflate(R.layout.custom_tab, null);
        tabFour.setText(R.string.article_title);
        tabFour.setCompoundDrawablesWithIntrinsicBounds(0,R.drawable.ic_image,0,0); // ตำแหน่งของรูปไอคอน
        tabLayout.getTabAt(3).setCustomView(tabFour);

        // Tab 5
        TextView tabFive = (TextView) LayoutInflater.from(this).inflate(R.layout.custom_tab, null);
        tabFive.setText(R.string.contact_title);
        tabFive.setCompoundDrawablesWithIntrinsicBounds(0,R.drawable.ic_done,0,0); // ตำแหน่งของรูปไอคอน
        tabLayout.getTabAt(4).setCustomView(tabFive);

    }


} // class{}
