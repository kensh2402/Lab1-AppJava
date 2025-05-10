package com.example.lab1_kenshinng;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity2 extends AppCompatActivity {

    Button btn2= null;
    Button closeButton, whatsappButton;
    EditText numberInput;

    // Variables para validar longitud
    int limiteCodeLocal = 3;
    int limitePhone = 8;
    String defaultLocal = "507";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main2);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.id_btn), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Log.d("State", "onCreate");
        btn2 = findViewById(R.id.id_btn2);
        whatsappButton = findViewById(R.id.whatsappButton);
        numberInput = findViewById(R.id.numberInput);

        if(btn2 != null){
            btn2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    finish();
                }
            });
        }
        whatsappButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String input = numberInput.getText().toString().trim();

                if (input.length() == (limiteCodeLocal + limitePhone)) {
                    Toast.makeText(getApplicationContext(), "Número ingresado: " + input, Toast.LENGTH_SHORT).show();
                    sendMessageWhatsapp(input);
                } else {
                    Toast.makeText(getApplicationContext(),
                            "Por favor ingrese un número válido de " + (limiteCodeLocal + limitePhone) + " dígitos",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
    private void sendMessageWhatsapp(String phone) {
        String numeroPhone = phone.substring(phone.lastIndexOf(defaultLocal));
        System.out.println("numeroPhone = " + numeroPhone);

        Handler handler = new Handler();
        handler.post(new Runnable() {
            public void run() {
                String url = "https://api.whatsapp.com/send?phone=" + phone;
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);
                finish();
            }
        });
    }
}
