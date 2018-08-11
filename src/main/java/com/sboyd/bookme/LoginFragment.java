package com.sboyd.bookme;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;


/**
 * A simple {@link Fragment} subclass.
 */
public class LoginFragment extends Fragment implements View.OnClickListener{

    private Button login;
    private EditText email;
    private EditText password;
    private FirebaseAuth mAuth;

    public LoginFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_login, container, false);
        email = (EditText)v.findViewById(R.id.email);
        password = (EditText)v.findViewById(R.id.password2);
        login = (Button)v.findViewById(R.id.login2);

        mAuth = FirebaseAuth.getInstance();
        login.setOnClickListener(this);

        return v;
    }

    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.login2)
        {
            String emailTxt = email.getText().toString();
            String passwordTxt = password.getText().toString();

            if(emailTxt.isEmpty() || passwordTxt.isEmpty())
            {
                Toast t = Toast.makeText(getContext(),"Required fields are empty!",Toast.LENGTH_LONG);
                t.show();
            }
            else
            {
                mAuth.signInWithEmailAndPassword(emailTxt,passwordTxt).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful())
                        {
                            Toast t = Toast.makeText(getContext(),"Success!",Toast.LENGTH_LONG);
                            t.show();
                        }
                        else
                        {
                            Toast t = Toast.makeText(getContext(),"Failure!",Toast.LENGTH_LONG);
                            t.show();
                        }
                    }
                });
            }
        }
    }
}
