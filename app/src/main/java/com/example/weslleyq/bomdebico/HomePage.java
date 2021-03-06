package com.example.weslleyq.bomdebico;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.widget.SearchView;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.DrawerLayout.DrawerListener;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import com.example.weslleyq.bomdebico.R;

import static android.app.PendingIntent.getActivity;
public class HomePage extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener,
        AnuncioFragment.OnFragmentInteractionListener, InserirAnuncioFragment.OnFragmentInteractionListener,
        PerfilFragment.OnFragmentInteractionListener,
        SobreFragment.OnFragmentInteractionListener , SearchView.OnQueryTextListener{





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);



        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.conteudo, new InserirAnuncioFragment());
                transaction.commit();

            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();




        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setItemIconTintList(null);

        getSupportFragmentManager().beginTransaction().replace(R.id.conteudo, new AnuncioFragment()).commit();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //  getMenuInflater().inflate(R.menu.menu_search, menu);
        getMenuInflater().inflate(R.menu.home_page, menu);

        //  MenuItem searchItem = menu.findItem(R.id.search);
        // SearchView searchView = (SearchView) MenuItemCompat.getActionView(searchItem);
        //searchView.setOnQueryTextListener(this);

        return true;
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        // User pressed the search button
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        // User changed the text
        return false;
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

    //@Override
    //public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
      //  getMenuInflater().inflate(R.menu.home_page, menu);
        //return true;
   // }

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


    @Override

    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();
        Fragment fragment = null;
        Context contexto = null;

        boolean fragmentSelecionado=false;
        boolean activitySelecionado=false;
        Bundle bundle = new Bundle();
        if (id == R.id.nav_anuncios) {
            fragment = new AnuncioFragment();
            fragmentSelecionado=true;
        } else if (id == R.id.nav_sobre) {
            fragment = new SobreFragment();
            fragmentSelecionado=true;

        } else if (id == R.id.nav_perfil) {
       //fragment = new PerfilFragment();
          // fragmentSelecionado=true;
           Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
           startActivity(intent);

        }
        else if (id == R.id.nav_inserir) {
            fragment = new InserirAnuncioFragment();
            fragmentSelecionado=true;
        }
        else if (id == R.id.fab) {
            fragment = new InserirAnuncioFragment();
            fragmentSelecionado=true;
        }
        if (fragmentSelecionado==true){
            getSupportFragmentManager().beginTransaction().replace(R.id.conteudo,fragment).commit();
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
