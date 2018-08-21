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
import android.widget.Toast;

import com.app.gsl.pikaloforbusiness.Model.Business;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class SignIn extends AppCompatActivity {
    public static final String TAG = SignUpStep3.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        final EditText edtUsername = (EditText)findViewById(R.id.edtUsername);
        final EditText edtPassword = (EditText)findViewById(R.id.edtPassword);
        Button btnSignIn = (Button)findViewById(R.id.btnSignIn);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference table_business = database.getReference("Business");

        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final ProgressDialog mDialog = new ProgressDialog(SignIn.this);
                mDialog.setMessage("Please waiting...");
                mDialog.show();

                table_business.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        //Check is the user is in the database
                        if(dataSnapshot.child(edtUsername.getText().toString()).exists()){
                            //Get User Information
                            mDialog.dismiss();
                            Business business = dataSnapshot.child(edtUsername.getText().toString()).getValue(Business.class);
                            Log.i(TAG, "Data found: ");
                            Log.i(TAG, "               " + business.getBusinessName());
                            Log.i(TAG, "               " + business.getDescription());
                            Log.i(TAG, "               " + business.getCategory());
                            Log.i(TAG, "               " + business.getTypeBusiness());
                            Log.i(TAG, "               " + business.getIdPlace());
                            Log.i(TAG, "               " + business.getPassword());

                            if(business.getPassword().equals(edtPassword.getText().toString())){
                                Toast.makeText(SignIn.this, "Sign in successfully !", Toast.LENGTH_SHORT).show();
                                Intent addOffer = new Intent(SignIn.this, OfferUploadStep3.class);
                                startActivity(addOffer);
                            }
                            else{
                                Toast.makeText(SignIn.this, "Wrong Password !", Toast.LENGTH_SHORT).show();
                            }
                        }
                        else{
                            mDialog.dismiss();
                            Toast.makeText(SignIn.this, "User not exits", Toast.LENGTH_LONG).show();
                        }
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });

            }
        });

    }
}
