package com.lupinesoft.myfirebasecrud;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.OpenableColumns;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

public class SingleCreated extends AppCompatActivity {

    EditText etPName, etPDescr;
    Button btnSelect, btnSubmit;
    TextView tvPrev;

    Uri photoURI;

    DatabaseReference dbPhotoRef;
    StorageReference storageReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_created);

        etPName = findViewById(R.id.sc_et_photoName_ID);
        etPDescr = findViewById(R.id.sc_et_photoDescr_ID);
        btnSelect = findViewById(R.id.sc_btn_selectfile_ID);
        btnSubmit = findViewById(R.id.sc_btn_submit_ID);
        tvPrev = findViewById(R.id.sc_tv_prevName_ID);

        dbPhotoRef = FirebaseDatabase.getInstance().getReference().child("SinglePhoto");
        storageReference = FirebaseStorage.getInstance().getReference();

        btnSelect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent galleryIntent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
                galleryIntent.addCategory(Intent.CATEGORY_OPENABLE);
                galleryIntent.setType("image/*");
                startActivityForResult(galleryIntent, 7);
            }
        });

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(photoURI!=null){
                    uploadPhotoWithDetails(photoURI);
                }else {
                    Toast.makeText(SingleCreated.this, "Select Photo First.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode==7 && resultCode==RESULT_OK){
            photoURI = data.getData();
            Cursor returnCursor = getContentResolver().query(photoURI, null, null, null, null);
            int nameIndex = returnCursor.getColumnIndex(OpenableColumns.DISPLAY_NAME);
            returnCursor.moveToFirst();
            String strName = returnCursor.getString(nameIndex);
            tvPrev.setText(strName);
        }
    }

    public void uploadPhotoWithDetails(Uri uri){
       final StorageReference sReference = storageReference.child(System.currentTimeMillis() + "." +getFileExtension(uri));
        sReference.putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                sReference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {
                        PhotoModel photoModel = new PhotoModel(uri.toString(), etPName.getText().toString(), etPDescr.getText().toString());
                        dbPhotoRef.child(etPName.getText().toString()).setValue(photoModel);
                        Toast.makeText(SingleCreated.this, "Inserted.", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }

    public String getFileExtension(Uri mUri){
        ContentResolver cr = getContentResolver();
        MimeTypeMap mimeTypeMap = MimeTypeMap.getSingleton();
        return mimeTypeMap.getExtensionFromMimeType(cr.getType(mUri));
    }
}