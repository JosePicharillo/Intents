package br.edu.ifsp.scl.sdm.intents;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import br.edu.ifsp.scl.sdm.intents.databinding.ActivityActionBinding;

public class ActionActivity extends AppCompatActivity {

    private ActivityActionBinding activityActionBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityActionBinding = ActivityActionBinding.inflate(getLayoutInflater());
        setContentView(activityActionBinding.getRoot());

        activityActionBinding.mainTb.appTb.setTitle("Tratando Intents");
        activityActionBinding.mainTb.appTb.setSubtitle("Principais Tipos");
        setSupportActionBar(activityActionBinding.mainTb.appTb);
        activityActionBinding.parameterTv.setText(getIntent().getStringExtra(Intent.EXTRA_TEXT));
    }

}
