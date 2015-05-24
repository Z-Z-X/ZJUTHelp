package com.zjut.zjuthelp.Fragments;

import android.app.Activity;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.gc.materialdesign.views.ButtonFlat;
import com.zjut.zjuthelp.R;
import com.zjut.zjuthelp.Web.WebTools;
import com.zjut.zjuthelp.Web.ZJUTTeachingAffairs;


import org.json.JSONArray;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link RoomFreeQueryFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link RoomFreeQueryFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class RoomFreeQueryFragment extends Fragment {
    // Spinners
    private Spinner sprTerm;
    private Spinner sprWeek;
    private Spinner sprStart;
    private Spinner sprEnd;
    private Spinner sprDay;
    private Spinner sprCampus;
    // TextView
    private TextView txtResult;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment RoomFreeQueryFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static RoomFreeQueryFragment newInstance(String param1, String param2) {
        RoomFreeQueryFragment fragment = new RoomFreeQueryFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public RoomFreeQueryFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_room_free_query, container, false);

        // Spinner select term
        sprTerm = (Spinner) rootView.findViewById(R.id.spinner_term);
        ArrayAdapter adpTerm = ArrayAdapter.createFromResource(getActivity(), R.array.terms, android.R.layout.simple_spinner_item);
        adpTerm.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sprTerm.setAdapter(adpTerm);
        // Spinner select week
        String[] weeks = new String[16];
        for (int i = 0 ; i < weeks.length; ++i) {
            weeks[i] = String.valueOf(i+1);
        }
        sprWeek = (Spinner) rootView.findViewById(R.id.spinner_week);
        ArrayAdapter<String> adpWeek = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, weeks);
        adpWeek.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sprWeek.setAdapter(adpWeek);
        // Spinner select time
        String[] times = new String[12];
        for (int i = 0 ; i < times.length; ++i) {
            times[i] = String.valueOf(i+1);
        }
        sprStart = (Spinner) rootView.findViewById(R.id.spinner_start_time);
        sprEnd = (Spinner) rootView.findViewById(R.id.spinner_end_time);
        ArrayAdapter<String> adpTimes = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, times);
        adpTimes.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sprStart.setAdapter(adpTimes);
        sprEnd.setAdapter(adpTimes);
        // Spinner select weekday
        sprDay = (Spinner) rootView.findViewById(R.id.spinner_weekday);
        ArrayAdapter adpDay = ArrayAdapter.createFromResource(getActivity(), R.array.weekday, android.R.layout.simple_spinner_item);
        adpDay.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sprDay.setAdapter(adpDay);
        // Spinner select campus
        sprCampus = (Spinner) rootView.findViewById(R.id.spinner_campus);
        ArrayAdapter adpCampus = ArrayAdapter.createFromResource(getActivity(), R.array.campus, android.R.layout.simple_spinner_item);
        adpCampus.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sprCampus.setAdapter(adpCampus);

        // Result text
        txtResult = (TextView) rootView.findViewById(R.id.txt_query_result);

        // Query button
        ButtonFlat btnQuery = (ButtonFlat) rootView.findViewById(R.id.btn_query);
        btnQuery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Query().execute();
            }
        });
        return rootView;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (OnFragmentInteractionListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        public void onFragmentInteraction(Uri uri);
    }

    class Query extends AsyncTask<Void, Integer, Integer> {
        private String json;
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            txtResult.setText("正在查询...");
        }

        @Override
        protected Integer doInBackground(Void... params) {
            try {
                // Edit params
                Map<String, String> httpParams = new HashMap<String, String>();
                httpParams.put("xueqi", sprTerm.getSelectedItem().toString());
                httpParams.put("qsz", sprWeek.getSelectedItem().toString());
                httpParams.put("jsz", sprWeek.getSelectedItem().toString());
                httpParams.put("qsj", sprStart.getSelectedItem().toString());
                httpParams.put("jsj", sprEnd.getSelectedItem().toString());
                httpParams.put("xingqi", sprDay.getSelectedItem().toString());
                httpParams.put("xiaoqu", sprCampus.getSelectedItem().toString());
                byte[] data = WebTools.getRequestData(httpParams, "utf-8").toString().getBytes();
                // Post request
                URL url = new URL(ZJUTTeachingAffairs.ROOM_FREE_QUERY_URL);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setDoInput(true);
                connection.setDoOutput(true);
                connection.setRequestMethod("POST");
                connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
                connection.setRequestProperty("Content-Length", String.valueOf(data.length));
                OutputStream outputStream = connection.getOutputStream();
                outputStream.write(data);
                // Get Response
                int response = connection.getResponseCode();
                if (response == HttpURLConnection.HTTP_OK) {
                    InputStream inputStream = connection.getInputStream();
                    json = WebTools.dealResponseResult(inputStream);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Integer integer) {
            super.onPostExecute(integer);
            String text = "";
            try {
                JSONArray ja = new JSONArray(json);
                for (int i = 0 ; i < ja.length() ; ++i) {
                    JSONArray array = ja.getJSONArray(i);
                    text += array.get(2).toString() + "\n";
            }
            } catch (Exception e) {
                e.printStackTrace();
            }
            txtResult.setText(text);
        }
    }
}
