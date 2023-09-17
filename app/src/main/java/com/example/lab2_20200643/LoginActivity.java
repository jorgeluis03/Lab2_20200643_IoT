package com.example.lab2_20200643;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.lab2_20200643.databinding.ActivityLoginBinding;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LoginActivity extends AppCompatActivity {
    ActivityLoginBinding binding;
    ApiService apiService;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Toast.makeText(this, "Se enceuntra en el Login", Toast.LENGTH_SHORT).show();

        apiService = new Retrofit.Builder()
                .baseUrl("https://randomuser.me")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(ApiService.class);
        obtnerRespuestaWebService();

        binding.buttonRegistrarseEntrar.setOnClickListener(view -> {
            if(binding.checkBox.isChecked()==true){
                String nombre = binding.textFieldNombre.getEditText().getText().toString(); //Se obtiene el nombre del textFiel
                String apelldio = binding.textFieldApelldio.getEditText().getText().toString();
                String urlFoto = binding.urlFoto.getText().toString();

                Intent intent = new Intent(LoginActivity.this, AppInterfaceActivity.class);
                intent.putExtra("nombre",nombre);
                intent.putExtra("apellido",apelldio);
                intent.putExtra("foto",urlFoto);
                startActivity(intent);
            }else {
                Toast.makeText(getApplicationContext(), "Debe aceptar términos y condiciones", Toast.LENGTH_SHORT).show();
            }

        });

    }

    public void obtnerRespuestaWebService(){
        apiService.getUserData().enqueue(new Callback<UserData>() {
            @Override
            public void onResponse(Call<UserData> call, Response<UserData> response) {
                // Verificar si la respuesta es exitosa
                if (response.isSuccessful()) {

                    UserData userData = response.body();
                    if (userData != null && !userData.getResults().isEmpty()) {
                        UserProfile userProfile = userData.getResults().get(0);

                        TextInputEditText nombre = binding.textFieldNombre.findViewById(R.id.textInputEditTextNombre);
                        TextInputEditText apellido = binding.textFieldApelldio.findViewById(R.id.textInputEditTextApellido);
                        TextInputEditText correo = binding.textFieldCorreo.findViewById(R.id.textInputEditTextCorreo);
                        TextInputEditText password = binding.textFieldPassword.findViewById(R.id.textInputEditTextPassword);

                        binding.urlFoto.setText(userProfile.getPicture().getFoto());
                        nombre.setText(userProfile.getName().getNombre());
                        apellido.setText(userProfile.getName().getApellido());
                        correo.setText(userProfile.getEmail());
                        password.setText(userProfile.getLogin().getPassword());

                        /*Log.d("tag_smg", "Nombre: " + userProfile.getName().getNombre());
                        Log.d("tag_smg", "Apellido: " + userProfile.getName().getApellido());
                        Log.d("tag_smg", "Correo: " + userProfile.getEmail());
                        Log.d("tag_smg", "Password: " + userProfile.getLogin().getPassword());*/
                    }

                }
            }

            @Override
            public void onFailure(Call<UserData> call, Throwable t) {
                Log.d("msg_error","ocurrio un error "+t);
            }
        });
    }



}