package com.sboyd.bookme;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


/**
 * A simple {@link Fragment} subclass.
 */
public class Tab1Fragment extends Fragment implements View.OnClickListener{

    private Button login_button;
    private Button signup_button;


    public Tab1Fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_tab1, container, false);
        login_button = (Button)v.findViewById(R.id.login);
        signup_button = (Button)v.findViewById(R.id.signup);

        login_button.setOnClickListener(this);
        signup_button.setOnClickListener(this);

        return v;
    }

    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.login)
        {
            getFragmentManager().beginTransaction().replace(R.id.container,new LoginFragment()).commitNow();
        }
        else if(view.getId() == R.id.signup)
        {
            getFragmentManager().beginTransaction().replace(R.id.container,new SignupFragment()).commitNow();
        }
    }
}
