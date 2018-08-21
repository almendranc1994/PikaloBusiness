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

public class OfferUploadStep2 extends AppCompatActivity {
    public static final String TAG = SignUp.class.getSimpleName();
    String[] day = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31"};
    String[] month = {"January", "February", "March", "September", "October", "November", "Dicember"};
    String[] descriptionData = {"Offer Info", "Time", "Images"};
    private Offer offer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_offer_upload_step2);

        StateProgressBar stateProgressBar = (StateProgressBar) findViewById(R.id.stateProgressBar);
        stateProgressBar.setStateDescriptionData(descriptionData);

        ArrayAdapter<String> arrayAdapterDayStart = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, day);
        arrayAdapterDayStart.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        final Spinner spinnerDayStart = (Spinner) findViewById(R.id.dayOfferStart);
        spinnerDayStart.setAdapter(arrayAdapterDayStart);

        ArrayAdapter<String> arrayAdapterMonthStart = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, month);
        arrayAdapterMonthStart.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        final Spinner spinnerMonthStart = (Spinner) findViewById(R.id.mothOfferStart);
        spinnerMonthStart.setAdapter(arrayAdapterMonthStart);

        ArrayAdapter<String> arrayAdapterDayFinish = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, day);
        arrayAdapterDayStart.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        final Spinner spinnerDayFinish = (Spinner) findViewById(R.id.dayOfferEnd);
        spinnerDayFinish.setAdapter(arrayAdapterDayFinish);

        ArrayAdapter<String> arrayAdapterMonthFinish = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, month);
        arrayAdapterMonthStart.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        final Spinner spinnerMonthFinish = (Spinner) findViewById(R.id.mothOfferEnd);
        spinnerMonthFinish.setAdapter(arrayAdapterMonthFinish);

        Button btnNext = (Button)findViewById(R.id.btnCreateOfferStep2);
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String dayStartSelected = spinnerDayStart.getSelectedItem().toString();
                String monthStartSelected = spinnerMonthStart.getSelectedItem().toString();
                String dayFinishSelected = spinnerDayFinish.getSelectedItem().toString();
                String monthFinishSelected = spinnerMonthFinish.getSelectedItem().toString();
                EditText txtStock = (EditText)findViewById(R.id.stockOffer);

                offer = (Offer) getIntent().getSerializableExtra("offer");
                offer.setStartDate(dayStartSelected + "/" + monthStartSelected);
                offer.setFinishDate(dayFinishSelected + "/" + monthFinishSelected);
                offer.setStock(txtStock.getText().toString());

                Intent offerUploadStep3 = new Intent(OfferUploadStep2.this, OfferUploadStep3.class);
                offerUploadStep3.putExtra("offer", offer);
                startActivity(offerUploadStep3);

            }
        });
    }
}
