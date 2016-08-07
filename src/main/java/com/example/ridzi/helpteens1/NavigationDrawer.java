package com.example.ridzi.helpteens1;

/**
 * Created by Ridzi on 07-07-2016.
 */


        import android.app.Dialog;
        import android.app.FragmentManager;
        import android.app.SearchManager;
        import android.content.ClipData;
        import android.content.Context;
        import android.content.Intent;
        import android.os.Bundle;
        import android.support.design.widget.FloatingActionButton;
        import android.support.design.widget.Snackbar;
        import android.app.FragmentTransaction;
        import android.support.v7.widget.SearchView;
        import android.view.View;
        import android.support.design.widget.NavigationView;
        import android.support.v4.view.GravityCompat;
        import android.support.v4.widget.DrawerLayout;
        import android.support.v7.app.ActionBarDrawerToggle;
        import android.support.v7.app.AppCompatActivity;
        import android.support.v7.widget.Toolbar;
        import android.view.Menu;
        import android.view.MenuItem;
        import android.view.Window;
        import android.widget.Button;
        import android.widget.TextView;
        import android.widget.Toast;

public class NavigationDrawer extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    NavigationView navigationView = null;

    Toolbar toolbar = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //set initial fragment
        Mypost_Fragment fragment = new Mypost_Fragment();
        FragmentManager fm=getFragmentManager();



        Intent intent=getIntent();
        String data=intent.getStringExtra("data");





       FragmentTransaction fragmentTransaction = fm.beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container, fragment);
        fragmentTransaction.commit();

        setContentView(R.layout.activity_navigation_drawer);
        toolbar = (Toolbar) findViewById(R.id.toolbar);

    NavigationView navigationView =(NavigationView)findViewById(R.id.nav_view);
        View view = navigationView.inflateHeaderView(R.layout.nav_header_navigation_drawer);
       TextView tv=(TextView)view.findViewById(R.id.emailheader);
        tv.setText(data);




        setSupportActionBar(toolbar);


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        //Changes by Santosh
        getMenuInflater().inflate(R.menu.navigation_drawer, menu);
        MenuItem searchItem=menu.findItem(R.id.action_search);

        SearchManager searchManager=(SearchManager)NavigationDrawer.this.getSystemService(Context.SEARCH_SERVICE);

        SearchView searchView =null;
        if(searchItem!=null){
            searchView=(SearchView)searchItem.getActionView();

        }
        if(searchView!=null)
        {
            searchView.setSearchableInfo(searchManager.getSearchableInfo(NavigationDrawer.this.getComponentName()));
        }
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_refresh) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_post) {
            //set initial fragment
            Mypost_Fragment fragment = new Mypost_Fragment();
            FragmentManager fm=getFragmentManager();
            FragmentTransaction fragmentTransaction = fm.beginTransaction();
            fragmentTransaction.replace(R.id.fragment_container, fragment);
            fragmentTransaction.commit();

        } else if (id == R.id.nav_category) {
            //set initial fragment
            Category_Fragment fragment = new Category_Fragment();
            FragmentManager fm=getFragmentManager();
           FragmentTransaction fragmentTransaction = fm.beginTransaction();
            fragmentTransaction.replace(R.id.fragment_container, fragment);
            fragmentTransaction.commit();

        } else if (id == R.id.nav_feedback) {
            //set initial fragment
            Feedback_Fragment fragment = new Feedback_Fragment();
            FragmentManager fm=getFragmentManager();
            FragmentTransaction fragmentTransaction = fm.beginTransaction();
            fragmentTransaction.replace(R.id.fragment_container, fragment);
            fragmentTransaction.commit();

        }
        else if (id == R.id.nav_setting) {
            Intent intent = new Intent(NavigationDrawer.this, Setting.class);
            startActivity(intent);

        }
        else if (id == R.id.nav_createpost) {
            //set initial fragment
            Createpost_Fragment fragment = new Createpost_Fragment();
            FragmentManager fm=getFragmentManager();
            FragmentTransaction fragmentTransaction = fm.beginTransaction();
            fragmentTransaction.replace(R.id.fragment_container, fragment);
            fragmentTransaction.commit();

        }
        else if (id == R.id.nav_aboutUs) {
            //set initial fragment
            Aboutus_Fragment fragment = new Aboutus_Fragment();
            FragmentManager fm=getFragmentManager();
            FragmentTransaction fragmentTransaction = fm.beginTransaction();
            fragmentTransaction.replace(R.id.fragment_container, fragment);
            fragmentTransaction.commit();

        }
        else if (id == R.id.nav_logout)
        {
            final Dialog dialog = new Dialog(NavigationDrawer.this);
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            dialog.setContentView(R.layout.exit_dialog);
            Button noBtn = (Button) dialog.findViewById(R.id.btn_no);
            Button yesBtn=(Button)dialog.findViewById(R.id.btn_yes);
            noBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog.dismiss();
                }
            });
            yesBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    finish();
                    Intent intent = new Intent(NavigationDrawer.this, Login.class);
                    startActivity(intent);

                }
            });
            dialog.show();

        }
        /*else if (id == R.id.nav_setting) {
            //set initial fragment
            Setting_Fragment fragment = new Setting_Fragment();
            android.support.v4.app.FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.fragment_container, fragment);
            fragmentTransaction.commit();

        } else if (id == R.id.nav_logout) {

        }*/

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
