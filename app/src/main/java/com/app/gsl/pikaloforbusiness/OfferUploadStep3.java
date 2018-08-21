package com.app.gsl.pikaloforbusiness;

import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.Handler;
import android.os.storage.StorageManager;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.app.gsl.pikaloforbusiness.Model.Offer;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StorageTask;
import com.google.firebase.storage.UploadTask;

public class OfferUploadStep3 extends AppCompatActivity {

    public static final String TAG = SignUpStep3.class.getSimpleName();
    private static final int PICK_IMAGE_REQUEST = 1;
    private Button mButtonChooseImage;
    private Button mButtonUpload;
    private EditText mEditTextFileName;
    private ImageView mImageView;
    private ProgressBar mProgressBar;
    private TextView mTextViewShowUploads;

    private Uri mImageUri;
    private Offer offerStep2;

    private StorageReference mStorageRef;
    private DatabaseReference mDatabaseRef;
    private StorageTask mUploadTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_offer_upload_step3);

        offerStep2 = (Offer) getIntent().getSerializableExtra("offer");
        Log.i(TAG, "Data recieved: ");
        Log.i(TAG, "               " + offerStep2.getName());
        Log.i(TAG, "               " + offerStep2.getDescription());
        Log.i(TAG, "               " + offerStep2.getRealPrice());
        Log.i(TAG, "               " + offerStep2.getOfferPrice());
        Log.i(TAG, "               " + offerStep2.getStartDate());
        Log.i(TAG, "               " + offerStep2.getFinishDate());
        Log.i(TAG, "               " + offerStep2.getStock());

        mButtonChooseImage = (Button) findViewById(R.id.button_choose_image);
        mButtonUpload = (Button)findViewById(R.id.button_upload);
        //mTextViewShowUploads
        mImageView = (ImageView)findViewById(R.id.image_view);
        mProgressBar = (ProgressBar)findViewById(R.id.progress_bar);

        mStorageRef = FirebaseStorage.getInstance().getReference("Offer");
        mDatabaseRef = FirebaseDatabase.getInstance().getReference("Offer");

        mButtonChooseImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openFileChooser();
            }
        });

        mButtonUpload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mUploadTask != null && mUploadTask.isInProgress()){
                    Toast.makeText(OfferUploadStep3.this, "Upload in progress", Toast.LENGTH_SHORT).show();
                }else{
                    uplooadFile();
                }
            }
        });

    }

    private void openFileChooser(){
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, PICK_IMAGE_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null){
            mImageUri = data.getData();

            //Picasso.with(this).load(mImageUri).into(mImageView);
            mImageView.setImageURI(mImageUri);
        }
    }

    private String getFileExtention(Uri uri){
        ContentResolver cR = getContentResolver();
        MimeTypeMap mime = MimeTypeMap.getSingleton();
        return mime.getExtensionFromMimeType(cR.getType(uri));
    }

    protected void uplooadFile(){
        if(mImageUri !=  null){
            StorageReference fileReference = mStorageRef.child(System.currentTimeMillis() + "." + getFileExtention(mImageUri));
            mUploadTask = fileReference.putFile(mImageUri)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            Handler handler = new Handler();
                            handler.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    mProgressBar.setProgress(0);
                                }
                            }, 500);
                            Toast.makeText(OfferUploadStep3.this, "Upload successful", Toast.LENGTH_SHORT).show();
                            Offer offer = new Offer();
                            offer.setBusinessID(offerStep2.getBusinessID());
                            offer.setDescription(offerStep2.getDescription());
                            offer.setFinishDate(offerStep2.getFinishDate());
                            offer.setName(offerStep2.getName());
                            offer.setOfferPrice(offerStep2.getOfferPrice());
                            offer.setRealPrice(offerStep2.getRealPrice());
                            offer.setStock(offerStep2.getStock());
                            offer.setImageURL(taskSnapshot.getStorage().getDownloadUrl().toString());

                            String uploadID = mDatabaseRef.push().getKey();
                            mDatabaseRef.child(uploadID).setValue(offer);

                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(OfferUploadStep3.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    })
                    .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                            double progress = (100 * taskSnapshot.getBytesTransferred() / taskSnapshot.getTotalByteCount());
                            mProgressBar.setProgress((int)progress);
                        }
                    });
        }else{
            Toast.makeText(this, "No file selected", Toast.LENGTH_SHORT).show();
        }
    }
}

