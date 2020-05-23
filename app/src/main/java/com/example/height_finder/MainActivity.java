package com.example.height_finder;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.Toast;

// this entire project wwas done by Kiran alone not venkat nooooooooooooooo

public class MainActivity extends AppCompatActivity implements CompoundButton.OnCheckedChangeListener {
    Switch Permission;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        if (ActivityCompat.checkSelfPermission(this.getApplicationContext(), Manifest.permission.ACCESS_COARSE_LOCATION) == 0 && ActivityCompat.checkSelfPermission(this.getApplicationContext(), Manifest.permission.ACCESS_COARSE_LOCATION) == 0)
        {
            //If Permission granted going to the Features Activity.
            Intent I = new Intent(getApplicationContext(),FeaturesActivity.class);
            startActivity(I);
        }
        else {
            setContentView(R.layout.activity_main);
            Permission = (Switch)findViewById(R.id.switch_permission);
            Permission.setOnCheckedChangeListener(this);
        }
    }

    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean b)
    {
        if(b)
        {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION}, 123);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults)
    {
        if (requestCode == 123)
        {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED && grantResults[1] == PackageManager.PERMISSION_GRANTED)
            {
                Intent I = new Intent(getApplicationContext(),FeaturesActivity.class);
                startActivity(I);
            }
            else
            {
                Permission.setChecked(false);
                Toast.makeText(this, "Need Location Permissions", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
