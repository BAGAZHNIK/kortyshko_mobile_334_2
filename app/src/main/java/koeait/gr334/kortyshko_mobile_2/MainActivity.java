package koeait.gr334.kortyshko_mobile_2;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    Spinner spFrom;
    Spinner spTo;
    EditText etFrom;
    TextView tvTo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        spFrom = findViewById(R.id.spinner_from);
        spTo = findViewById(R.id.spinner_to);
        etFrom = findViewById(R.id.edit_from);
        tvTo = findViewById(R.id.edit_to);

        ArrayAdapter<String> adp = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1);

        adp.add("mm");
        adp.add("cm");
        adp.add("m");
        adp.add("km");

        spFrom.setAdapter(adp);
        spTo.setAdapter(adp);
    }


    public void on_convert(View v) {
        String input = etFrom.getText().toString().trim();

        if (input.isEmpty()) {
            tvTo.setText("Ошибка: введите значение!");
            return;
        }

        try {
            double from = Double.parseDouble(input);


            if (from < 0) {
                tvTo.setText("Ошибка: введите положительное число!");
                return;
            }

            String sFrom = (String) spFrom.getSelectedItem();
            String sTo = (String) spTo.getSelectedItem();


            double inMeters = 0.0;
            switch (sFrom) {
                case "mm": inMeters = from / 1000.0; break;
                case "cm": inMeters = from / 100.0; break;
                case "m": inMeters = from; break;
                case "km": inMeters = from * 1000.0; break;
            }

            double result = 0.0;
            switch (sTo) {
                case "mm": result = inMeters * 1000.0; break;
                case "cm": result = inMeters * 100.0; break;
                case "m": result = inMeters; break;
                case "km": result = inMeters / 1000.0; break;
            }

            tvTo.setText(String.format("%.4f", result));

        } catch (NumberFormatException e) {
            tvTo.setText("Ошибка: введите корректное число!");
        }
    }
}