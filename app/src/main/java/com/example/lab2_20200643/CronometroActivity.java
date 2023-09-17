package com.example.lab2_20200643;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.TextView;
import android.widget.Toast;


public class CronometroActivity extends AppCompatActivity {
    private Chronometer chronometer;
    private Button startButton, stopButton, resumeButton, resetButton;

    private long pauseOffset = 0;
    private boolean isRunning = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cronometro);
        Toast.makeText(this, "Se encuentra en el Cronometro", Toast.LENGTH_SHORT).show();
        chronometer = findViewById(R.id.chronometer);
        startButton = findViewById(R.id.start_button);
        stopButton = findViewById(R.id.stop_button);
        resumeButton = findViewById(R.id.resume_button);
        resetButton = findViewById(R.id.reset_button);

        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startChronometer();
            }
        });

        stopButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pauseChronometer();
            }
        });

        resumeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resumeChronometer();
            }
        });

        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetChronometer();
            }
        });
    }

    private void startChronometer() {
        if (!isRunning) {
            chronometer.setBase(SystemClock.elapsedRealtime() - pauseOffset);
            chronometer.start();
            isRunning = true;
            startButton.setVisibility(View.GONE);
            stopButton.setVisibility(View.VISIBLE);
            resetButton.setVisibility(View.GONE);
            resumeButton.setVisibility(View.GONE);
        }
    }

    private void pauseChronometer() {
        if (isRunning) {
            chronometer.stop();
            pauseOffset = SystemClock.elapsedRealtime() - chronometer.getBase();
            isRunning = false;
            stopButton.setVisibility(View.GONE);
            resetButton.setVisibility(View.VISIBLE);
            resumeButton.setVisibility(View.VISIBLE);
        }
    }

    private void resumeChronometer() {
        if (!isRunning) {
            chronometer.setBase(SystemClock.elapsedRealtime() - pauseOffset);
            chronometer.start();
            isRunning = true;
            stopButton.setVisibility(View.VISIBLE);
            resetButton.setVisibility(View.GONE);
            resumeButton.setVisibility(View.GONE);
        }
    }

    private void resetChronometer() {
        chronometer.setBase(SystemClock.elapsedRealtime());
        pauseOffset = 0;
        isRunning = false;
        startButton.setVisibility(View.VISIBLE);
        stopButton.setVisibility(View.GONE);
        resetButton.setVisibility(View.GONE);
        resumeButton.setVisibility(View.GONE);
    }
}