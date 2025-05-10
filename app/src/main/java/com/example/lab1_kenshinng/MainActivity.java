package com.example.lab1_kenshinng;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.graphics.Color;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Date;

public class MainActivity extends AppCompatActivity {
    Button btn = null;
    TextView txt = null;
    EditText text_area = null;
    ImageView image;
    CalendarView calendar = null;
    CheckBox check = null;
    ImageButton img_btn = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState); //
        EdgeToEdge.enable(this);

        setContentView(R.layout.activity_main); //

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Asignación de vistas
        txt=findViewById(R.id.textView);
        txt.setTextColor(Color.parseColor("#4A306D"));

        btn = findViewById(R.id.btn_impresion);
        btn.setBackgroundColor(Color.parseColor("#0E273C"));
        btn.setTextColor(Color.parseColor("#D3BCCC"));

        text_area = findViewById(R.id.txtCampo);
        text_area.setTextColor(Color.parseColor("#4A306D"));

        image = findViewById(R.id.imageView);
        image.setImageResource(R.drawable.profile_2);

        calendar = findViewById(R.id.calendarView);

        check = findViewById(R.id.id_checkbox);
        check.setTextColor(Color.parseColor("#4A306D"));

        img_btn = findViewById(R.id.imageButton);

        calendar.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth) {
                // Convert selected date to milliseconds
                java.util.Calendar selectedCalendar = java.util.Calendar.getInstance();
                selectedCalendar.set(year, month, dayOfMonth);
                long selectedDateMillis = selectedCalendar.getTimeInMillis();

                // Format the selected date
                String fechaSeleccionada = java.text.DateFormat.getDateInstance().format(new Date(selectedDateMillis));

                // Update the date variable to use later in Toast
                calendar.setDate(selectedDateMillis);
            }
        });
        // Acción del primer botón
        if (btn != null) {
            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // conseguir checkbox
                    boolean isChecked = check.isChecked();

                    // conseguir texto
                    String texto = text_area.getText().toString();

                    // conseguir fecha
                    long selectedDateMillis = calendar.getDate();
                    String fechaSeleccionada = java.text.DateFormat.getDateInstance().format(new Date(selectedDateMillis));

                    // conseguir hora
                    java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("HH:mm:ss");
                    String tiempoActual = sdf.format(new Date());

                    // Create a message with all values
                    String toastMessage = texto +
                            ", " + isChecked +
                            ", " + fechaSeleccionada +
                            ", " + tiempoActual;

                    //  mostrar toast
                    Toast.makeText(getApplicationContext(), toastMessage, Toast.LENGTH_LONG).show();
                }
            });
        }

        //accion del segundo boton
        if(img_btn !=null){
            img_btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //enlace de esta aplicacion a otras aplicaiones
                    Intent ptn = new Intent(getApplicationContext(), MainActivity2.class);
                    startActivity(ptn);
                         }
            });
        }

    }
}
