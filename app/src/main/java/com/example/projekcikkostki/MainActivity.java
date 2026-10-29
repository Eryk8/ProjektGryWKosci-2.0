package com.example.projekcikkostki;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private EditText editTextLiczbaKostek;
    private Button buttonLosuj, buttonRosnaco, buttonMalejaco;
    private TextView textViewWyniki;
    private GridLayout gridKostki;

    private ArrayList<Integer> wyniki = new ArrayList<>();
    private boolean czyWylosowano = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextLiczbaKostek = findViewById(R.id.editTextLiczbaKostek);
        buttonLosuj = findViewById(R.id.buttonLosuj);
        buttonRosnaco = findViewById(R.id.buttonRosnaco);
        buttonMalejaco = findViewById(R.id.buttonMalejaco);
        textViewWyniki = findViewById(R.id.textViewWyniki);
        gridKostki = findViewById(R.id.gridKostki);

        buttonLosuj.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tekst = editTextLiczbaKostek.getText().toString();

                if (tekst.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Wpisz liczbę kostek!", Toast.LENGTH_SHORT).show();
                    return;
                }

                int liczba = Integer.parseInt(tekst);

                if (liczba <= 0 || liczba > 100) {
                    Toast.makeText(MainActivity.this, "Liczba kostek musi być od 1 do 100!", Toast.LENGTH_LONG).show();
                    return;
                }

                wyniki.clear();
                Random rnd = new Random();
                for (int i = 0; i < liczba; i++) {
                    wyniki.add(rnd.nextInt(6) + 1);
                }

                czyWylosowano = true;
                wyswietlWyniki();
            }
        });

        buttonRosnaco.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!czyWylosowano) {
                    Toast.makeText(MainActivity.this, "Najpierw należy wylosować kostki!", Toast.LENGTH_SHORT).show();
                    return;
                }

                for (int i = 0; i < wyniki.size() - 1; i++) {
                    for (int j = 0; j < wyniki.size() - i - 1; j++) {
                        if (wyniki.get(j) > wyniki.get(j + 1)) {
                            int temp = wyniki.get(j);
                            wyniki.set(j, wyniki.get(j + 1));
                            wyniki.set(j + 1, temp);
                        }
                    }
                }
                wyswietlWyniki();
            }
        });

        buttonMalejaco.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!czyWylosowano) {
                    Toast.makeText(MainActivity.this, "Najpierw należy wylosować kostki!", Toast.LENGTH_SHORT).show();
                    return;
                }

                for (int i = 0; i < wyniki.size() - 1; i++) {
                    for (int j = 0; j < wyniki.size() - i - 1; j++) {
                        if (wyniki.get(j) < wyniki.get(j + 1)) {
                            int temp = wyniki.get(j);
                            wyniki.set(j, wyniki.get(j + 1));
                            wyniki.set(j + 1, temp);
                        }
                    }
                }
                wyswietlWyniki();
            }
        });
    }

    private void wyswietlWyniki() {
        gridKostki.removeAllViews();

        for (int val : wyniki) {
            ImageView img = new ImageView(this);
            img.setLayoutParams(new ViewGroup.LayoutParams(90, 90));
            img.setPadding(4, 4, 4, 4);

            switch (val) {
                case 1:
                    img.setImageResource(R.drawable.kostka1);
                    break;
                case 2:
                    img.setImageResource(R.drawable.kostka2);
                    break;
                case 3:
                    img.setImageResource(R.drawable.kostka3);
                    break;
                case 4:
                    img.setImageResource(R.drawable.kostka4);
                    break;
                case 5:
                    img.setImageResource(R.drawable.kostka5);
                    break;
                case 6:
                    img.setImageResource(R.drawable.kostka6);
                    break;
            }

            gridKostki.addView(img);
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < wyniki.size(); i++) {
            sb.append(wyniki.get(i));
            if (i < wyniki.size() - 1) {
                sb.append(", ");
            }
        }
        textViewWyniki.setText(sb.toString());
    }
}