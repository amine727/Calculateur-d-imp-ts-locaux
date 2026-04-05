package com.example.calculateurdimptslocaux;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    // Déclaration des éléments
    private EditText nomInput, adresseInput, surfaceInput, piecesInput;
    private CheckBox piscineCheckbox;
    private TextView resultView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Liaison XML -> Java
        nomInput = findViewById(R.id.input_nom);
        adresseInput = findViewById(R.id.input_adresse);
        surfaceInput = findViewById(R.id.input_surface);
        piecesInput = findViewById(R.id.input_pieces);
        piscineCheckbox = findViewById(R.id.checkbox_piscine);
        resultView = findViewById(R.id.result);

        // Bouton calcul
        findViewById(R.id.button_calcul).setOnClickListener(v -> calculer());
    }

    private void calculer() {

        // Récupération des valeurs
        String nom = nomInput.getText().toString().trim();
        String adresse = adresseInput.getText().toString().trim();
        String surfaceStr = surfaceInput.getText().toString().trim();
        String piecesStr = piecesInput.getText().toString().trim();

        // Vérification des champs
        if (surfaceStr.isEmpty() || piecesStr.isEmpty()) {
            resultView.setText("⚠️ Veuillez remplir tous les champs !");
            return;
        }

        try {
            double surface = Double.parseDouble(surfaceStr);
            int pieces = Integer.parseInt(piecesStr);
            boolean piscine = piscineCheckbox.isChecked();

            // Calculs
            double impotBase = surface * 2;
            double supplement = pieces * 50 + (piscine ? 100 : 0);
            double total = impotBase + supplement;

            // Affichage détaillé
            resultView.setText(
                    "Nom : " + nom + "\n" +
                            "Adresse : " + adresse + "\n\n" +
                            "Impôt de base : " + impotBase + "\n" +
                            "Impôt supplémentaire : " + supplement + "\n" +
                            "Impôt total : " + total + " DH"
            );

        } catch (NumberFormatException e) {
            resultView.setText("⚠️ Erreur de saisie !");
        }
    }
}