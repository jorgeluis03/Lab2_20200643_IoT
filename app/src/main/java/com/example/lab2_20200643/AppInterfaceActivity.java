package com.example.lab2_20200643;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import java.util.Calendar;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.lab2_20200643.databinding.ActivityAppInterfaceBinding;
import com.squareup.picasso.Picasso;

public class AppInterfaceActivity extends AppCompatActivity {
    private static final int ALARM_ID = 1;
    ActivityAppInterfaceBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= ActivityAppInterfaceBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        Toast.makeText(this, "Se encuentra en el Interfaz principal", Toast.LENGTH_SHORT).show();
        Intent intent = getIntent();
        String nombreUser = intent.getStringExtra("nombre");
        String apellidoUser = intent.getStringExtra("apellido");
        String urlFoto = intent.getStringExtra("foto");

        ImageView imageView = findViewById(R.id.imageViewUser);
        Picasso.get().load(urlFoto).into(imageView);
        binding.userNombreApellido.setText(nombreUser+' '+apellidoUser);



    }
    public void setAlarm(View view) {
        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(this, AlarmReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, ALARM_ID, intent, PendingIntent.FLAG_UPDATE_CURRENT);

        // Configura la hora de la alarma
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        calendar.add(Calendar.SECOND, 10); // Establece la alarma para que suene después de 10 segundos

        // Establece la alarma
        alarmManager.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pendingIntent);
    }

    public void onCardContadorClick(View view) {
        Intent intent = new Intent(AppInterfaceActivity.this, ContadorActivity.class);
        startActivity(intent);
    }

    public void onCardCronometroClick(View view) {
        Intent intent = new Intent(AppInterfaceActivity.this, CronometroActivity.class);
        startActivity(intent);
    }
}