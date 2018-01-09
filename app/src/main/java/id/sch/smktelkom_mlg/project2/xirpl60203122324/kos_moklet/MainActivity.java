package id.sch.smktelkom_mlg.project2.xirpl60203122324.kos_moklet;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import android.widget.TextView;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private Context mContext;

    private static final String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mContext = getApplicationContext();


        /*
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        */

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        FragmentTransaction tx = getSupportFragmentManager().beginTransaction();
        String fromMenu = getIntent().getStringExtra("menu");

        if (fromMenu != null){

        }
        else {
            tx.replace(R.id.main,
                    Fragment.instantiate(MainActivity.this, "id.sch.smktelkom_mlg.project2.xirpl60203122324.kos_moklet.Dashboard"));
            tx.addToBackStack(null);
            tx.commit();
        }
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            // super.onBackPressed();
            if(String.valueOf(getSupportFragmentManager().getBackStackEntryCount()).equals("1")){
                menuExit();
            }else{

                FragmentManager fm = getSupportFragmentManager();
                for (int x = 1; x < fm.getBackStackEntryCount(); x++){
                    fm.popBackStack();
                }
            }
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_dashboard){
            FragmentTransaction tx = getSupportFragmentManager().beginTransaction();
            tx.replace(R.id.main,
                    Fragment.instantiate(MainActivity.this, "id.sch.smktelkom_mlg.project2.xirpl60203122324.kos_moklet.Dashboard"));
            tx.addToBackStack(null);
            tx.commit();
            //   tx.addToBackStack(null);
        }

        else if (id == R.id.nav_kos_putra){
            FragmentTransaction tx = getSupportFragmentManager().beginTransaction();
            tx.replace(R.id.main,
                    Fragment.instantiate(MainActivity.this, "id.sch.smktelkom_mlg.project2.xirpl60203122324.kos_moklet.kos_putra"));
            tx.addToBackStack(null);
            tx.commit();
            //   tx.addToBackStack(null);
        }
        else if (id == R.id.nav_kos_putri){
            FragmentTransaction tx = getSupportFragmentManager().beginTransaction();
            tx.replace(R.id.main,
                    Fragment.instantiate(MainActivity.this, "id.sch.smktelkom_mlg.project2.xirpl60203122324.kos_moklet.kos_putri"));
            tx.addToBackStack(null);
            tx.commit();
            //  tx.addToBackStack(null);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void menuExit(){
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setTitle("KosMo");
        //builder.setIcon(R.drawable.ic_your_logo);
        builder.setMessage("Anda yakin ingin keluar?");
        builder.setPositiveButton("Batal",
                new DialogInterface.OnClickListener()
                {
                    public void onClick(DialogInterface dialog, int id)
                    {
                        dialog.cancel();
                    }
                });

        builder.setNegativeButton("Keluar",
                new DialogInterface.OnClickListener()
                {
                    public void onClick(DialogInterface dialog, int id)
                    {
                        System.exit(0);
                        finish();
                    }
                });

        builder.show();
    }
}
