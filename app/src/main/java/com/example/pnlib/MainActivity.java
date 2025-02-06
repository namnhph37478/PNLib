package com.example.pnlib;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.navigation_view);

        // Hiển thị mặc định fragment Quản lý Phiếu Mượn khi mở ứng dụng
        if (savedInstanceState == null) {
            loadFragment(new ManageLoansFragment());
            navigationView.setCheckedItem(R.id.nav_phieu_muon);
        }

        // Xử lý khi nhấn vào menu Navigation Drawer
        navigationView.setNavigationItemSelectedListener(item -> {
            Fragment selectedFragment = null;
            String title = "PNLib - Quản lý thư viện";

            switch (item.getItemId()) {
                case R.id.nav_phieu_muon:
                    selectedFragment = new ManageLoansFragment();
                    title = "Quản lý Phiếu Mượn";
                    break;
                case R.id.nav_thanh_vien:
                    selectedFragment = new ManageMembersFragment();
                    title = "Quản lý Thành Viên";
                    break;
                case R.id.nav_sach:
                    selectedFragment = new ManageBooksFragment();
                    title = "Quản lý Sách";
                    break;
                case R.id.nav_thong_ke:
                    selectedFragment = new StatisticsFragment();
                    title = "Thống kê";
                    break;
                case R.id.nav_doi_mat_khau:
                    // Điều hướng đến màn hình đổi mật khẩu
                    break;
                case R.id.nav_dang_xuat:
                    // Xử lý đăng xuất
                    break;
            }

            if (selectedFragment != null) {
                loadFragment(selectedFragment);
                setTitle(title);
            }
            drawerLayout.closeDrawers();
            return true;
        });

        // Thêm nút mở menu Drawer
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
    }

    private void loadFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.main_fragment_container, fragment);
        transaction.commit();
    }
}