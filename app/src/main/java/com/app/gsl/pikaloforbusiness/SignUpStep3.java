package com.app.gsl.pikaloforbusiness;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.app.gsl.pikaloforbusiness.Model.Business;
import com.app.gsl.pikaloforbusiness.Model.LocationBusiness;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.kofigyan.stateprogressbar.StateProgressBar;

import org.w3c.dom.Text;

public class SignUpStep3 extends AppCompatActivity {
    String[] descriptionData = {"Business Info", "Location", "Profile"};

    private Business business;
    public static final String TAG = SignUpStep3.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_step3);

        StateProgressBar stateProgressBar = (StateProgressBar) findViewById(R.id.stateProgressBar);
        stateProgressBar.setStateDescriptionData(descriptionData);

        business = (Business) getIntent().getSerializableExtra("business");
        Log.i(TAG, "Data recieved: ");
        Log.i(TAG, "               " + business.getBusinessName());
        Log.i(TAG, "               " + business.getDescription());
        Log.i(TAG, "               " + business.getCategory());
        Log.i(TAG, "               " + business.getTypeBusiness());
        Log.i(TAG, "               " + business.getIdPlace());

        final EditText txtUsername = (EditText)findViewById(R.id.edtUsername);
        final EditText txtEmail = (EditText)findViewById(R.id.edtEmail);
        final EditText txtPassword = (EditText)findViewById(R.id.edtPassword);

        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference table_business = database.getReference("Business");

        Button btnRegister = (Button)findViewById(R.id.btnRegister);
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final ProgressDialog mDialog = new ProgressDialog(SignUpStep3.this);
                mDialog.setMessage("Please waiting ...");
                mDialog.show();

                business.setEmail(txtEmail.getText().toString());
                business.setPassword(txtPassword.getText().toString());
                final String username = txtUsername.getText().toString();

                table_business.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        if(dataSnapshot.child(txtUsername.getText().toString()).exists()){
                            Log.i(TAG, "Already exits: " + username);
                            mDialog.dismiss();
                            Toast.makeText(SignUpStep3.this, "Business already register", Toast.LENGTH_SHORT).show();
                        }else{
                            mDialog.dismiss();
                            Business newBusiness = new Business(business.getBusinessName(), business.getCategory(), business.getDescription(), txtEmail.getText().toString(), txtPassword.getText().toString(), business.getTypeBusiness(), business.getIdPlace(), "0");
                            table_business.child(txtUsername.getText().toString()).setValue(newBusiness);
                            Toast.makeText(SignUpStep3.this, "Sign up successfully", Toast.LENGTH_SHORT).show();
                            finish();
                            Intent main = new Intent(SignUpStep3.this, MainActivity.class);
                            startActivity(main);
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
            }
        });
    }
}
