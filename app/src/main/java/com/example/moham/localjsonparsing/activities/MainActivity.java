package com.example.moham.localjsonparsing.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.moham.localjsonparsing.R;
import com.example.moham.localjsonparsing.fragments.PullService_Frag;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportFragmentManager().beginTransaction()
                .replace(android.R.id.content,new PullService_Frag())
                .commit();
    }
}
