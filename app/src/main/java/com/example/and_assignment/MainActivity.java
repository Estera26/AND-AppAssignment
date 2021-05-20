package com.example.and_assignment;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.and_assignment.viewmodels.MainPageViewModel;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {
    private NavigationView navigationView;
    private MainPageViewModel viewModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = new ViewModelProvider(this).get(MainPageViewModel.class);
        checkIfSignedIn();
        setContentView(R.layout.activity_main);
        prepareToolbar();


    }

    private void checkIfSignedIn() {
        viewModel.getCurrentUser().observe(this, user -> {
            if (user != null) {
                setNavigationHeader();
            } else
                startActivity(new Intent(this, LoginActivity.class));
        });
    }

    private void prepareToolbar() {
        MaterialToolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle(null);
        setSupportActionBar(toolbar);
        NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.nav_fragment_main);
        NavController navController = navHostFragment.getNavController();
        DrawerLayout drawerLayout = findViewById(R.id.drawer_layout1);
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(R.id.mainPageFragment).setOpenableLayout(drawerLayout).build();

        navigationView = findViewById(R.id.nav_view);
        NavigationUI.setupWithNavController(navigationView, navController);
        NavigationUI.setupWithNavController(toolbar, navController, appBarConfiguration);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        MenuItem logoutItem = navigationView.getMenu().getItem(0);
        logoutItem.setOnMenuItemClickListener(menuItem -> {
            viewModel.signOut();
            return false;
        });
    }


    private void setNavigationHeader() {
        View header = navigationView.getHeaderView(0);
        TextView headerName = header.findViewById(R.id.nav_header_name);
        ImageView headerAvatar = header.findViewById(R.id.nav_header_avatar);

        viewModel.getCurrentUser().observe(this, user -> {
            if (user != null) {
                headerName.setText(user.getDisplayName());
                Glide.with(this).load(user.getPhotoUrl()).into(headerAvatar);
            }
        });
    }
}


