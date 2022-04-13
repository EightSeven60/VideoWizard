package com.example.vproject.ui;

import android.app.Activity;
import android.os.Bundle;

import androidx.appcompat.widget.SwitchCompat;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

import com.example.vproject.R;
import com.example.vproject.networking.SendToServerAsyncTask;
import com.example.vproject.user.CurrentUser;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ProfileFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ProfileFragment extends Fragment {

    private Activity activity;
    private EditText eTextName;
    private EditText eTextEmail;
    private Switch paymentSwitch;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ProfileFragment() {
        // Required empty public constructor
    }

    public ProfileFragment(Activity activity) {
        this.activity = activity;
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ProfileFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ProfileFragment newInstance(String param1, String param2) {
        ProfileFragment fragment = new ProfileFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
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
        return inflater.inflate(R.layout.fragment_profile, container, false);
    }

    public Void setup() {
        EditText eTextEmail = activity.findViewById(R.id.tEditEmailP);
        EditText eTextName = activity.findViewById(R.id.tEditNameP);
        SwitchCompat paymentSwitch = activity.findViewById(R.id.switchPaymentP);

        eTextEmail.setText(CurrentUser.Email);
        eTextName.setText(CurrentUser.Name);
        paymentSwitch.setChecked(CurrentUser.Payment);

        activity.findViewById(R.id.applyProfileChangesP).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = eTextEmail.getText().toString();
                String name = eTextName.getText().toString();
                String payment = paymentSwitch.isChecked() ? "1" : "0";

                Toast.makeText(activity, "Contacting server", Toast.LENGTH_LONG).show();

                SendToServerAsyncTask sendToServerAsyncTask = new SendToServerAsyncTask();
                sendToServerAsyncTask.execute("Edit" + CurrentUser.separator + CurrentUser.Email +
                                CurrentUser.separator + email + CurrentUser.separator + name +
                                CurrentUser.separator + CurrentUser.PasswordHash + CurrentUser.separator + payment,
                        "192.168.100.3",
                        "8000");
            }
        });
        return null;
    }
}