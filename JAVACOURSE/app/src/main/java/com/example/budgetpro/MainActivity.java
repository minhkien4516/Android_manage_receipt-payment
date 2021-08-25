package com.example.budgetpro;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.Menu;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.budgetpro.adapter.SlideAdapter;
import com.example.budgetpro.dialog.ChiDialog;
import com.example.budgetpro.dialog.LoaiChiDialog;
import com.example.budgetpro.dialog.LoaiThuDialog;
import com.example.budgetpro.dialog.ThuDialog;
import com.example.budgetpro.ui.PayMent.KindofpaymentFragment;
import com.example.budgetpro.ui.PayMent.PayableFragment_khoanchi;
import com.example.budgetpro.ui.PayMent.PaymentFragment;
import com.example.budgetpro.ui.Saving.KindofSavingFragment;
import com.example.budgetpro.ui.Saving.ReceiptFragment;
import com.example.budgetpro.ui.Saving.SavingFragment;
import com.example.budgetpro.ui.home.HomeFragment;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.navigation.NavController;
import androidx.navigation.NavDestination;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private ViewPager mSlideViewPager;
    private LinearLayout mDotLayout;
    private SlideAdapter slideAdapter;
    private TextView[] mDots;

    private static FragmentManager fragmentManager;

    private Button mNextBtn;
    private Button  mBackBtn;
    private int mCurrentPage;

    private AppBarConfiguration mAppBarConfiguration;

    public MainActivity() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fragmentManager = getSupportFragmentManager();


        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
//        FloatingActionButton fab = findViewById(R.id.fab);
        final MainActivity currentContext=this;
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                List<Fragment> fragments=getSupportFragmentManager().getFragments();
//
//                Fragment fragment= fragments.get(fragments.size()-1);
//
//                    if (fragment instanceof KindofSavingFragment)
//                    {
//                        LoaiThuDialog dialog1 = new LoaiThuDialog(currentContext, (KindofSavingFragment) fragment);
//                        dialog1.show();
//                    }else if (fragment instanceof ReceiptFragment)
//                    {
//                        ThuDialog dialog2 = new ThuDialog(currentContext, (ReceiptFragment) fragment);
//                        dialog2.show();
//                    }
//                    else if (fragment instanceof KindofpaymentFragment)
//                    {
//                        LoaiChiDialog dialog3 = new LoaiChiDialog(currentContext, (KindofpaymentFragment) fragment);
//                        dialog3.show();
//                    }else if (fragment instanceof PayableFragment_khoanchi) {
//                        ChiDialog dialog4 = new ChiDialog(currentContext, (PayableFragment_khoanchi) fragment);
//                        dialog4.show();
//                    }
//
//            }
//        });



        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home)
                .setDrawerLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        navController.addOnDestinationChangedListener(new NavController.OnDestinationChangedListener() {
            @Override
            public void onDestinationChanged(@NonNull NavController controller, @NonNull NavDestination destination, @Nullable Bundle arguments) {
                if(destination.getId()==R.id.nav_thoat)
                {
                 finish();
                }
            }
        });
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

          @Override
        public boolean onOptionsItemSelected(@NonNull MenuItem item) {
//            Toast.makeText(this, "Selected Item: " + item.getTitle(), Toast.LENGTH_SHORT).show();
            switch (item.getItemId()) {
                case R.id.action_settings:
                    Intent intent = new Intent(this, LoginActivity.class);
                    startActivity(intent);
                    return true;
                default:
                    return super.onOptionsItemSelected(item);
            }

    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }
}