package com.example.lab2_20200643;


import android.content.Context;

import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.work.Data;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

public class ContadorWorker extends Worker {
    public ContadorWorker(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
    }



    @NonNull
    @Override
    public Result doWork() {

        int contador = getInputData().getInt("numero",0);
        Log.d("numero",String.valueOf(contador).toString());
        int contadorFinal = 110;
        while (contador<=contadorFinal){
            Log.d("sms","contador: "+contador);
            //SetProgre puede enviar actualizaciones parciales mientras el trabajo no finaliza.
            setProgressAsync(new Data.Builder().putInt("contador",contador).build());
            contador++;
            try {
                Thread.sleep(1000);
            }catch (InterruptedException e){
                e.printStackTrace();
                return Result.failure();
            }
        }

        Data data = new Data.Builder()
                .putString("info","Worker finalizado")
                .build();

        return Result.success(data);

    }


}
