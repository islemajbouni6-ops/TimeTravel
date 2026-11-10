
package com.example.timetravel1;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

public class ResultActivity extends AppCompatActivity {

    TextView txtResultat;
    Button btnNouveau;
    LinearLayout layoutResultat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        txtResultat = findViewById(R.id.txt_resultat_ia);
        btnNouveau = findViewById(R.id.btn_nouveau_ia);
        layoutResultat = findViewById(R.id.layout_resultat_ia);

        Intent intent = getIntent();

        boolean verdict = intent.getBooleanExtra(
                IntentKeys.key(IntentKeys.MATRICULE, "VERDICT"),
                false
        );

        String nom = intent.getStringExtra(
                IntentKeys.key(IntentKeys.MATRICULE, "NOM_VOYAGEUR")
        );

        if (verdict) {
            layoutResultat.setBackgroundColor(Color.GREEN);
            txtResultat.setText("Bon voyage " + nom + " !");
        } else {
            layoutResultat.setBackgroundColor(Color.RED);
            txtResultat.setText(
                    nom + " est en état d'arrestation temporelle !"
            );
        }

        btnNouveau.setOnClickListener(v -> {
            Intent retour = new Intent(
                    ResultActivity.this,
                    MainActivity.class
            );

            retour.setFlags(
                    Intent.FLAG_ACTIVITY_CLEAR_TOP |
                            Intent.FLAG_ACTIVITY_SINGLE_TOP
            );

            startActivity(retour);
            finish();
        });
    }
}
