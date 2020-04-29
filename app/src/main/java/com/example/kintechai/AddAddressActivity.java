package com.example.kintechai;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class AddAddressActivity extends AppCompatActivity {

    private Spinner citySpinner;
    private EditText locality;
    private EditText flatNo;
    private EditText pincode;
    private EditText landmark;
    private EditText name;
    private EditText mobileNo;
    private EditText alternateMobileNo;
    //private Spinner stateSpinner;
    private Button saveBtn;

    private String[] cityList;
    private String selectedCity;
    private Dialog loadingDialog;

    private boolean updateAddress = false;
    private AddressesModel addressesModel;
    private int position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_address);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        getSupportActionBar().setTitle("Add a new address");
        toolbar.setTitleTextColor(getResources().getColor(R.color.colorAccent));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        ////////////////////////////// loading dialog
        loadingDialog = new Dialog(this);
        loadingDialog.setContentView(R.layout.loading_progress_dialog);
        loadingDialog.setCancelable(false);
        loadingDialog.getWindow().setBackgroundDrawable(getDrawable(R.drawable.slider_background));
        loadingDialog.getWindow().setLayout(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        ////////////////////////////// loading dialog //////////////////////////////////////
        cityList = getResources().getStringArray(R.array.Bangladesh_City);

        citySpinner = findViewById(R.id.city_spinner);
        locality = findViewById(R.id.locality);
        flatNo = findViewById(R.id.flat_no);
        pincode = findViewById(R.id.pincode);
        landmark = findViewById(R.id.landmark);
        name = findViewById(R.id.name);
        mobileNo = findViewById(R.id.mobile_no);
        alternateMobileNo = findViewById(R.id.alternate_mobile_no);
        //stateSpinner = findViewById(R.id.state_spinner);


        //////////spinner
        ArrayAdapter spinnerAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, cityList);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_list_item_single_choice);

        citySpinner.setAdapter(spinnerAdapter);

        citySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedCity = cityList[position];
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        //////////////spinner////////////////////////

        if (getIntent().getStringExtra("INTENT").equals("update_address")) {
            updateAddress = true;
            position = getIntent().getIntExtra("index", 1);
            addressesModel = DBqueries.addressesModelList.get(position);

            for (int i = 0; i < cityList.length; i++) {
                if (cityList[i].equals(addressesModel.getCity())) {
                    citySpinner.setSelection(i);
                }
            }
            locality.setText(addressesModel.getLocality());
            flatNo.setText(addressesModel.getFlatNo());
            pincode.setText(addressesModel.getPincode());
            landmark.setText(addressesModel.getLandmark());
            name.setText(addressesModel.getName());
            mobileNo.setText(addressesModel.getMobileNo());
            alternateMobileNo.setText(addressesModel.getAlternateMobileNo());

//            saveBtn.setText("Update");

        } else {
            position = DBqueries.addressesModelList.size();
        }

        saveBtn = findViewById(R.id.save_btn);
        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!TextUtils.isEmpty(locality.getText())) {
                    if (!TextUtils.isEmpty(flatNo.getText())) {
                        if (!TextUtils.isEmpty(pincode.getText()) && pincode.getText().length() == 4) {
                            if (!TextUtils.isEmpty(name.getText())) {
                                if (!TextUtils.isEmpty(mobileNo.getText()) && mobileNo.getText().length() == 11) {

                                    loadingDialog.show();

                                    //final String fullAddress = locality.getText().toString() + flatNo.getText().toString() + landmark.getText().toString();
                                    // final String fullAddress = "Flat No or Building Name: " + flatNo.getText().toString() + " - Local Area or Street: " + locality.getText().toString() + " - Landmark: " + landmark.getText().toString() + " - District: " + selectedCity;


                                    Map<String, Object> addAddress = new HashMap();
                                    addAddress.put("city_" + String.valueOf(position + 1), selectedCity);
                                    addAddress.put("locality_" + String.valueOf(position + 1), locality.getText().toString());
                                    addAddress.put("flat_no_" + String.valueOf(position + 1), flatNo.getText().toString());
                                    addAddress.put("pincode_" + String.valueOf(position + 1), pincode.getText().toString());
                                    addAddress.put("landmark_" + String.valueOf(position + 1), landmark.getText().toString());
                                    addAddress.put("name_" + String.valueOf(position + 1), name.getText().toString());
                                    addAddress.put("mobile_no_" + String.valueOf(position + 1), mobileNo.getText().toString());
                                    addAddress.put("alternate_mobile_no_" + String.valueOf(position + 1), alternateMobileNo.getText().toString());
                                    if (!updateAddress) {
                                        addAddress.put("list_size", (long) DBqueries.addressesModelList.size() + 1);
                                        if (getIntent().getStringExtra("INTENT").equals("manage")){
                                            if (DBqueries.addressesModelList.size() == 0){
                                                addAddress.put("selected_" + String.valueOf(position + 1), true);
                                            }else {
                                                addAddress.put("selected_" + String.valueOf(position + 1), false);
                                            }
                                        }else {
                                            addAddress.put("selected_" + String.valueOf(position + 1), true);
                                        }
                                        if (DBqueries.addressesModelList.size() > 0) {
                                            addAddress.put("selected_" + (DBqueries.selectedAddress + 1), false);
                                        }
                                    }

                                    FirebaseFirestore.getInstance().collection("USERS")
                                            .document(FirebaseAuth.getInstance().getUid()).collection("USER_DATA")
                                            .document("MY_ADDRESSES")
                                            .update(addAddress).addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            if (task.isSuccessful()) {
                                                if (!updateAddress) {
                                                    if (DBqueries.addressesModelList.size() > 0) {
                                                        DBqueries.addressesModelList.get(DBqueries.selectedAddress).setSelected(false);
                                                    }
                                                    //DBqueries.addressesModelList.add(new AddressesModel(true, locality.getText().toString(), flatNo.getText().toString(), pincode.getText().toString(), landmark.getText().toString(), name.getText().toString(), mobileNo.getText().toString(), alternateMobileNo.getText().toString(), selectedCity));  /*name.getText().toString() + " - " + mobileNo.getText().toString(), fullAddress, "Pincode: " + pincode.getText().toString(), true));*/
                                                    DBqueries.addressesModelList.add(new AddressesModel(true,selectedCity, locality.getText().toString(), flatNo.getText().toString(), pincode.getText().toString(), landmark.getText().toString(), name.getText().toString(), mobileNo.getText().toString(), alternateMobileNo.getText().toString()));  /*name.getText().toString() + " - " + mobileNo.getText().toString(), fullAddress, "Pincode: " + pincode.getText().toString(), true));*/

                                                    if (getIntent().getStringExtra("INTENT").equals("manage")){
                                                        if (DBqueries.addressesModelList.size() == 0){
                                                            DBqueries.selectedAddress = DBqueries.addressesModelList.size() - 1;
                                                        }
                                                    }else {
                                                        DBqueries.selectedAddress = DBqueries.addressesModelList.size() - 1;
                                                    }
                                                } else {
                                                    //DBqueries.addressesModelList.set(position,new AddressesModel(true, locality.getText().toString(), flatNo.getText().toString(), pincode.getText().toString(), landmark.getText().toString(), name.getText().toString(), mobileNo.getText().toString(), alternateMobileNo.getText().toString(), selectedCity));  /*name.getText().toString() + " - " + mobileNo.getText().toString(), fullAddress, "Pincode: " + pincode.getText().toString(), true));*/
                                                    DBqueries.addressesModelList.set(position, new AddressesModel(true, selectedCity ,locality.getText().toString(),flatNo.getText().toString(),pincode.getText().toString() ,landmark.getText().toString(), name.getText().toString(), mobileNo.getText().toString(), alternateMobileNo.getText().toString()));
                                                }
                                                if (getIntent().getStringExtra("INTENT").equals("deliveryIntent")) {
                                                    Intent deliveryIntent = new Intent(AddAddressActivity.this, DeliveryActivity.class);
                                                    startActivity(deliveryIntent);
                                                } else {
                                                    MyAddressesActivity.refreshItem(DBqueries.selectedAddress,DBqueries.addressesModelList.size() - 1);
                                                }
                                                finish();
                                            } else {
                                                String error = task.getException().getMessage();
                                                Toast.makeText(AddAddressActivity.this, error, Toast.LENGTH_SHORT).show();
                                            }
                                            loadingDialog.dismiss();
                                        }
                                    });
                                } else {
                                    locality.requestFocus();
                                    Toast.makeText(AddAddressActivity.this, "Please provide valide mobile number", Toast.LENGTH_SHORT).show();
                                }
                            } else {
                                locality.requestFocus();
                            }
                        } else {
                            locality.requestFocus();
                            Toast.makeText(AddAddressActivity.this, "Please provide valide pincode", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        locality.requestFocus();
                    }
                } else {
                    locality.requestFocus();
                }
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
