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
import com.google.firebase.database.FirebaseDatabase;


/**
 * A simple {@link Fragment} subclass.
 */
public class SignupFragment extends Fragment implements View.OnClickListener{

    private EditText email;
    private EditText username;
    private EditText password;
    private EditText phone;
    private Button signup;
    private FirebaseAuth mAuth;
    private FirebaseDatabase mDb;

    public SignupFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_signup, container, false);
        email = (EditText)v.findViewById(R.id.email2);
        username = (EditText)v.findViewById(R.id.username);
        password = (EditText)v.findViewById(R.id.password2);
        phone = (EditText)v.findViewById(R.id.phone);
        signup = (Button)v.findViewById(R.id.signup2);

        mAuth = FirebaseAuth.getInstance();
        mDb = FirebaseDatabase.getInstance();

        signup.setOnClickListener(this);
        return v;
    }

    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.signup2)
        {
            final String emailTxt = email.getText().toString();
            final String usernameTxt = username.getText().toString();
            final String passwordTxt = password.getText().toString();
            final String phoneTxt = phone.getText().toString();

            if(emailTxt.isEmpty() || usernameTxt.isEmpty() || passwordTxt.isEmpty() || phoneTxt.isEmpty())
            {
                Toast t = Toast.makeText(getContext(),"Required fields are empty!",Toast.LENGTH_LONG);
                t.show();
            }
            else
            {
                mAuth.createUserWithEmailAndPassword(emailTxt,passwordTxt).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful())
                        {
                            Toast t = Toast.makeText(getContext(),"Account created!",Toast.LENGTH_LONG);
                            t.show();

                            String uid = mAuth.getUid();
                            User user = new User(emailTxt,usernameTxt,phoneTxt);
                            writeNewUser(user);
                        }
                        else
                        {
                            Toast t = Toast.makeText(getContext(),"User account already exist!",Toast.LENGTH_LONG);
                            t.show();
                        }
                    }
                });
            }
        }
    }

    private void writeNewUser(User u)
    {
        mDb.getReference().child("users").child(mAuth.getUid()).setValue(u);
    }
}
