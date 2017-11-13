package com.example.eladgofer.project;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Base64;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.example.eladgofer.project.fragments.MovieFragment;
import com.example.eladgofer.project.fragments.NewsFragment;
import com.example.eladgofer.project.fragments.PhraseFragment;
import com.example.eladgofer.project.fragments.ShoppingNewsFragment;
import com.example.eladgofer.project.fragments.TodoFragment;
import com.example.eladgofer.project.utils.User;
import com.firebase.ui.auth.AuthUI;
import com.google.android.gms.common.Scopes;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private static final int RC_SIGN_IN = 1;
    NavigationView navigationView;
    private FirebaseUser mUser;
    private FirebaseAuth mAuth;
    private FirebaseDatabase mDatabase;

    private FirebaseAuth.AuthStateListener mAuthListener = new FirebaseAuth.AuthStateListener() {
        @Override
        public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
            mUser = firebaseAuth.getCurrentUser();
            if (mUser == null) {
                List<AuthUI.IdpConfig> mProviders = Arrays.asList(
                        new AuthUI.IdpConfig.Builder(AuthUI.EMAIL_PROVIDER).build(),
                        new AuthUI.IdpConfig.Builder(AuthUI.GOOGLE_PROVIDER).
                                setPermissions(Arrays.asList(Scopes.EMAIL, Scopes.PROFILE))
                                .build());

                Intent intent = AuthUI.getInstance().
                        createSignInIntentBuilder().
                        setLogo(R.mipmap.ic_launcher).
                        setAvailableProviders(mProviders).
                        setIsSmartLockEnabled(false).
                        build();

                startActivityForResult(intent, RC_SIGN_IN);
            } else {
                //Save the user to Database
                //  DatabaseReference userRow = mDatabase.getReference("Users").child(mUser.getUid());

                User u = new User(mUser);
                mDatabase.getReference("Users").child(mUser.getUid()).setValue(u);
                getSupportFragmentManager().
                        beginTransaction().
                        add(R.id.frame, new PhraseFragment(), "A").
                        addToBackStack("phrases").
                        commit();

            }
        }
    };

    @Override
    protected void onResume() {
        super.onResume();
        mAuth.addAuthStateListener(mAuthListener);

        sha1();
    }

    void sha1() {
        try {
            PackageInfo info = getPackageManager().getPackageInfo
                    ("more.hackeru.edu.shopee", PackageManager.GET_SIGNATURES);
            for (android.content.pm.Signature signature : info.signatures) {
                MessageDigest md = MessageDigest.getInstance("SHA1");
                md.update(signature.toByteArray());
                Log.d("KeyHash:", Base64.encodeToString(md.digest(), Base64.DEFAULT));
            }
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        mAuth.removeAuthStateListener(mAuthListener);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance();

        //weatherFragment = new WeatherFragment();
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();
        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setCheckedItem(R.id.nav_news);
        getSupportFragmentManager().
                beginTransaction().
                replace(R.id.frame, new PhraseFragment(), "A").
                addToBackStack("phrases").
                commit();


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
        switch (id) {
            case R.id.action_settings:
                return true;
            case R.id.action_sign_out:
                AuthUI.getInstance().signOut(this);
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        switch (id) {
            case R.id.nav_news:
                getSupportFragmentManager().
                        beginTransaction().
                        addToBackStack("news").
                        replace(R.id.frame, new NewsFragment()).
                        commit();
                break;
            case R.id.nav_shop_news:
                getSupportFragmentManager().
                        beginTransaction().
                        addToBackStack("shopNews").
                        replace(R.id.frame, new ShoppingNewsFragment()).
                        commit();

                break;
            case R.id.nav_tools:
                getSupportFragmentManager().
                        beginTransaction().
                        addToBackStack("todo").
                        replace(R.id.frame, new TodoFragment()).
                        commit();
                break;
            case R.id.nav_movies:
                getSupportFragmentManager().
                        beginTransaction().
                        addToBackStack("todo").
                        replace(R.id.frame, new MovieFragment()).
                        commit();
                break;
/*            case R.id.nav_share:
                break;
            case R.id.nav_send:
                break;*/
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

}
