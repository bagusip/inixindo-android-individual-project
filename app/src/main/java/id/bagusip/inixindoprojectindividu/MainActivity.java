package id.bagusip.inixindoprojectindividu;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

import id.bagusip.inixindoprojectindividu.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    private ActionBarDrawerToggle toggle;
    private OnBackPressedListener onBackPressedListener;


    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        toolbar = findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        initView();
    }

    private void initView() {
        String myStr = null;
        // custom toolbar
        setSupportActionBar(binding.toolbar);

        //set menu
        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        Fragment fragment = null;
        myStr = "home";

        if(extras != null)
            if(extras != null){
                myStr = extras.getString("keyName");
            } else {
                myStr = "home";
            }

        Toast.makeText(this, myStr, Toast.LENGTH_SHORT).show();

        switch (myStr){
            case "peserta":
                fragment = new PesertaFragment();
                getSupportActionBar().setTitle("Peserta");
                binding.drawer.closeDrawer(GravityCompat.START);
                callFragment(fragment);
                Toast.makeText(this, myStr, Toast.LENGTH_SHORT).show();
                break;

            case "instruktur":
                fragment = new InstrukturFragment();
                getSupportActionBar().setTitle("Instruktur");
                binding.drawer.closeDrawer(GravityCompat.START);
                callFragment(fragment);
                Toast.makeText(this, myStr, Toast.LENGTH_SHORT).show();
                break;

            case "materi":
                fragment = new MateriFragment();
                getSupportActionBar().setTitle("Materi");
                binding.drawer.closeDrawer(GravityCompat.START);
                callFragment(fragment);
                Toast.makeText(this, myStr, Toast.LENGTH_SHORT).show();
                break;

            case "kelas":
                fragment = new KelasFragment();
                getSupportActionBar().setTitle("Kelas");
                binding.drawer.closeDrawer(GravityCompat.START);
                callFragment(fragment);
                Toast.makeText(this, myStr, Toast.LENGTH_SHORT).show();
                break;

            case "detail_kelas":
                fragment = new DetailKelasFragment();
                getSupportActionBar().setTitle("Detail Kelas");
                binding.drawer.closeDrawer(GravityCompat.START);
                callFragment(fragment);
                Toast.makeText(this, myStr, Toast.LENGTH_SHORT).show();
                break;

            default:
                //default fragment dibuka pertama kali
                getSupportActionBar().setTitle("Home");
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.frame_layout, new HomeFragment())
                        .commit();
                binding.navView.setCheckedItem(R.id.nav_home);
                Toast.makeText(this, myStr, Toast.LENGTH_SHORT).show();
                break;
        }


        //membuka drawer
        toggle = new ActionBarDrawerToggle(this, binding.drawer, binding.toolbar, R.string.open, R.string.close);

        //drawer back button
        toggle.getDrawerArrowDrawable().setColor(getResources().getColor(R.color.white));

        // sinkronisasi drawer
        toggle.syncState();

        //salah satu menu navigasi dipilih
        binding.navView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            Fragment fragment = null;

            //option selected draw
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.nav_home:
                        fragment = new HomeFragment();
                        getSupportActionBar().setTitle("Home");
                        binding.drawer.closeDrawer(GravityCompat.START);
                        callFragment(fragment);
                        break;
                    case R.id.nav_peserta:
                        fragment = new PesertaFragment();
                        getSupportActionBar().setTitle("Peserta");
                        binding.drawer.closeDrawer(GravityCompat.START);
                        callFragment(fragment);
                        break;
                    case R.id.nav_instruktur:
                        fragment = new InstrukturFragment();
                        getSupportActionBar().setTitle("Instruktur");
                        binding.drawer.closeDrawer(GravityCompat.START);
                        callFragment(fragment);
                        break;
                    case R.id.nav_materi:
                        fragment = new MateriFragment();
                        getSupportActionBar().setTitle("Materi");
                        binding.drawer.closeDrawer(GravityCompat.START);
                        callFragment(fragment);
                        break;
                    case R.id.nav_kelas:
                        fragment = new KelasFragment();
                        getSupportActionBar().setTitle("Kelas");
                        binding.drawer.closeDrawer(GravityCompat.START);
                        callFragment(fragment);
                        break;
                    case R.id.nav_detail_kelas:
                        fragment = new DetailKelasFragment();
                        getSupportActionBar().setTitle("Detail Kelas");
                        binding.drawer.closeDrawer(GravityCompat.START);
                        callFragment(fragment);
                        break;
                    case R.id.nav_search_data:
                        fragment = new SearchDataFragment();
                        getSupportActionBar().setTitle("Search Data");
                        binding.drawer.closeDrawer(GravityCompat.START);
                        callFragment(fragment);
                        break;
                }
                return true;
            }
        });

        NavigationView navigationView = findViewById(R.id.navView);
        View headerView = getLayoutInflater().inflate(R.layout.nav_header_layout, navigationView, false);
        navigationView.addHeaderView(headerView);

    }



    private void callFragment(Fragment fragment) {
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.setCustomAnimations(
                android.R.anim.slide_in_left,
                android.R.anim.slide_out_right
        );
        transaction.replace(R.id.frame_layout, fragment);
        //menunya bergantian
        transaction.addToBackStack(null);
        transaction.commit();
    }

    @Override
    public void onBackPressed() {
        if (onBackPressedListener != null) {
            getSupportActionBar().setTitle("Home Fragment");
            binding.navView.setCheckedItem(R.id.nav_home);
            onBackPressedListener.doBack();
            binding.drawer.closeDrawer(GravityCompat.START);
        } else if (onBackPressedListener == null) {
            finish();
            super.onBackPressed();
        }
    }

    public interface OnBackPressedListener {
        void doBack();
    }

    public void setOnBackPressedListener(OnBackPressedListener onBackPressedListener) {
        this.onBackPressedListener = onBackPressedListener;
    }

    @Override
    protected void onDestroy() {
        onBackPressedListener = null;
        super.onDestroy();
    }
}