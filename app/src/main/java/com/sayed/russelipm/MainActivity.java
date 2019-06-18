package com.sayed.russelipm;


import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.BottomSheetDialog;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();

        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.nav);

        bottomNavigationView.setOnNavigationItemSelectedListener
                (new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                        switch (item.getItemId()) {
                            case R.id.home:
                                HomeActivity homeActivity = new HomeActivity();
                                Bundle bundleHome = new Bundle();
                                bundleHome.putString("text", "welcome to home Fragment ");
                                homeActivity.setArguments(bundleHome);
                                FragmentTransaction transactionHome = getSupportFragmentManager().beginTransaction();
                                transactionHome.replace(R.id.frame, homeActivity);
                                transactionHome.commit();
                                break;
                            case R.id.more:
                                MoreActivity moreActivity = new MoreActivity();
                                Bundle bundleMore = new Bundle();
                                bundleMore.putString("text", "more about us");
                                moreActivity.setArguments(bundleMore);
                                FragmentTransaction transactionMore = getSupportFragmentManager().beginTransaction();
                                transactionMore.replace(R.id.frame, moreActivity);
                                transactionMore.commit();
                                break;
                            case R.id.msg:
                                InquiryActivity inquiryActivity = new InquiryActivity();
                                Bundle bundle = new Bundle();
                                bundle.putString("text", "inquiry Fragment");
                                inquiryActivity.setArguments(bundle);
                                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                                transaction.replace(R.id.frame, inquiryActivity);
                                transaction.commit();
                                break;
                        }

                        return true;
                    }
                });

        //Manually displaying the first fragment - one time only
        HomeActivity homeFrag = new HomeActivity();
        Bundle bundle = new Bundle();
        bundle.putString("text", "");
        homeFrag.setArguments(bundle);
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame, homeFrag);
        transaction.commit();
    }


    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(this)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setMessage("Are you sure you want to exit?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }

                })
                .setNegativeButton("No", null)
                .show();
    }

}

