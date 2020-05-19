package com.example.turn.Activity.Main.Fragment.FragmentReserve.Fragment;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.turn.Activity.Main.Activity_Main_Turn;
import com.example.turn.Activity.Main.Adapter.AdRecycPopUp;
import com.example.turn.Activity.Main.Adapter.onClickInterface;
import com.example.turn.Activity.Main.Adapter.setDataToFragment;
import com.example.turn.Activity.Main.Model.ModAlerts;
import com.example.turn.Classes.EnglishNumber;
import com.example.turn.Classes.ShowMessage;
import com.example.turn.Classes.setConnectionVolley;
import com.example.turn.R;
import com.google.android.material.snackbar.Snackbar;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class frTabRes_Filter extends Fragment implements SearchView.OnQueryTextListener {
    //  linearSelectFilters
    private AlertDialog alertDialogFilter;

    private TextView txtFrRes_city;
    private TextView txtFrRes_takhasos;
    private TextView txtFrRes_darmonghah;
    private TextView txtFrRes_time;
    private TextView txtFrRes_doctor;

    private LinearLayout linearFrRes_city;
    private LinearLayout linearFrRes_takhasos;
    private LinearLayout linearFrRes_darmonghah;
    private LinearLayout linearFrRes_time;
    private LinearLayout linearFrRes_doctor;

    private Button btnFrRes_filter;
    private Button btnFrRes_next;

    private ArrayList dataCity;
    private ArrayList dataTakhasos;
    private ArrayList dataTakhasos2;
    private ArrayList dataHospital;
    private ArrayList dataHospital2;
    private ArrayList dataTime;
    private ArrayList dataDoctor;
    private ArrayList dataDoctor2;

    private ArrayList dataCityID;
    private ArrayList dataTakhasosID;
    private ArrayList dataTakhasosID2;
    private ArrayList dataHospitalID;
    private ArrayList dataHospitalID2;
    private ArrayList dataTimeID;
    private ArrayList dataDoctorID;
    private ArrayList dataDoctorID2;

    private String cityId = "";
    private String takhasosId = "";
    private String hospiralId = "";
    private String timeId = "";
    private String doctorId = "";

    private String citySt = "";
    private String takhasosSt = "";
    private String hospiralSt = "";
    private String timeSt = "";
    private String doctorSt = "";

    private AlertDialog alertDialogLoding;


    public static frTabRes_Filter newInstance() {

        Bundle args = new Bundle();
        frTabRes_Filter fragment = new frTabRes_Filter();
        return fragment;
    }


    public void getDataFromFragment(String message) {
        Toast.makeText(getContext(), message+"frTabRes_Filter", Toast.LENGTH_SHORT).show();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fr_reserve_filter, container, false);

        loading();
        selectFilters(view);
        onAttachToParentFragment(getParentFragment());

        return view;
    }

    private void selectFilters(View view) {
        try {

//      this layout is for linearResTimes
            LinearLayout layoutBtnsRT = view.findViewById(R.id.layoutBtnsRT);
            layoutBtnsRT.setVisibility(View.GONE);

            txtFrRes_city = view.findViewById(R.id.txtFrRes_city);
            txtFrRes_takhasos = view.findViewById(R.id.txtFrRes_takhasos);
            txtFrRes_darmonghah = view.findViewById(R.id.txtFrRes_darmonghah);
            txtFrRes_time = view.findViewById(R.id.txtFrRes_time);
            txtFrRes_doctor = view.findViewById(R.id.txtFrRes_doctor);

            linearFrRes_city = view.findViewById(R.id.linearFrRes_city);
            linearFrRes_takhasos = view.findViewById(R.id.linearFrRes_takhasos);
            linearFrRes_darmonghah = view.findViewById(R.id.linearFrRes_darmonghah);
            linearFrRes_time = view.findViewById(R.id.linearFrRes_time);
            linearFrRes_doctor = view.findViewById(R.id.linearFrRes_doctor);

            btnFrRes_filter = view.findViewById(R.id.btnFrRes_filter);
            btnFrRes_next = view.findViewById(R.id.btnFrRes_next);

//  Clicks
            linearFrRes_city.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    alertDialogSearch("city");
                }
            });
            linearFrRes_takhasos.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    alertDialogSearch("takhasos");
                }
            });
            linearFrRes_darmonghah.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    alertDialogSearch("darmonghah");
                }
            });
            linearFrRes_time.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    alertDialogSearch("time");
                }
            });

            linearFrRes_doctor.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    alertDialogSearch("doctor");
                }
            });

            btnFrRes_filter.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    // TODO: clear all
                    defaultDataDropDown();
                }
            });
//merccccccccccccccccccccc
            btnFrRes_next.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                 /*   Snackbar.make(view, "This is main activity", Snackbar.LENGTH_LONG)
                            .setAction("CLOSE", new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {

                                }
                            })
                            .setActionTextColor(getResources().getColor(android.R.color.holo_red_light ))
                            .show();*/


                    try {
//                Toast.makeText(getContext(), "اجرا شدن لودینگ 2 ثانیه", Toast.LENGTH_SHORT).show();
                        alertDialogLoding.show();
                        //nextPage(linearSelectFilters, linearSelectFiltersBtn, linearResTimes, linearResTimesBtn);
                        //   arrayListResTimes.clear();
                        //  adapterResTimes.notifyDataSetChanged();
                    /*    pageNumber = 0;
                        morePost = true;*/
                        doSearch(0);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });

            dataCity = new ArrayList();
            dataTakhasos = new ArrayList();
            dataTakhasos2 = new ArrayList();
            dataHospital = new ArrayList();
            dataHospital2 = new ArrayList();
            dataTime = new ArrayList();
            dataDoctor = new ArrayList();
            dataDoctor2 = new ArrayList();
            dataCityID = new ArrayList();
            dataTakhasosID = new ArrayList();
            dataTakhasosID2 = new ArrayList();
            dataHospitalID = new ArrayList();
            dataHospitalID2 = new ArrayList();
            dataTimeID = new ArrayList();
            dataDoctorID = new ArrayList();
            dataDoctorID2 = new ArrayList();
//TODO------ Connection data for dropDowns link1

            JSONObject object = new JSONObject();
            try {
                //     object.put("hospital", "-1");
                //  object.put("city", "-1");
                // object.put("specialty", "-1");
                object.put("hsp_id", "0");
                object.put("spc_id", "0");
                object.put("dr_id", "0");
                object.put("date_period", "0");
                object.put("city_id", "0");
            } catch (Exception e) {
                e.printStackTrace();
            }

            alertDialogLoding.show();

            new setConnectionVolley(getContext(), "http://nobat.mazums.ac.ir/turnappApi/Search?spc_id=0&dr_id=0&city_id=0&hsp_id=0&date_period=0", object
            ).connectStringRequest(new setConnectionVolley.OnResponse() {
                @Override
                public void OnResponse(String response) {
                    setDropDownsData(response, "new");
                }
            });

            //  String response = "{\"status\":\"yes\",\"message\":\"\",\"data\":{ \"hospital\":[{\"id\":\"0\",\"title\":\"تمامی بیمارستان ها\"},{\"id\":\"4\",\"title\":\"بیمارستان شهید بهشتی\"},{\"id\":\"4\",\"title\":\"بیمارستان شهید بهشتی\"},{\"id\":\"4\",\"title\":\"بیمارستان شهید بهشتی\"},{\"id\":\"4\",\"title\":\"بیمارستان شهید بهشتی\"},{\"id\":\"4\",\"title\":\"بیمارستان شهید بهشتی\"},{\"id\":\"4\",\"title\":\"بیمارستان شهید بهشتی\"},{\"id\":\"4\",\"title\":\"بیمارستان شهید بهشتی\"},{\"id\":\"4\",\"title\":\"بیمارستان شهید بهشتی\"},{\"id\":\"4\",\"title\":\"بیمارستان شهید بهشتی\"},{\"id\":\"4\",\"title\":\"بیمارستان شهید بهشتی\"},{\"id\":\"4\",\"title\":\"بیمارستان شهید بهشتی\"},{\"id\":\"4\",\"title\":\"بیمارستان شهید بهشتی\"},{\"id\":\"4\",\"title\":\"بیمارستان شهید بهشتی\"},{\"id\":\"4\",\"title\":\"بیمارستان شهید بهشتی\"},{\"id\":\"4\",\"title\":\"بیمارستان شهید بهشتی\"},{\"id\":\"4\",\"title\":\"بیمارستان شهید بهشتی\"},{\"id\":\"4\",\"title\":\"بیمارستان شهید بهشتی\"},{\"id\":\"4\",\"title\":\"بیمارستان شهید بهشتی\"},{\"id\":\"4\",\"title\":\"بیمارستان شهید بهشتی\"},{\"id\":\"4\",\"title\":\"بیمارستان شهید بهشتی\"},{\"id\":\"4\",\"title\":\"بیمارستان شهید بهشتی\"},{\"id\":\"4\",\"title\":\"بیمارستان شهید بهشتی\"},{\"id\":\"4\",\"title\":\"بیمارستان شهید بهشتی\"},{\"id\":\"4\",\"title\":\"بیمارستان شهید بهشتی\"},{\"id\":\"4\",\"title\":\"بیمارستان شهید بهشتی\"},{\"id\":\"4\",\"title\":\"بیمارستان شهید بهشتی\"},{\"id\":\"4\",\"title\":\"بیمارستان شهید بهشتی\"},{\"id\":\"4\",\"title\":\"بیمارستان شهید بهشتی\"},{\"id\":\"4\",\"title\":\"بیمارستان شهید بهشتی\"},{\"id\":\"4\",\"title\":\"بیمارستان شهید بهشتی\"},{\"id\":\"4\",\"title\":\"بیمارستان شهید بهشتی\"},{\"id\":\"4\",\"title\":\"بیمارستان شهید بهشتی\"},{\"id\":\"4\",\"title\":\"بیمارستان شهید بهشتی\"},{\"id\":\"4\",\"title\":\"بیمارستان شهید بهشتی\"},{\"id\":\"4\",\"title\":\"بیمارستان شهید بهشتی\"},{\"id\":\"4\",\"title\":\"بیمارستان شهید بهشتی\"},{\"id\":\"4\",\"title\":\"بیمارستان شهید بهشتی\"},{\"id\":\"4\",\"title\":\"بیمارستان شهید بهشتی\"},{\"id\":\"4\",\"title\":\"بیمارستان شهید بهشتی\"},{\"id\":\"4\",\"title\":\"بیمارستان شهید بهشتی\"},{\"id\":\"4\",\"title\":\"بیمارستان شهید بهشتی\"},{\"id\":\"4\",\"title\":\"بیمارستان شهید بهشتی\"},{\"id\":\"4\",\"title\":\"بیمارستان شهید بهشتی\"},{\"id\":\"4\",\"title\":\"بیمارستان شهید بهشتی\"},{\"id\":\"4\",\"title\":\"بیمارستان شهید بهشتی\"},{\"id\":\"4\",\"title\":\"بیمارستان شهید بهشتی\"},{\"id\":\"4\",\"title\":\"بیمارستان شهید بهشتی\"},{\"id\":\"4\",\"title\":\"بیمارستان شهید بهشتی\"},{\"id\":\"4\",\"title\":\"بیمارستان شهید بهشتی\"},{\"id\":\"4\",\"title\":\"بیمارستان شهید بهشتی\"},{\"id\":\"4\",\"title\":\"بیمارستان شهید بهشتی\"},{\"id\":\"4\",\"title\":\"بیمارستان شهید بهشتی\"},{\"id\":\"4\",\"title\":\"بیمارستان شهید بهشتی\"},{\"id\":\"4\",\"title\":\"بیمارستان شهید بهشتی\"},{\"id\":\"4\",\"title\":\"بیمارستان شهید بهشتی\"},{\"id\":\"4\",\"title\":\"بیمارستان شهید بهشتی\"},{\"id\":\"4\",\"title\":\"بیمارستان شهید بهشتی\"},{\"id\":\"4\",\"title\":\"بیمارستان شهید بهشتی\"},{\"id\":\"4\",\"title\":\"بیمارستان شهید بهشتی\"},{\"id\":\"4\",\"title\":\"بیمارستان شهید بهشتی\"},{\"id\":\"4\",\"title\":\"بیمارستان شهید بهشتی\"},{\"id\":\"4\",\"title\":\"بیمارستان شهید بهشتی\"},{\"id\":\"4\",\"title\":\"بیمارستان شهید بهشتی\"},{\"id\":\"4\",\"title\":\"بیمارستان شهید بهشتی\"},{\"id\":\"4\",\"title\":\"بیمارستان شهید بهشتی\"},{\"id\":\"4\",\"title\":\"بیمارستان شهید بهشتی\"},{\"id\":\"4\",\"title\":\"بیمارستان شهید بهشتی\"},{\"id\":\"4\",\"title\":\"بیمارستان شهید بهشتی\"},{\"id\":\"4\",\"title\":\"بیمارستان شهید بهشتی\"},{\"id\":\"4\",\"title\":\"بیمارستان شهید بهشتی\"},{\"id\":\"4\",\"title\":\"بیمارستان شهید بهشتی\"},{\"id\":\"4\",\"title\":\"بیمارستان شهید بهشتی\"},{\"id\":\"4\",\"title\":\"بیمارستان شهید بهشتی\"},{\"id\":\"4\",\"title\":\"بیمارستان شهید بهشتی\"},{\"id\":\"4\",\"title\":\"بیمارستان شهید بهشتی\"},{\"id\":\"4\",\"title\":\"بیمارستان شهید بهشتی\"},{\"id\":\"4\",\"title\":\"بیمارستان شهید بهشتی\"},{\"id\":\"4\",\"title\":\"بیمارستان شهید بهشتی\"},{\"id\":\"4\",\"title\":\"بیمارستان شهید بهشتی\"},{\"id\":\"4\",\"title\":\"بیمارستان شهید بهشتی\"},{\"id\":\"4\",\"title\":\"بیمارستان شهید بهشتی\"},{\"id\":\"4\",\"title\":\"بیمارستان شهید بهشتی\"},{\"id\":\"4\",\"title\":\"بیمارستان شهید بهشتی\"},{\"id\":\"4\",\"title\":\"بیمارستان شهید بهشتی\"},{\"id\":\"4\",\"title\":\"بیمارستان شهید بهشتی\"},{\"id\":\"4\",\"title\":\"بیمارستان شهید بهشتی\"},{\"id\":\"4\",\"title\":\"بیمارستان شهید بهشتی\"},{\"id\":\"4\",\"title\":\"بیمارستان شهید بهشتی\"},{\"id\":\"4\",\"title\":\"بیمارستان شهید بهشتی\"},{\"id\":\"4\",\"title\":\"بیمارستان شهید بهشتی\"},{\"id\":\"4\",\"title\":\"بیمارستان شهید بهشتی\"},{\"id\":\"4\",\"title\":\"بیمارستان شهید بهشتی\"},{\"id\":\"4\",\"title\":\"بیمارستان شهید بهشتی\"},{\"id\":\"4\",\"title\":\"بیمارستان شهید بهشتی\"},{\"id\":\"4\",\"title\":\"بیمارستان شهید بهشتی\"},{\"id\":\"4\",\"title\":\"بیمارستان شهید بهشتی\"},{\"id\":\"4\",\"title\":\"بیمارستان شهید بهشتی\"},{\"id\":\"4\",\"title\":\"بیمارستان شهید بهشتی\"},{\"id\":\"4\",\"title\":\"بیمارستان شهید بهشتی\"},{\"id\":\"4\",\"title\":\"بیمارستان شهید بهشتی\"},{\"id\":\"4\",\"title\":\"بیمارستان شهید بهشتی\"},{\"id\":\"4\",\"title\":\"بیمارستان شهید بهشتی\"},{\"id\":\"4\",\"title\":\"بیمارستان شهید بهشتی\"},{\"id\":\"4\",\"title\":\"بیمارستان شهید بهشتی\"},{\"id\":\"4\",\"title\":\"بیمارستان شهید بهشتی\"},{\"id\":\"4\",\"title\":\"بیمارستان شهید بهشتی\"},{\"id\":\"4\",\"title\":\"بیمارستان شهید بهشتی\"},{\"id\":\"4\",\"title\":\"بیمارستان شهید بهشتی\"},{\"id\":\"4\",\"title\":\"بیمارستان شهید بهشتی\"},{\"id\":\"4\",\"title\":\"بیمارستان شهید بهشتی\"},{\"id\":\"4\",\"title\":\"بیمارستان شهید بهشتی\"},{\"id\":\"4\",\"title\":\"بیمارستان شهید بهشتی\"},{\"id\":\"4\",\"title\":\"بیمارستان شهید بهشتی\"},{\"id\":\"4\",\"title\":\"بیمارستان شهید بهشتی\"},{\"id\":\"4\",\"title\":\"بیمارستان شهید بهشتی\"},{\"id\":\"4\",\"title\":\"بیمارستان شهید بهشتی\"},{\"id\":\"4\",\"title\":\"بیمارستان شهید بهشتی\"},{\"id\":\"4\",\"title\":\"بیمارستان شهید بهشتی\"},{\"id\":\"4\",\"title\":\"بیمارستان شهید بهشتی\"},{\"id\":\"4\",\"title\":\"بیمارستان شهید بهشتی\"},{\"id\":\"4\",\"title\":\"بیمارستان شهید بهشتی\"},{\"id\":\"4\",\"title\":\"بیمارستان شهید بهشتی\"},{\"id\":\"4\",\"title\":\"بیمارستان شهید بهشتی\"},{\"id\":\"4\",\"title\":\"بیمارستان شهید بهشتی\"},{\"id\":\"4\",\"title\":\"بیمارستان شهید بهشتی\"},{\"id\":\"4\",\"title\":\"بیمارستان شهید بهشتی\"},{\"id\":\"4\",\"title\":\"بیمارستان شهید بهشتی\"},{\"id\":\"4\",\"title\":\"بیمارستان شهید بهشتی\"},{\"id\":\"4\",\"title\":\"بیمارستان شهید بهشتی\"},{\"id\":\"4\",\"title\":\"بیمارستان شهید بهشتی\"},{\"id\":\"4\",\"title\":\"بیمارستان شهید بهشتی\"},{\"id\":\"4\",\"title\":\"بیمارستان شهید بهشتی\"},{\"id\":\"4\",\"title\":\"بیمارستان شهید بهشتی\"},{\"id\":\"4\",\"title\":\"بیمارستان شهید بهشتی\"},{\"id\":\"4\",\"title\":\"بیمارستان شهید بهشتی\"},{\"id\":\"4\",\"title\":\"بیمارستان شهید بهشتی\"},{\"id\":\"4\",\"title\":\"بیمارستان شهید بهشتی\"},{\"id\":\"4\",\"title\":\"بیمارستان شهید بهشتی\"},{\"id\":\"4\",\"title\":\"بیمارستان شهید بهشتی\"},{\"id\":\"4\",\"title\":\"بیمارستان شهید بهشتی\"},{\"id\":\"4\",\"title\":\"بیمارستان شهید بهشتی\"},{\"id\":\"4\",\"title\":\"بیمارستان شهید بهشتی\"},{\"id\":\"4\",\"title\":\"بیمارستان شهید بهشتی\"},{\"id\":\"4\",\"title\":\"بیمارستان شهید بهشتی\"},{\"id\":\"4\",\"title\":\"بیمارستان شهید بهشتی\"},{\"id\":\"4\",\"title\":\"بیمارستان شهید بهشتی\"},{\"id\":\"4\",\"title\":\"بیمارستان شهید بهشتی\"},{\"id\":\"4\",\"title\":\"بیمارستان شهید بهشتی\"},{\"id\":\"4\",\"title\":\"بیمارستان شهید بهشتی\"},{\"id\":\"4\",\"title\":\"بیمارستان شهید بهشتی\"},{\"id\":\"4\",\"title\":\"بیمارستان شهید بهشتی\"},{\"id\":\"4\",\"title\":\"بیمارستان شهید بهشتی\"},{\"id\":\"4\",\"title\":\"بیمارستان شهید بهشتی\"},{\"id\":\"4\",\"title\":\"بیمارستان شهید بهشتی\"},{\"id\":\"4\",\"title\":\"بیمارستان شهید بهشتی\"},{\"id\":\"4\",\"title\":\"بیمارستان شهید بهشتی\"},{\"id\":\"4\",\"title\":\"بیمارستان شهید بهشتی\"},{\"id\":\"4\",\"title\":\"بیمارستان شهید بهشتی\"},{\"id\":\"4\",\"title\":\"بیمارستان شهید بهشتی\"},{\"id\":\"4\",\"title\":\"بیمارستان شهید بهشتی\"},{\"id\":\"4\",\"title\":\"بیمارستان شهید بهشتی\"},{\"id\":\"4\",\"title\":\"بیمارستان شهید بهشتی\"},{\"id\":\"4\",\"title\":\"بیمارستان شهید بهشتی\"},{\"id\":\"4\",\"title\":\"بیمارستان شهید بهشتی\"},{\"id\":\"4\",\"title\":\"بیمارستان شهید بهشتی\"},{\"id\":\"4\",\"title\":\"بیمارستان شهید بهشتی\"},{\"id\":\"4\",\"title\":\"بیمارستان شهید بهشتی\"},{\"id\":\"4\",\"title\":\"بیمارستان شهید بهشتی\"},{\"id\":\"4\",\"title\":\"بیمارستان شهید بهشتی\"},{\"id\":\"4\",\"title\":\"بیمارستان شهید بهشتی\"},{\"id\":\"4\",\"title\":\"بیمارستان شهید بهشتی\"},{\"id\":\"4\",\"title\":\"بیمارستان شهید بهشتی\"},{\"id\":\"4\",\"title\":\"بیمارستان شهید بهشتی\"},{\"id\":\"4\",\"title\":\"بیمارستان شهید بهشتی\"},{\"id\":\"4\",\"title\":\"بیمارستان شهید بهشتی\"},{\"id\":\"4\",\"title\":\"بیمارستان شهید بهشتی\"},{\"id\":\"4\",\"title\":\"بیمارستان شهید بهشتی\"},{\"id\":\"4\",\"title\":\"بیمارستان شهید بهشتی\"},{\"id\":\"4\",\"title\":\"بیمارستان شهید بهشتی\"},{\"id\":\"4\",\"title\":\"بیمارستان شهید بهشتی\"},{\"id\":\"4\",\"title\":\"بیمارستان شهید بهشتی\"},{\"id\":\"4\",\"title\":\"بیمارستان شهید بهشتی\"},{\"id\":\"4\",\"title\":\"بیمارستان شهید بهشتی\"},{\"id\":\"4\",\"title\":\"بیمارستان شهید بهشتی\"},{\"id\":\"4\",\"title\":\"بیمارستان شهید بهشتی\"},{\"id\":\"4\",\"title\":\"بیمارستان شهید بهشتی\"},{\"id\":\"4\",\"title\":\"بیمارستان شهید بهشتی\"},{\"id\":\"4\",\"title\":\"بیمارستان شهید بهشتی\"},{\"id\":\"4\",\"title\":\"بیمارستان شهید بهشتی\"},{\"id\":\"4\",\"title\":\"بیمارستان شهید بهشتی\"},{\"id\":\"4\",\"title\":\"بیمارستان شهید بهشتی\"},{\"id\":\"4\",\"title\":\"بیمارستان شهید بهشتی\"},{\"id\":\"4\",\"title\":\"بیمارستان شهید بهشتی\"},{\"id\":\"4\",\"title\":\"بیمارستان شهید بهشتی\"},{\"id\":\"4\",\"title\":\"بیمارستان شهید بهشتی\"},{\"id\":\"4\",\"title\":\"بیمارستان شهید بهشتی\"},{\"id\":\"4\",\"title\":\"بیمارستان شهید بهشتی\"},{\"id\":\"4\",\"title\":\"بیمارستان شهید بهشتی\"},{\"id\":\"4\",\"title\":\"بیمارستان شهید بهشتی\"},{\"id\":\"4\",\"title\":\"بیمارستان شهید بهشتی\"},{\"id\":\"4\",\"title\":\"بیمارستان شهید بهشتی\"},{\"id\":\"4\",\"title\":\"بیمارستان شهید بهشتی\"},{\"id\":\"4\",\"title\":\"بیمارستان شهید بهشتی\"},{\"id\":\"4\",\"title\":\"بیمارستان شهید بهشتی\"},{\"id\":\"4\",\"title\":\"بیمارستان شهید بهشتی\"},{\"id\":\"4\",\"title\":\"بیمارستان شهید بهشتی\"},{\"id\":\"4\",\"title\":\"بیمارستان شهید بهشتی\"},{\"id\":\"4\",\"title\":\"بیمارستان شهید بهشتی\"},{\"id\":\"4\",\"title\":\"بیمارستان شهید بهشتی\"},{\"id\":\"4\",\"title\":\"بیمارستان شهید بهشتی\"},{\"id\":\"4\",\"title\":\"بیمارستان شهید بهشتی\"},{\"id\":\"4\",\"title\":\"بیمارستان شهید بهشتی\"},{\"id\":\"4\",\"title\":\"بیمارستان شهید بهشتی\"},{\"id\":\"4\",\"title\":\"بیمارستان شهید بهشتی\"},{\"id\":\"4\",\"title\":\"بیمارستان شهید بهشتی\"},{\"id\":\"4\",\"title\":\"بیمارستان شهید بهشتی\"},{\"id\":\"4\",\"title\":\"بیمارستان شهید بهشتی\"},{\"id\":\"4\",\"title\":\"بیمارستان شهید بهشتی\"},{\"id\":\"4\",\"title\":\"بیمارستان شهید بهشتی\"},{\"id\":\"4\",\"title\":\"بیمارستان شهید بهشتی\"},{\"id\":\"4\",\"title\":\"بیمارستان شهید بهشتی\"},{\"id\":\"4\",\"title\":\"بیمارستان شهید بهشتی\"},{\"id\":\"4\",\"title\":\"بیمارستان شهید بهشتی\"},{\"id\":\"4\",\"title\":\"بیمارستان شهید بهشتی\"},{\"id\":\"4\",\"title\":\"بیمارستان شهید بهشتی\"},{\"id\":\"4\",\"title\":\"بیمارستان شهید بهشتی\"},{\"id\":\"4\",\"title\":\"بیمارستان شهید بهشتی\"},{\"id\":\"4\",\"title\":\"بیمارستان شهید بهشتی\"},{\"id\":\"4\",\"title\":\"بیمارستان شهید بهشتی\"},{\"id\":\"4\",\"title\":\"بیمارستان شهید بهشتی\"},{\"id\":\"4\",\"title\":\"بیمارستان شهید بهشتی\"},{\"id\":\"4\",\"title\":\"بیمارستان شهید بهشتی\"},{\"id\":\"4\",\"title\":\"بیمارستان شهید بهشتی\"},{\"id\":\"4\",\"title\":\"بیمارستان شهید بهشتی\"},{\"id\":\"4\",\"title\":\"بیمارستان شهید بهشتی\"},{\"id\":\"4\",\"title\":\"بیمارستان شهید بهشتی\"},{\"id\":\"4\",\"title\":\"بیمارستان شهید بهشتی\"},{\"id\":\"4\",\"title\":\"بیمارستان شهید بهشتی\"},{\"id\":\"4\",\"title\":\"بیمارستان شهید بهشتی\"},{\"id\":\"4\",\"title\":\"بیمارستان شهید بهشتی\"},{\"id\":\"4\",\"title\":\"بیمارستان شهید بهشتی\"},{\"id\":\"4\",\"title\":\"بیمارستان شهید بهشتی\"},{\"id\":\"4\",\"title\":\"بیمارستان شهید بهشتی\"},{\"id\":\"4\",\"title\":\"بیمارستان شهید بهشتی\"},{\"id\":\"4\",\"title\":\"بیمارستان شهید بهشتی\"},{\"id\":\"4\",\"title\":\"بیمارستان شهید بهشتی\"},{\"id\":\"4\",\"title\":\"بیمارستان شهید بهشتی\"},{\"id\":\"4\",\"title\":\"بیمارستان شهید بهشتی\"},{\"id\":\"4\",\"title\":\"بیمارستان شهید بهشتی\"},{\"id\":\"4\",\"title\":\"بیمارستان شهید بهشتی\"},{\"id\":\"4\",\"title\":\"بیمارستان شهید بهشتی\"},{\"id\":\"4\",\"title\":\"بیمارستان شهید بهشتی\"},{\"id\":\"4\",\"title\":\"بیمارستان شهید بهشتی\"},{\"id\":\"4\",\"title\":\"بیمارستان شهید بهشتی\"},{\"id\":\"4\",\"title\":\"بیمارستان شهید بهشتی\"},{\"id\":\"4\",\"title\":\"بیمارستان شهید بهشتی\"},{\"id\":\"4\",\"title\":\"بیمارستان شهید بهشتی\"},{\"id\":\"4\",\"title\":\"بیمارستان شهید بهشتی\"},{\"id\":\"4\",\"title\":\"بیمارستان شهید بهشتی\"},{\"id\":\"4\",\"title\":\"بیمارستان شهید بهشتی\"},{\"id\":\"4\",\"title\":\"بیمارستان شهید بهشتی\"},{\"id\":\"4\",\"title\":\"بیمارستان شهید بهشتی\"},{\"id\":\"4\",\"title\":\"بیمارستان شهید بهشتی\"},{\"id\":\"4\",\"title\":\"بیمارستان شهید بهشتی\"},{\"id\":\"4\",\"title\":\"بیمارستان شهید بهشتی\"},{\"id\":\"4\",\"title\":\"بیمارستان شهید بهشتی\"},{\"id\":\"4\",\"title\":\"بیمارستان شهید بهشتی\"},{\"id\":\"4\",\"title\":\"بیمارستان شهید بهشتی\"},{\"id\":\"4\",\"title\":\"بیمارستان شهید بهشتی\"},{\"id\":\"4\",\"title\":\"بیمارستان شهید بهشتی\"},{\"id\":\"4\",\"title\":\"بیمارستان شهید بهشتی\"},{\"id\":\"4\",\"title\":\"بیمارستان شهید بهشتی\"},{\"id\":\"4\",\"title\":\"بیمارستان شهید بهشتی\"},{\"id\":\"4\",\"title\":\"بیمارستان شهید بهشتی\"},{\"id\":\"4\",\"title\":\"بیمارستان شهید بهشتی\"},{\"id\":\"4\",\"title\":\"بیمارستان شهید بهشتی\"},{\"id\":\"4\",\"title\":\"بیمارستان شهید بهشتی\"},{\"id\":\"4\",\"title\":\"بیمارستان شهید بهشتی\"},{\"id\":\"4\",\"title\":\"بیمارستان شهید بهشتی\"},{\"id\":\"4\",\"title\":\"بیمارستان شهید بهشتی\"},{\"id\":\"4\",\"title\":\"بیمارستان شهید بهشتی\"},{\"id\":\"4\",\"title\":\"بیمارستان شهید بهشتی\"},{\"id\":\"4\",\"title\":\"بیمارستان شهید بهشتی\"},{\"id\":\"4\",\"title\":\"بیمارستان شهید بهشتی\"},{\"id\":\"4\",\"title\":\"بیمارستان شهید بهشتی\"},{\"id\":\"4\",\"title\":\"بیمارستان شهید بهشتی\"},{\"id\":\"4\",\"title\":\"بیمارستان شهید بهشتی\"},{\"id\":\"4\",\"title\":\"بیمارستان شهید بهشتی\"},{\"id\":\"4\",\"title\":\"بیمارستان شهید بهشتی\"},{\"id\":\"4\",\"title\":\"بیمارستان شهید بهشتی\"},{\"id\":\"4\",\"title\":\"بیمارستان شهید بهشتی\"},{\"id\":\"4\",\"title\":\"بیمارستان شهید بهشتی\"},{\"id\":\"4\",\"title\":\"بیمارستان شهید بهشتی\"},{\"id\":\"4\",\"title\":\"بیمارستان شهید بهشتی\"},{\"id\":\"4\",\"title\":\"بیمارستان شهید بهشتی\"},{\"id\":\"4\",\"title\":\"بیمارستان شهید بهشتی\"},{\"id\":\"4\",\"title\":\"بیمارستان شهید بهشتی\"},{\"id\":\"4\",\"title\":\"بیمارستان شهید بهشتی\"},{\"id\":\"4\",\"title\":\"بیمارستان شهید بهشتی\"},{\"id\":\"4\",\"title\":\"بیمارستان شهید بهشتی\"},{\"id\":\"4\",\"title\":\"بیمارستان شهید بهشتی\"},{\"id\":\"4\",\"title\":\"بیمارستان شهید بهشتی\"},{\"id\":\"4\",\"title\":\"بیمارستان شهید بهشتی\"},{\"id\":\"4\",\"title\":\"بیمارستان شهید بهشتی\"},{\"id\":\"4\",\"title\":\"بیمارستان شهید بهشتی\"},{\"id\":\"4\",\"title\":\"بیمارستان شهید بهشتی\"},{\"id\":\"4\",\"title\":\"بیمارستان شهید بهشتی\"},{\"id\":\"4\",\"title\":\"بیمارستان شهید بهشتی\"},{\"id\":\"4\",\"title\":\"بیمارستان شهید بهشتی\"},{\"id\":\"4\",\"title\":\"بیمارستان شهید بهشتی\"},{\"id\":\"4\",\"title\":\"بیمارستان شهید بهشتی\"},{\"id\":\"4\",\"title\":\"بیمارستان شهید بهشتی\"},{\"id\":\"4\",\"title\":\"بیمارستان شهید بهشتی\"},{\"id\":\"4\",\"title\":\"بیمارستان شهید بهشتی\"},{\"id\":\"4\",\"title\":\"بیمارستان شهید بهشتی\"},{\"id\":\"4\",\"title\":\"بیمارستان شهید بهشتی\"},{\"id\":\"4\",\"title\":\"بیمارستان شهید بهشتی\"},{\"id\":\"4\",\"title\":\"بیمارستان شهید بهشتی\"},{\"id\":\"4\",\"title\":\"بیمارستان شهید بهشتی\"},{\"id\":\"4\",\"title\":\"بیمارستان شهید بهشتی\"},{\"id\":\"4\",\"title\":\"بیمارستان شهید بهشتی\"},{\"id\":\"4\",\"title\":\"بیمارستان شهید بهشتی\"},{\"id\":\"4\",\"title\":\"بیمارستان شهید بهشتی\"},{\"id\":\"4\",\"title\":\"بیمارستان شهید بهشتی\"},{\"id\":\"4\",\"title\":\"بیمارستان شهید بهشتی\"},{\"id\":\"4\",\"title\":\"بیمارستان شهید بهشتی\"},{\"id\":\"4\",\"title\":\"بیمارستان شهید بهشتی\"},{\"id\":\"4\",\"title\":\"بیمارستان شهید بهشتی\"},{\"id\":\"4\",\"title\":\"بیمارستان شهید بهشتی\"},{\"id\":\"4\",\"title\":\"بیمارستان شهید بهشتی\"},{\"id\":\"4\",\"title\":\"بیمارستان شهید بهشتی\"},{\"id\":\"4\",\"title\":\"بیمارستان شهید بهشتی\"},{\"id\":\"4\",\"title\":\"بیمارستان شهید بهشتی\"},{\"id\":\"4\",\"title\":\"بیمارستان شهید بهشتی\"},{\"id\":\"4\",\"title\":\"بیمارستان شهید بهشتی\"},{\"id\":\"4\",\"title\":\"بیمارستان شهید بهشتی\"},{\"id\":\"4\",\"title\":\"بیمارستان شهید بهشتی\"},{\"id\":\"4\",\"title\":\"بیمارستان شهید بهشتی\"},{\"id\":\"4\",\"title\":\"بیمارستان شهید بهشتی\"},{\"id\":\"4\",\"title\":\"بیمارستان شهید بهشتی\"},{\"id\":\"4\",\"title\":\"بیمارستان شهید بهشتی\"},{\"id\":\"4\",\"title\":\"بیمارستان شهید بهشتی\"},{\"id\":\"4\",\"title\":\"بیمارستان شهید بهشتی\"},{\"id\":\"4\",\"title\":\"بیمارستان شهید بهشتی\"},{\"id\":\"4\",\"title\":\"بیمارستان شهید بهشتی\"},{\"id\":\"4\",\"title\":\"بیمارستان شهید بهشتی\"},{\"id\":\"4\",\"title\":\"بیمارستان شهید بهشتی\"},{\"id\":\"4\",\"title\":\"بیمارستان شهید بهشتی\"},{\"id\":\"4\",\"title\":\"بیمارستان شهید بهشتی\"},{\"id\":\"4\",\"title\":\"بیمارستان شهید بهشتی\"},{\"id\":\"4\",\"title\":\"بیمارستان شهید بهشتی\"},{\"id\":\"4\",\"title\":\"بیمارستان شهید بهشتی\"},{\"id\":\"4\",\"title\":\"بیمارستان شهید بهشتی\"},{\"id\":\"4\",\"title\":\"بیمارستان شهید بهشتی\"},{\"id\":\"4\",\"title\":\"بیمارستان شهید بهشتی\"},{\"id\":\"4\",\"title\":\"بیمارستان شهید بهشتی\"},{\"id\":\"4\",\"title\":\"بیمارستان شهید بهشتی\"},{\"id\":\"4\",\"title\":\"بیمارستان شهید بهشتی\"},{\"id\":\"4\",\"title\":\"بیمارستان شهید بهشتی\"},{\"id\":\"4\",\"title\":\"بیمارستان شهید بهشتی\"},{\"id\":\"4\",\"title\":\"بیمارستان شهید بهشتی\"},{\"id\":\"4\",\"title\":\"بیمارستان شهید بهشتی\"},{\"id\":\"4\",\"title\":\"بیمارستان شهید بهشتی\"},{\"id\":\"1\",\"title\":\"بیمارستان عمومی ماساچوست\"}],\"doctor\":[{\"id\":\"0\",\"title\":\"تمامی دکترها\"},{\"id\":\"4\",\"title\":\"علی علیزاده\"},{\"id\":\"1\",\"title\":\"حسین زارعی\"}],\"specialty\":[{\"id\":\"0\",\"title\":\"تمامی تخصص ها\"},{\"id\":\"6\",\"title\":\"اطفال\"},{\"id\":\"1\",\"title\":\"عفونی\"}],\"time\":[{\"id\":\"0\",\"title\":\"تمامی زمان ها\"},{\"id\":\"4\",\"title\":\"فردا\"},{\"id\":\"5\",\"title\":\"هفته ی جاری\"},{\"id\":\"4\",\"title\":\"ماه جاری\"}],\"city\":[{\"id\":\"0\",\"title\":\"تمامی شهرها\"},{\"id\":\"1\",\"title\":\"اراک\"},{\"id\":\"2\",\"title\":\"امل\"},{\"id\":\"41\",\"title\":\"تهران\"}]}}";
            //setDropDownsData(response, "new");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void defaultDataDropDown() {
        try {
            dataHospital = new ArrayList(dataHospital2);
            dataHospitalID = new ArrayList(dataHospitalID2);
            dataTakhasos = new ArrayList(dataTakhasos2);
            dataTakhasosID = new ArrayList(dataTakhasosID2);
            dataDoctor = new ArrayList(dataDoctor2);
            dataDoctorID = new ArrayList(dataDoctorID2);
            txtFrRes_city.setText(dataCity.get(0) + "");
            txtFrRes_takhasos.setText(dataTakhasos.get(0) + "");
            txtFrRes_darmonghah.setText(dataHospital.get(0) + "");
            txtFrRes_time.setText(dataTime.get(0) + "");
            txtFrRes_doctor.setText(dataDoctor.get(0) + "");
            cityId = dataCityID.get(0) + "";
            takhasosId = dataTakhasosID.get(0) + "";
            hospiralId = dataHospitalID.get(0) + "";
            timeId = dataTimeID.get(0) + "";
            doctorId = dataDoctorID.get(0) + "";
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void loading() {
        try {
            AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
            builder.setCancelable(false);
            LinearLayout layout = (LinearLayout) getLayoutInflater().inflate(R.layout.loading, null, false);

            builder.setView(layout);
            alertDialogLoding = builder.create();
            alertDialogLoding.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void setDropDownsData(String res, String tag) {
        try {
            if (alertDialogLoding.isShowing()) alertDialogLoding.dismiss();

            JSONObject object = new JSONObject(res);
            String status = object.getString("status");
            String message = object.getString("message");

            if (status.equals("yes")) {
                String data = object.getString("data");
                JSONObject objData = new JSONObject(data);
                JSONArray arrayHospital = objData.getJSONArray("hospital");
                JSONArray arrayDoctor = objData.getJSONArray("doctor");
                JSONArray arraySpecialty = objData.getJSONArray("specialty");
                JSONArray arrayTime = objData.getJSONArray("time");
                JSONArray arrayCity = objData.getJSONArray("city");

                if (tag.equals("new")) {

                    for (int i = 0; i < arrayHospital.length(); i++) {
                        JSONObject object1 = arrayHospital.getJSONObject(i);
                        dataHospitalID.add(object1.getString("id"));
                        dataHospital.add(object1.getString("title"));
                        dataHospitalID2.add(object1.getString("id"));
                        dataHospital2.add(object1.getString("title"));
                    }
                    for (int i = 0; i < arrayDoctor.length(); i++) {
                        JSONObject object1 = arrayDoctor.getJSONObject(i);
                        dataDoctorID.add(object1.getString("id"));
                        dataDoctor.add(object1.getString("title"));
                        dataDoctorID2.add(object1.getString("id"));
                        dataDoctor2.add(object1.getString("title"));
                    }
                    for (int i = 0; i < arraySpecialty.length(); i++) {
                        JSONObject object1 = arraySpecialty.getJSONObject(i);
                        dataTakhasosID.add(object1.getString("id"));
                        dataTakhasos.add(object1.getString("title"));
                        dataTakhasosID2.add(object1.getString("id"));
                        dataTakhasos2.add(object1.getString("title"));
                    }
                    for (int i = 0; i < arrayTime.length(); i++) {
                        JSONObject object1 = arrayTime.getJSONObject(i);
                        dataTimeID.add(object1.getString("id"));
                        dataTime.add(object1.getString("title"));
                    }
                    for (int i = 0; i < arrayCity.length(); i++) {
                        JSONObject object1 = arrayCity.getJSONObject(i);
                        dataCityID.add(object1.getString("id"));
                        dataCity.add(object1.getString("title"));
                    }
                    defaultDataDropDown();

                } else if (tag.equals("updateHospital")) {

                    if (dataHospital.size() != 0)
                        dataHospital.clear();
                    if (dataHospitalID.size() != 0)
                        dataHospitalID.clear();
                    for (int i = 0; i < arrayHospital.length(); i++) {
                        JSONObject object1 = arrayHospital.getJSONObject(i);
                        dataHospitalID.add(object1.getString("id"));
                        dataHospital.add(object1.getString("title"));

                        txtFrRes_darmonghah.setText(dataHospital.get(0) + "");
                        hospiralId = dataHospitalID.get(0) + "";

                    }

                } else if (tag.equals("updateTakasos")) {

                    if (dataTakhasos.size() != 0) {
                        dataTakhasos.clear();
                        dataTakhasosID.clear();
                    }
                    for (int i = 0; i < arrayHospital.length(); i++) {
                        JSONObject object1 = arrayHospital.getJSONObject(i);
                        dataTakhasosID.add(object1.getString("id"));
                        dataTakhasos.add(object1.getString("title"));

                        txtFrRes_takhasos.setText(dataTakhasos.get(0) + "");
                        takhasosId = dataTakhasosID.get(0) + "";

                    }

                } else if (tag.equals("updateDoctor")) {

                    if (dataDoctor.size() != 0) {
                        dataDoctor.clear();
                        dataDoctorID.clear();
                    }
                    for (int i = 0; i < arrayDoctor.length(); i++) {
                        JSONObject object1 = arrayDoctor.getJSONObject(i);
                        dataDoctorID.add(object1.getString("id"));
                        dataDoctor.add(object1.getString("title"));

                        txtFrRes_doctor.setText(dataDoctor.get(0) + "");
                        doctorId = dataDoctorID.get(0) + "";

                    }

                }
            } else
                new ShowMessage(getContext()).ShowMessType2_NoBtn(message, true, 2);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void doSearch(int pageNumber) {
        if (alertDialogLoding.isShowing())
            alertDialogLoding.dismiss();
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("cityId", cityId);
            jsonObject.put("takhasosId", takhasosId);
            jsonObject.put("hospiralId", hospiralId);
            jsonObject.put("timeId", timeId);
            jsonObject.put("doctorId", doctorId);
            jsonObject.put("doctorSt", doctorSt);
        } catch (Exception e) {
            e.printStackTrace();
        }
        msetDataToFragment.setDataToFragment(jsonObject.toString(), "firstToSecond");
    }

    //    SearchView
    private void alertDialogSearch(final String tag) {
        try {
            AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
            LinearLayout layout = (LinearLayout) getLayoutInflater().inflate(R.layout.item_listview_chooser, null, false);

            Button btnFr = layout.findViewById(R.id.btnFr);
            btnFr.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    alertDialogFilter.dismiss();
                }
            });

            ArrayList arrayList = new ArrayList();
            ArrayList arrayListID = new ArrayList();

            if (tag.equals("city")) {
                arrayList = new ArrayList(dataCity);
                arrayListID = new ArrayList(dataCityID);

            } else if (tag.equals("takhasos")) {
                arrayList = new ArrayList(dataTakhasos);
                arrayListID = new ArrayList(dataTakhasosID);
            } else if (tag.equals("darmonghah")) {
                arrayList = new ArrayList(dataHospital);
                arrayListID = new ArrayList(dataHospitalID);
            } else if (tag.equals("time")) {
                arrayList = new ArrayList(dataTime);
                arrayListID = new ArrayList(dataTimeID);
            } else if (tag.equals("doctor")) {
                arrayList = new ArrayList(dataDoctor);
                arrayListID = new ArrayList(dataDoctorID);
            }


            if (arraylistSearchView.size() != 0)
                arraylistSearchView.clear();

            for (int i = 0; i < arrayList.size(); i++) {
                ModAlerts modAlerts = new ModAlerts(arrayList.get(i) + "", arrayListID.get(i) + "");
                arraylistSearchView.add(modAlerts);
            }

            RecyclerView recycFitler = layout.findViewById(R.id.recycFitler);
            adRecycPopUp = new AdRecycPopUp(getContext(), arraylistSearchView, new onClickInterface() {
                @Override
                public void setClick(int position, boolean canUse, View view) {
                    try {
                        TextView txttitle = ((LinearLayout) view).findViewById(R.id.txtTitle);
                        TextView txtId = ((LinearLayout) view).findViewById(R.id.txtId);
                        String title = txttitle.getText().toString();
                        String id = txtId.getText().toString();
                        if (tag.equals("city")) {
                            txtFrRes_city.setText(title + "");
                            cityId = id;
                            citySt = title;
                            if (id.equals("0")) {
                                if (dataHospital.size() != 0) {
                                    dataHospital.clear();
                                    dataHospitalID.clear();
                                }

                                dataHospital = new ArrayList(dataHospital2);
                                dataHospitalID = new ArrayList(dataHospitalID2);

                            } else {
                                // TODO: Sent new id to link1 and change HOSPITAL NAMES --------------------------------------------------------

                                JSONObject object = new JSONObject();
                                try {
                                    //    object.put("id", id);
                                    object.put("hsp_id", "");
                                    object.put("spc_id", "0");
                                    object.put("dr_id", "0");
                                    object.put("prg_date", "0");
                                    object.put("city_id", cityId + "");
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }

                                alertDialogLoding.show();

                                new setConnectionVolley(getContext(), "http://nobat.mazums.ac.ir/TurnAppApi/Search?city_id=" + cityId, object
                                ).connectStringRequest(new setConnectionVolley.OnResponse() {
                                    @Override
                                    public void OnResponse(String response) {
                                        setDropDownsData(response, "updateHospital");
                                        dataDoctor = new ArrayList(dataDoctor2);
                                        dataDoctorID = new ArrayList(dataDoctorID2);
                                        txtFrRes_doctor.setText(dataDoctor.get(0) + "");
                                        doctorId = dataDoctorID.get(0) + "";
                                    }
                                });
                            }
                            //  String result = "{\"status\":\"yes\",\"message\":\"\",\"data\":{ \"hospital\":[{\"id\":\"0\",\"title\":\"تمامی بیمارستان ها\"},{\"id\":\"4\",\"title\":\"بیمارستان شهید حسینی\"},{\"id\":\"4\",\"title\":\"بیمارستان شهید حسینی\"},{\"id\":\"4\",\"title\":\"بیمارستان شهید حسینی\"},{\"id\":\"4\",\"title\":\"بیمارستان شهید حسینی\"},{\"id\":\"1\",\"title\":\"بیمارستان عمومی ماساچوست\"}],\"doctor\":[],\"specialty\":[],\"time\":[],\"city\":[]}}";
                            //  setDropDownsData(result, "update");
                        } else if (tag.equals("takhasos")) {
                            txtFrRes_takhasos.setText(title + "");
                            takhasosId = id;
                            takhasosSt = title;
                            if (id.equals("0")) {
                                if (dataTakhasos.size() != 0) {
                                    dataTakhasos.clear();
                                    dataTakhasosID.clear();
                                }
                                dataTakhasos = new ArrayList(dataTakhasos2);
                                dataTakhasosID = new ArrayList(dataTakhasosID2);
                                dataDoctorID = new ArrayList(dataDoctorID2);
                                dataDoctor = new ArrayList(dataDoctor2);

                            } else {
                                // TODO: Sent new id to link1 and change TAKASOS  --------------------------------------------------------

                                JSONObject object = new JSONObject();
                                try {
                                    //    object.put("id", id);
                                    object.put("hsp_id", hospiralId + "");
                                    object.put("spc_id", takhasosId + "");
                                    object.put("dr_id", "0");
                                    object.put("prg_date", "0");
                                    object.put("city_id", cityId);
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }

                                alertDialogLoding.show();
                                String vUrlTakhasos = "http://nobat.mazums.ac.ir/TurnAppApi/Search?hsp_id=" + hospiralId + "&spc_id=" + takhasosId;
                                new setConnectionVolley(getContext(), vUrlTakhasos, object  // inja chi bayad gozasht?  takhasos va hospital
                                ).connectStringRequest(new setConnectionVolley.OnResponse() {
                                    @Override
                                    public void OnResponse(String response) {
                                        setDropDownsData(response, "updateDoctor");
                                    }
                                });
                            }

                        } else if (tag.equals("darmonghah")) {
                            txtFrRes_darmonghah.setText(title + "");
                            hospiralId = id;  // the last hsp_id save in here
                            hospiralSt = title;
                            if (id.equals("0")) {
                                if (dataHospitalID2.size() != 0) {
                                    dataHospital2.clear();
                                    dataHospitalID2.clear();
                                }
                                dataHospital = new ArrayList(dataHospital2);
                                dataHospitalID = new ArrayList(dataHospitalID2);

                                dataDoctorID = new ArrayList(dataDoctorID2);
                                dataDoctor = new ArrayList(dataDoctor2);
                            } else {
                                // TODO: Sent new id to link1 and change TAKASOS  --------------------------------------------------------

                                JSONObject object = new JSONObject();
                                try {
                                    //    object.put("id", id);
                                    object.put("hsp_id", hospiralId + "");
                                    object.put("spc_id", takhasosId + "");
                                    object.put("dr_id", "0");
                                    object.put("prg_date", "0");
                                    object.put("city_id", "0"); // ؟
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }

                                alertDialogLoding.show();

                                new setConnectionVolley(getContext(), "http://nobat.mazums.ac.ir/TurnAppApi/Search?hsp_id=" + hospiralId + "&spc_id=" + takhasosId, object  // inja chi bayad gozasht?  takhasos va hospital
                                ).connectStringRequest(new setConnectionVolley.OnResponse() {
                                    @Override
                                    public void OnResponse(String response) {
                                        setDropDownsData(response, "updateDoctor");
                                    }
                                });
                            }
                        } else if (tag.equals("time")) {
                            txtFrRes_time.setText(title + "");
                            timeId = id;
                            timeSt = title;
                        } else if (tag.equals("doctor")) {
                            txtFrRes_doctor.setText(title + "");
                            doctorId = id;
                            doctorSt = title;
                        }

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    alertDialogFilter.dismiss();
                }
            });
            recycFitler.setAdapter(adRecycPopUp);

            editsearchSearchView = layout.findViewById(R.id.searchFr);
            editsearchSearchView.setOnQueryTextListener(this);

            builder.setView(layout);
            alertDialogFilter = builder.create();
            alertDialogFilter.show();
            alertDialogFilter.getWindow().setBackgroundDrawableResource(android.R.color.transparent);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //  SearchView -------------------------------------------------------------
    private SearchView editsearchSearchView;
    private AdRecycPopUp adRecycPopUp;
    private ArrayList<ModAlerts> arraylistSearchView = new ArrayList<ModAlerts>();

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        String text = newText;
        adRecycPopUp.filter(text);
        return true;
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

  private   setDataToFragment msetDataToFragment;
    private void onAttachToParentFragment(Fragment fragment) {
        try {
            msetDataToFragment = (setDataToFragment) fragment;
        } catch (ClassCastException e) {
            throw new ClassCastException(
                    fragment.toString() + " must implement OnPlayerSelectionSetListener");
        }
    }


}