package com.sd.coronaviewer;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.File;
import java.io.IOException;

public class tamilnadu extends AppCompatActivity {

    public DatabaseReference indiacounts;
    public ProgressDialog loadingbar;
    public FirebaseStorage storage = FirebaseStorage.getInstance();
    StorageReference mStorageRef,mStorageRef2;

    public TextView update;

    public ImageView live,maps,todo,tamilnadu,tamilnadu2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tamilnadu);


        loadingbar = new ProgressDialog(tamilnadu.this);
        loadingbar.setCanceledOnTouchOutside(false);
        loadingbar.setMessage("Loading..");
        loadingbar.show();

        mStorageRef=storage.getReferenceFromUrl("gs://corona-viewer-19dd7.appspot.com").child("tamilnadu1.jpg");
        tamilnadu = findViewById(R.id.tnlistimage);
        try {
            final File file = File.createTempFile("image","jpg");
            mStorageRef.getFile(file).addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
                    loadingbar.dismiss();
                    Bitmap bitmap = BitmapFactory.decodeFile(file.getAbsolutePath());
                    tamilnadu.setImageBitmap(bitmap);
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(tamilnadu.this,"Data did not Load Properly",Toast.LENGTH_SHORT).show();
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }

        mStorageRef2=storage.getReferenceFromUrl("gs://corona-viewer-19dd7.appspot.com").child("tamilnadu2.jpg");
        tamilnadu2 = findViewById(R.id.tnlistimage2);
        try {
            final File files = File.createTempFile("image","jpg");
            mStorageRef2.getFile(files).addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
                    loadingbar.dismiss();
                    Bitmap bitmap = BitmapFactory.decodeFile(files.getAbsolutePath());
                    tamilnadu2.setImageBitmap(bitmap);
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(tamilnadu.this,"Data did not Load Properly",Toast.LENGTH_SHORT).show();
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }


        //ICONS//
        live = findViewById(R.id.liveiconlistact);
        maps = findViewById(R.id.mapiconlistact);
        todo = findViewById(R.id.todoiconlistact);
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(getApplicationContext(),todolist.class));
    }

    public void liveklik(View view) {
        startActivity(new Intent(getApplicationContext(),HomePage.class));
        finish();
    }

    public void mapklik(View view) {
        startActivity(new Intent(getApplicationContext(),MapsActivity.class));
    }

    public void todocklik(View view) {
        startActivity(new Intent(getApplicationContext(),todolist.class));
    }
}
