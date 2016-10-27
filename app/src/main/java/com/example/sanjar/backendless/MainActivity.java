package com.example.sanjar.backendless;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;

import com.backendless.Backendless;
import com.backendless.BackendlessUser;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.async.callback.BackendlessCallback;
import com.backendless.exceptions.BackendlessFault;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText email;
    private EditText password;
    private Button signIn;
    private Button logIn;

    final BackendlessUser user = new BackendlessUser();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final String appVersion = "v1";
        Backendless.initApp(this, "029697B0-C8A8-6930-FF62-AFF3ADDBFB00", "FEB230C8-755C-75DC-FFE1-9F4A33ACB200", appVersion);


        initVars();

        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final BackendlessUser user = new BackendlessUser();
                user.setEmail(email.getText().toString());
                user.setPassword(password.getText().toString());

                Backendless.UserService.register(user, new BackendlessCallback<BackendlessUser>() {
                    @Override
                    public void handleResponse(BackendlessUser backendlessUser) {
                        Toast toast = Toast.makeText(getApplicationContext(), "Registration " + backendlessUser.getEmail() + " successfully registered", Toast.LENGTH_LONG);
                        toast.show();


                        Intent intent = new Intent(MainActivity.this, Main2Activity.class);
                        intent.putExtra("email", user.getEmail());
                        startActivity(intent);

                    }
                });

            }
        });

        logIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String mail = email.getText().toString();
                String pass = password.getText().toString();

                Backendless.UserService.login(mail, pass, new AsyncCallback<BackendlessUser>()
                {
                    public void handleResponse( BackendlessUser user )
                    {
                        Toast toast = Toast.makeText(getApplicationContext(), "Логин введён успешно", Toast.LENGTH_LONG);
                        toast.show();

                        Intent intent = new Intent(MainActivity.this, Main2Activity.class);
                        intent.putExtra("email", user.getEmail());
                        startActivity(intent);
                    }

                    public void handleFault( BackendlessFault fault )
                    {
                        fault.getCode();
                    }
                });
            }
        });


    }

    private void initVars() {
        email = (EditText) findViewById(R.id.editText);
        password = (EditText) findViewById(R.id.editText2);
        signIn = (Button) findViewById(R.id.button);
        logIn = (Button) findViewById(R.id.logIn);

    }
}