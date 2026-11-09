package com.example.timetravel1;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class VerificationActivity extends AppCompatActivity {

    TextView txtNom, txtAge, txtMotif;
    Button btnApprouver, btnRefuser;

    String nomVoyageur;
    String motifVoyage;
    int ageTemporel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verification);

        // Initialisation des composants
        txtNom = findViewById(R.id.txt_nom_ia);
        txtAge = findViewById(R.id.txt_age_ia);
        txtMotif = findViewById(R.id.txt_motif_ia);

        btnApprouver = findViewById(R.id.btn_approuver_ia);
        btnRefuser = findViewById(R.id.btn_refuser_ia);

        // Récupération des données reçues
        Intent intent = getIntent();

        nomVoyageur = intent.getStringExtra(
                IntentKeys.key(IntentKeys.MATRICULE, "NOM_VOYAGEUR")
        );

        int anneeNaissance = intent.getIntExtra(
                IntentKeys.key(IntentKeys.MATRICULE, "ANNEE_NAISSANCE"), 0
        );

        int anneeDestination = intent.getIntExtra(
                IntentKeys.key(IntentKeys.MATRICULE, "ANNEE_DESTINATION"), 0
        );

        motifVoyage = intent.getStringExtra(
                IntentKeys.key(IntentKeys.MATRICULE, "MOTIF")
        );

        // Calcul de la différence temporelle
        ageTemporel = Math.abs(anneeDestination - anneeNaissance);

        // Affichage des informations
        txtNom.setText("Voyageur : " + nomVoyageur);
        txtAge.setText("Âge temporel : " + ageTemporel + " ans");
        txtMotif.setText("Motif : " + motifVoyage);

        // Vérification du paradoxe temporel
        if ("Modification du passé".equals(motifVoyage)
                && estPremier(ageTemporel)) {

            btnApprouver.setEnabled(false);
            btnApprouver.setText("Saut interdit : paradoxe !");
        } else {
            btnApprouver.setEnabled(true);
        }

        // Bouton Approuver
        btnApprouver.setOnClickListener(v -> envoyerVerdict(true));

        // Bouton Refuser
        btnRefuser.setOnClickListener(v -> envoyerVerdict(false));
    }

    // Fonction de vérification du nombre premier
    private boolean estPremier(int nombre) {

        if (nombre <= 1) {
            return false;
        }

        for (int i = 2; i <= Math.sqrt(nombre); i++) {

            if (nombre % i == 0) {
                return false;
            }
        }

        return true;
    }

    // Envoi du verdict vers ResultActivity
    private void envoyerVerdict(boolean verdict) {

        Intent intent = new Intent(
                VerificationActivity.this,
                ResultActivity.class
        );

        intent.putExtra(
                IntentKeys.key(IntentKeys.MATRICULE, "VERDICT"),
                verdict
        );

        intent.putExtra(
                IntentKeys.key(IntentKeys.MATRICULE, "NOM_VOYAGEUR"),
                nomVoyageur
        );

        startActivity(intent);
    }
}
