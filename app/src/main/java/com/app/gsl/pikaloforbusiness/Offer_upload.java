package com.app.gsl.pikaloforbusiness;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.app.gsl.pikaloforbusiness.Model.Business;
import com.app.gsl.pikaloforbusiness.Model.Offer;
import com.kofigyan.stateprogressbar.StateProgressBar;

public class Offer_upload extends AppCompatActivity {
    public static final String TAG = SignUp.class.getSimpleName();
    String[] type = {"Soles", "%"};
    String[] descriptionData = {"Offer Info", "Time", "Images"};
    private String businessID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_offer_upload);

        StateProgressBar stateProgressBar = (StateProgressBar) findViewById(R.id.stateProgressBar);
        stateProgressBar.setStateDescriptionData(descriptionData);

        ArrayAdapter<String> arrayAdapterTypeBusiness = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, type);
        arrayAdapterTypeBusiness.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        final Spinner spinnerType = (Spinner) findViewById(R.id.typeOffer);
        spinnerType.setAdapter(arrayAdapterTypeBusiness);

        businessID = (String) getIntent().getSerializableExtra("businessID");

        Button btnNext = (Button)findViewById(R.id.btnCreateOfferStep1);
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText txtName = (EditText)findViewById(R.id.edtOfferName);
                EditText txtDescription = (EditText)findViewById(R.id.edtOfferDescription);
                EditText txtRealPrice = (EditText)findViewById(R.id.edtRealPrice);
                EditText txtOfferPrice = (EditText)findViewById(R.id.edtOfferPrice);
                String typeSelected = spinnerType.getSelectedItem().toString();

                Offer offer = new Offer();
                offer.setName(txtName.getText().toString());
                offer.setDescription(txtDescription.getText().toString());
                offer.setRealPrice(txtRealPrice.getText().toString());
                offer.setOfferPrice(txtOfferPrice.getText().toString() + " " + typeSelected);
                offer.setBusinessID(businessID);

                Intent offerUploadStep2 = new Intent(Offer_upload.this, OfferUploadStep2.class);
                offerUploadStep2.putExtra("offer", offer);
                startActivity(offerUploadStep2);

            }
        });
    }
}
