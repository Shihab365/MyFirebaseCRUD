package com.lupinesoft.myfirebasecrud;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.Activity;
import android.content.ContentResolver;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
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

import java.io.File;

public class SingleFileUpdate extends AppCompatActivity {

    EditText etName, etDescr;
    Button btnPick, btnSubmit;
    TextView tvPreview;

    DatabaseReference dbPhotoRef;
    StorageReference storageReference;

    Uri photoURI;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_file_update);

        etName = findViewById(R.id.sf_updt_et_name_id);
        etDescr = findViewById(R.id.sf_updt_et_descr_id);
        btnPick = findViewById(R.id.sf_updt_btn_pick_photo_id);
        btnSubmit = findViewById(R.id.sf_updt_btn_submit_id);
        tvPreview = findViewById(R.id.sf_updt_tv_preview_id);

        dbPhotoRef = FirebaseDatabase.getInstance().getReference().child("SinglePhoto");
        storageReference = FirebaseStorage.getInstance().getReference();

        btnPick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent galleryIntent = new Intent(Intent.ACTION_PICK);
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
                    Toast.makeText(SingleFileUpdate.this, "Select Photo First.", Toast.LENGTH_SHORT).show();
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
            tvPreview.setText(strName);
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
                        PhotoModel photoModel = new PhotoModel(uri.toString(), etName.getText().toString(), etDescr.getText().toString());
                        dbPhotoRef.child(etName.getText().toString()).setValue(photoModel);
                        Toast.makeText(SingleFileUpdate.this, "Updated", Toast.LENGTH_SHORT).show();
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