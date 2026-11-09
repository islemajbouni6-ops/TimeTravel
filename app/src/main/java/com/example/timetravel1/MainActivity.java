package com.example.timetravel1;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;


public class MainActivity extends AppCompatActivity {

    private static final int ANNEE_MIN = 1;
    private static final int ANNEE_MAX = 9999;

    private EditText nom, naissance, destination;
    private Spinner motif;
    private Button btnInspecter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nom = findViewById(R.id.et_nom_ia);
        naissance = findViewById(R.id.et_naissance_ia);
        destination = findViewById(R.id.et_destination_ia);
        motif = findViewById(R.id.sp_motif_ia);
        btnInspecter = findViewById(R.id.btn_inspecter_ia);

        btnInspecter.setOnClickListener(v -> inspecter());
    }

    private void inspecter() {
        String nomVoyageur = nom.getText().toString().trim();
        String anneeN = naissance.getText().toString().trim();
        String anneeD = destination.getText().toString().trim();

        if (nomVoyageur.isEmpty() || anneeN.isEmpty() || anneeD.isEmpty()) {
            afficher("Veuillez remplir tous les champs");
            return;
        }

        if (motif.getSelectedItem() == null) {
            afficher("Veuillez choisir un motif");
            return;
        }
        String motifVoyage = motif.getSelectedItem().toString();

        int anneeNaissance;
        int anneeDestination;
        try {
            anneeNaissance = Integer.parseInt(anneeN);
            anneeDestination = Integer.parseInt(anneeD);
        } catch (NumberFormatException e) {
            afficher("Veuillez saisir des années valides");
            return;
        }

        if (anneeNaissance < ANNEE_MIN || anneeNaissance > ANNEE_MAX
                || anneeDestination < ANNEE_MIN || anneeDestination > ANNEE_MAX) {
            afficher("Les années doivent être comprises entre " + ANNEE_MIN + " et " + ANNEE_MAX);
            return;
        }

        if (anneeDestination < anneeNaissance) {
            afficher("Année de destination invalide");
            return;
        }

        Intent intent = new Intent(MainActivity.this, VerificationActivity.class);
        intent.putExtra(IntentKeys.key(IntentKeys.MATRICULE, "NOM_VOYAGEUR"), nomVoyageur);
        intent.putExtra(IntentKeys.key(IntentKeys.MATRICULE, "ANNEE_NAISSANCE"), anneeNaissance);
        intent.putExtra(IntentKeys.key(IntentKeys.MATRICULE, "ANNEE_DESTINATION"), anneeDestination);
        intent.putExtra(IntentKeys.key(IntentKeys.MATRICULE, "MOTIF"), motifVoyage);
        startActivity(intent);
    }

    private void afficher(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}