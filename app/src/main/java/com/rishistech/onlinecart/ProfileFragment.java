package com.rishistech.onlinecart;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.util.Objects;

public class ProfileFragment extends Fragment {

    private static final String TAG = "ProfileFragment";
    private TextView tvNameProfile, tvEmailProfile, tvPhonenoProfile, tvMoneyProfile;
    private Button btnEditProfile;
    private ImageView ivProfileImage;

    private FirebaseDatabase database;
    private DatabaseReference myRef;
    private FirebaseUser user;
    private String id;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_profile, container, false);

        tvNameProfile = view.findViewById(R.id.tvNameProfile);
        tvEmailProfile = view.findViewById(R.id.tvEmailProfile);
        tvPhonenoProfile = view.findViewById(R.id.tvPhonenoProfile);
        tvMoneyProfile = view.findViewById(R.id.tvMoneyProfile);
        btnEditProfile = view.findViewById(R.id.btnEditProfile);
        ivProfileImage = view.findViewById(R.id.ivProfileImage);
        btnEditProfile = view.findViewById(R.id.btnEditProfile);
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("users");
        user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            id = user.getUid();
        }

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot ds : dataSnapshot.getChildren()){
                    if (Objects.equals(ds.child("id").getValue(String.class), id)){
                        tvNameProfile.setText(ds.child("name").getValue(String.class));
                        tvEmailProfile.setText(ds.child("email").getValue(String.class));
                        tvPhonenoProfile.setText(ds.child("phoneno").getValue(String.class));
                        tvMoneyProfile.setText(ds.child("money").getValue(String.class));
                        Glide.with(getContext()).load(ds.child("photourl").getValue(String.class)).into(ivProfileImage);
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.d(TAG, "failed to READ ", databaseError.toException());
            }
        });

        btnEditProfile.setOnContextClickListener(new View.OnContextClickListener() {
            @Override
            public boolean onContextClick(View v) {
                return false;
            }
        });

        return view;
    }





}
