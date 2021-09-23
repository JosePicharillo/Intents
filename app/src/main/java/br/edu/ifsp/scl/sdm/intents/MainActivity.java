package br.edu.ifsp.scl.sdm.intents;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.webkit.URLUtil;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import br.edu.ifsp.scl.sdm.intents.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding activityMainBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityMainBinding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(activityMainBinding.getRoot());

        activityMainBinding.mainTb.appTb.setTitle("Tratando Intents");
        activityMainBinding.mainTb.appTb.setSubtitle("Principais tipos");
        setSupportActionBar(activityMainBinding.mainTb.appTb);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return true;
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        boolean op = false;

        switch (item.getItemId()) {
            case R.id.viewMi:
                String url;
                url = activityMainBinding.parameterEt.getText().toString();
                boolean isURL = URLUtil.isValidUrl(url);
                if (isURL) {
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                    startActivity(intent);
                } else {
                    String resultURL;
                    resultURL = "https://" + url;
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(resultURL));
                    startActivity(intent);
                }
                op = true;
                break;

            case R.id.callMi:
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    if (ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                        requisitarPermissaoLigacao();
                    } else {
                        discarTelefone();
                    }
                } else {
                    discarTelefone();
                }
                op = true;
                break;

            case R.id.dialMi:
                String tel = activityMainBinding.parameterEt.getText().toString();
                Intent intent = new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", tel, null));
                startActivity(intent);
                op = true;
                break;

            case R.id.pickMi:
                break;

            case R.id.chooserMi:
                break;

            case R.id.exitMi:
                this.finish();
                op = true;
                break;

            case R.id.actionMi:
                Intent actionIntent = new Intent("OPEN_ACTION_ACTIVITY").putExtra(
                        Intent.EXTRA_TEXT,
                        activityMainBinding.parameterEt.getText().toString()
                );
                startActivity(actionIntent);
                op = true;
                break;

            default:
                op = false;
        }
        return op;
    }

    private void discarTelefone() {
        Intent discarIntent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + activityMainBinding.parameterEt.getText().toString()));
        startActivity(discarIntent);
    }

    private void requisitarPermissaoLigacao() {
        ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.CALL_PHONE}, 1);
    }
}