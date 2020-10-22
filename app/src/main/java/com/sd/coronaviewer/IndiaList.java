package com.sd.coronaviewer;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.File;
import java.io.IOException;

public class IndiaList extends AppCompatActivity {

    public DatabaseReference indiacounts;
    public ProgressDialog loadingbar;
    public FirebaseStorage storage = FirebaseStorage.getInstance();
    StorageReference mStorageRef,mStorageRef1,mStorageRef2;

    public TextView update;

    public ImageView live,maps,todo,indialist,indialist1,indialist2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_india_list);
        update = findViewById(R.id.updatedcountries);

        loadingbar = new ProgressDialog(IndiaList.this);
        loadingbar.setCanceledOnTouchOutside(false);
        loadingbar.setMessage("Loading..");
        loadingbar.show();

        mStorageRef=storage.getReferenceFromUrl("gs://corona-viewer-19dd7.appspot.com").child("coronavirus.jpg");

        indialist = findViewById(R.id.listimage);
        indialist1 = findViewById(R.id.listimage1);
        indialist2 = findViewById(R.id.listimage2);
        try {
            final File file = File.createTempFile("image","jpg");
            mStorageRef.getFile(file).addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
                    loadingbar.dismiss();
                    Bitmap bitmap = BitmapFactory.decodeFile(file.getAbsolutePath());
                    indialist.setImageBitmap(bitmap);
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(IndiaList.this,"Data did not Load Properly",Toast.LENGTH_SHORT).show();
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }

        mStorageRef1=storage.getReferenceFromUrl("gs://corona-viewer-19dd7.appspot.com").child("coronavirus1.jpg");

        try {
            final File files = File.createTempFile("image","jpg");
            mStorageRef1.getFile(files).addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
                    loadingbar.dismiss();
                    Bitmap bitmap = BitmapFactory.decodeFile(files.getAbsolutePath());
                    indialist1.setImageBitmap(bitmap);
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(IndiaList.this,"Data did not Load Properly",Toast.LENGTH_SHORT).show();
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }

        mStorageRef2=storage.getReferenceFromUrl("gs://corona-viewer-19dd7.appspot.com").child("coronavirus2.jpg");

        try {
            final File filesx = File.createTempFile("image","jpg");
            mStorageRef1.getFile(filesx).addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
                    loadingbar.dismiss();
                    Bitmap bitmap = BitmapFactory.decodeFile(filesx.getAbsolutePath());
                    indialist2.setImageBitmap(bitmap);
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(IndiaList.this,"Data did not Load Properly",Toast.LENGTH_SHORT).show();
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
        finish();
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
