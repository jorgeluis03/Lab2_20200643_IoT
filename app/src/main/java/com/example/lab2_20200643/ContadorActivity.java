package com.example.lab2_20200643;

import androidx.appcompat.app.AppCompatActivity;
import androidx.work.Data;
import androidx.work.OneTimeWorkRequest;
import androidx.work.WorkInfo;
import androidx.work.WorkManager;
import androidx.work.WorkRequest;


import android.os.Bundle;

import android.util.Log;
import android.widget.Toast;

import com.example.lab2_20200643.databinding.ActivityContadorBinding;


import java.util.UUID;

public class ContadorActivity extends AppCompatActivity {
    ActivityContadorBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= ActivityContadorBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        Toast.makeText(this, "Se encuentra en el Contador", Toast.LENGTH_SHORT).show();
        UUID uuid = UUID.randomUUID();
        //############## TRABAJO PERSISTENTE #############3
        binding.buttonContadorIniciar.setOnClickListener(view -> {

            //envio de parametros
            Data dataBuilder = new Data.Builder()
                    .putInt("numero", 104) // Establece el valor 104 directamente
                    .build();

            WorkRequest workRequest = new OneTimeWorkRequest.Builder(ContadorWorker.class)
                    .setId(uuid)
                    .setInputData(dataBuilder)
                    .build();
            // fin envio de parametros



            WorkManager
                    .getInstance(binding.getRoot().getContext())
                    .enqueue(workRequest);

            WorkManager.getInstance(binding.getRoot().getContext())
                    .getWorkInfoByIdLiveData(uuid)
                    .observe(ContadorActivity.this, workInfo -> {
                        if(workInfo != null){
                            if (workInfo.getState() == WorkInfo.State.RUNNING) {
                                Data progress = workInfo.getProgress();
                                int contador = progress.getInt("contador", 0);
                                Log.d("msg-test", "progress: " + contador);
                                binding.textView2.setText(String.valueOf(contador));

                            } else if (workInfo.getState() == WorkInfo.State.SUCCEEDED) {
                                Data outputData = workInfo.getOutputData();
                                String texto = outputData.getString("info");
                                Log.d("msg-test", texto);

                            }
                        }else{
                            Log.d("msg-test", "work info == null ");
                        }
                    });


        });


    }

}