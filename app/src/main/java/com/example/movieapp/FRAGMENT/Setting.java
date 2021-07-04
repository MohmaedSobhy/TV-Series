package com.example.movieapp.FRAGMENT;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;


import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.movieapp.Activity.ForgetPassword;
import com.example.movieapp.Activity.login;
import com.example.movieapp.Data.User;
import com.example.movieapp.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.ByteArrayOutputStream;
import java.io.IOException;


public class Setting extends Fragment {


    Context context;
    boolean check;
    Button logout,changepassword,addcover;
    FirebaseUser auth;
    final static  Integer id_camera=100;
    final static Integer id_gallery=50;
    Dialog dialog;
    Bitmap image;
    ImageView profileImage;
    FirebaseStorage stroage;
    FirebaseDatabase database;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_setting, container, false);

        ConstraintLayout constraintLayout=view.findViewById(R.id.constraint);
        ImageView imageView=view.findViewById(R.id.noInternet);
        profileImage=view.findViewById(R.id.profile_image);

        if(check)
        {
            constraintLayout.setVisibility(View.VISIBLE);
            imageView.setVisibility(View.GONE);
            final TextView name,email,phone;
            name=view.findViewById(R.id.username);
            email=view.findViewById(R.id.useremail);
            phone=view.findViewById(R.id.userphone);
            logout=view.findViewById(R.id.logout);
            changepassword=view.findViewById(R.id.changepass);
            addcover=view.findViewById(R.id.addcover);
            auth=FirebaseAuth.getInstance().getCurrentUser();
            stroage=FirebaseStorage.getInstance();
            database=FirebaseDatabase.getInstance();

            
            DatabaseReference reference= FirebaseDatabase.getInstance().getReference("Users");

            FirebaseUser user=FirebaseAuth.getInstance().getCurrentUser();
            if(user!=null)
            {
                  if(user.getPhotoUrl()!=null)
                  {
                      Glide.with(context).asBitmap().load(user.getPhotoUrl())
                              .into(profileImage);
                  }
            }

            if(auth!=null)
            {

                reference.child(auth.getUid()).addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {


                        if(snapshot.getValue(User.class)!=null)
                        {
                            name.setText(snapshot.getValue(User.class).getName());
                            phone.setText(snapshot.getValue(User.class).getPhone());
                            email.setText(snapshot.getValue(User.class).getEmail());
                        }
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }

            logout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    FirebaseAuth.getInstance().signOut();
                    Intent value=new Intent(context, login.class);
                    value.putExtra("Logout",1); // send value
                    startActivity(value);
                }
            });

            changepassword.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    startActivity(new Intent(context, ForgetPassword.class));
                }
            });

            addcover.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    dialog=new Dialog(context);
                    dialog.setContentView(R.layout.showdialog);
                    dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

                    LinearLayout camera=dialog.findViewById(R.id.linearcamera);
                    LinearLayout gallery=dialog.findViewById(R.id.linear_gallery);

                    camera.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                            Toast.makeText(context, "Camera", Toast.LENGTH_SHORT).show();
                            openCamera();
                            dialog.dismiss();
                        }
                    });

                    gallery.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Toast.makeText(context, "Gallery", Toast.LENGTH_SHORT).show();
                            openGallery();
                            dialog.dismiss();
                        }
                    });

                    dialog.show();

                }
            });
        }

        else{
            // if no internet in our devices
         constraintLayout.setVisibility(View.GONE);
         imageView.setVisibility(View.VISIBLE);
        }

        return view;
    }

    public Setting(Context context, boolean check) {
        this.context=context;
        this.check=check;
    }

     public void openCamera()
     {

         Intent x=new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
         startActivityForResult(x,id_camera);
     }

     public void openGallery()
     {

         Intent x=new Intent(Intent.ACTION_GET_CONTENT);
         x.setType("image/*");
         startActivityForResult(x,id_gallery);
     }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);


        if(requestCode==id_gallery)
        {

            try {
                image=MediaStore.Images.Media.getBitmap(context.getContentResolver(),data.getData());
                HandleUpload();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else if(requestCode==id_camera)
        {
            image= (Bitmap) data.getExtras().get("data");
            profileImage.setImageBitmap(image);
            HandleUpload();
        }


        dialog.setContentView(R.layout.progressbar_dialoge);
        dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        dialog.show();
    }

    public void HandleUpload() {

        ByteArrayOutputStream file=new ByteArrayOutputStream();
        image.compress(Bitmap.CompressFormat.JPEG,100,file);
        StorageReference storageReference=FirebaseStorage.getInstance().getReference()
                .child("profile_images")
                .child(auth.getUid()+".jpeg");

        storageReference.putBytes(file.toByteArray())
                .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        getDowonloadUrl(storageReference);
                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void getDowonloadUrl(StorageReference storageReference) {
        storageReference.getDownloadUrl()
                .addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {
                        getURl(uri);
                    }
                });

    }

    private void getURl(Uri uri) {

        FirebaseUser user=auth;
        UserProfileChangeRequest request=new UserProfileChangeRequest.Builder()
                .setPhotoUri(uri)
                .build();

        user.updateProfile(request)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(context, "Upadate", Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                        profileImage.setImageBitmap(image);
                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(context, "some wrong happen", Toast.LENGTH_SHORT).show();
            }
        });
    }


}


