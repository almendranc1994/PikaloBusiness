package com.app.gsl.pikaloforbusiness;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.app.gsl.pikaloforbusiness.Model.Business;
import com.kofigyan.stateprogressbar.StateProgressBar;
import com.weiwangcn.betterspinner.library.material.MaterialBetterSpinner;

public class SignUp extends AppCompatActivity {
    public static final String TAG = SignUp.class.getSimpleName();
    String[] typeBusiness = {"Restaurant", "Clothing", "Shoes", "Health and Personal care"};
    String[] category = {"Fast food", "Peruvian food", "Buffet", "Marine food"};
    String[] descriptionData = {"Business Info", "Location", "Profile"};
    Button btnNextStep;
    String categorySelected;
    String typeSelected;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        StateProgressBar stateProgressBar = (StateProgressBar) findViewById(R.id.stateProgressBar);
        stateProgressBar.setStateDescriptionData(descriptionData);

        ArrayAdapter<String> arrayAdapterTypeBusiness = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, typeBusiness);
        arrayAdapterTypeBusiness.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        final Spinner spinnerTypeBusiness = (Spinner) findViewById(R.id.typeBusiness);
        spinnerTypeBusiness.setAdapter(arrayAdapterTypeBusiness);
        //final MaterialBetterSpinner betterSpinnerTypeBusiness = (MaterialBetterSpinner) findViewById(R.id.typeBusiness);
        //betterSpinnerTypeBusiness.setAdapter(arrayAdapterTypeBusiness);

        ArrayAdapter<String> arrayAdapterCategory = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, category);
        arrayAdapterCategory.setDropDownViewResource(android.R.layout.simple_spinner_item);
        final Spinner spinnerCategory = (Spinner) findViewById(R.id.category);
        spinnerCategory.setAdapter(arrayAdapterCategory);
        //final MaterialBetterSpinner betterSpinnerCategory = (MaterialBetterSpinner) findViewById(R.id.category);
        //betterSpinnerCategory.setAdapter(arrayAdapterCategory);

        btnNextStep = (Button) findViewById(R.id.btnGoToStep2);
        btnNextStep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText txtBusinessName = (EditText) findViewById(R.id.edtBusiness);
                String businessName = txtBusinessName.getText().toString();
                EditText txtDescripion = (EditText) findViewById(R.id.edtDescription);
                String description = txtDescripion.getText().toString();

                categorySelected = spinnerCategory.getSelectedItem().toString();
                typeSelected = spinnerTypeBusiness.getSelectedItem().toString();

                //Save Data
                Business business = new Business();
                business.setBusinessName(businessName);
                business.setDescription(description);
                business.setCategory(categorySelected);
                business.setTypeBusiness(typeSelected);

                Intent signUpStep2 = new Intent(SignUp.this, SignUpStep2.class);
                signUpStep2.putExtra("business", business);
                startActivity(signUpStep2);
            }
        });
    }
}
