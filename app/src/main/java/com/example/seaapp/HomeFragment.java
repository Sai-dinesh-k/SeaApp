package com.example.seaapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;

import com.example.seaapp.utils.FirebaseUtil;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class HomeFragment extends Fragment {

    private Button btnWeather, btnFishingArea, btnBaseStation, btnDashboard, btnNearbyBoat, btnChat;

    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        // Find buttons by their IDs
        btnWeather = view.findViewById(R.id.btnWeather);
        btnFishingArea = view.findViewById(R.id.btnFishingArea);
        btnBaseStation = view.findViewById(R.id.btnBaseStation);
        btnDashboard = view.findViewById(R.id.btnDashboard);
        btnNearbyBoat = view.findViewById(R.id.btnNearbyBoat);
        btnChat = view.findViewById(R.id.btnChat);

        String uid = FirebaseUtil.currentUserId();
        CollectionReference uc = FirebaseFirestore.getInstance().collection("users");
        DocumentReference df = uc.document(uid);

        btnBaseStation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                df.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                        String type = documentSnapshot.getString("type");
                        if(type.equals("0")){
                            startActivity(new Intent(getActivity(), BaseStationUser.class));
                        }else{
                            startActivity(new Intent(getActivity(), BaseStationActivity.class));
                        }
                    }
                });
            }
        });

        btnFishingArea.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), FishingAreaActivity.class));
            }
        });

        btnWeather.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), WeatherActivity.class));
            }
        });
        btnDashboard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), DashboardActivity.class));
            }
        });

        btnNearbyBoat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), NearbyBoatActivity.class));
            }
        });
        btnChat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), ChatActivity.class));
            }
        });

        return view;

    }


}