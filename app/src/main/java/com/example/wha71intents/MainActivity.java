package com.example.wha71intents;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.icu.util.LocaleData;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.text.method.DateTimeKeyListener;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.time.LocalTime;
import java.util.Calendar;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnSynchronize = findViewById(R.id.btnSynchronize);
        btnSynchronize.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_SYNC);
                LocalTime ltNow = LocalTime.now();
                LocalTime lt6 = LocalTime.of(6, 00, 00, 00);
                LocalTime lt14 = LocalTime.of(14, 00, 00, 00);
                LocalTime lt15 = LocalTime.of(15, 00, 00, 00);

                Long ltNowToNano = ltNow.toNanoOfDay();
                Long lt6ToNano = lt6.toNanoOfDay();
                Long lt14ToNano = lt14.toNanoOfDay();
                Long lt15ToNano = lt15.toNanoOfDay();

                if (ltNowToNano > lt6ToNano && ltNowToNano <= lt14ToNano) {
                    intent.setData(Uri.parse("http://morning"));
                }
                if  (ltNowToNano > lt14ToNano && ltNowToNano <= lt15ToNano) {
                    intent.setData(Uri.parse("http://afternoon"));
                }
                if (ltNowToNano <= lt6ToNano || ltNowToNano > lt15ToNano ) {
                    intent.setData(Uri.parse("http://evening"));
                }

                startActivity(intent);
            }
        });
    }
}
