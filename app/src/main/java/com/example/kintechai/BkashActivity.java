package com.example.kintechai;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class BkashActivity extends AppCompatActivity {

    private EditText transectionId;
    private Button payNowBtn;
    private Dialog loadingDialog;
    public static Activity bkashActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bkash);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        getSupportActionBar().setTitle("Payment");
        toolbar.setTitleTextColor(getResources().getColor(R.color.colorAccent));

        transectionId = findViewById(R.id.transection_id);
        payNowBtn = findViewById(R.id.pay_now_btn);

        ////////////////////////////// loading dialog
        loadingDialog = new Dialog(BkashActivity.this);
        loadingDialog.setContentView(R.layout.loading_progress_dialog);
        loadingDialog.setCancelable(false);
        loadingDialog.getWindow().setBackgroundDrawable(getDrawable(R.drawable.slider_background));
        loadingDialog.getWindow().setLayout(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        ////////////////////////////// loading dialog //////////////////////////////////////

        payNowBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!transectionId.getText().toString().isEmpty()) {
                    bkashActivity = BkashActivity.this;
                    loadingDialog.show();
                    ConfirmOrderActivity();
                } else {
                    Toast.makeText(BkashActivity.this, "Give Transection ID Please", Toast.LENGTH_SHORT).show();
                }
                loadingDialog.dismiss();
            }
        });

    }

   public void ConfirmOrderActivity(){
        Intent intent = new Intent(this, ConfirmOrderActivity.class);
        startActivity(intent);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home){
            bkashActivity = null;
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
