package de.pgoldberg.kassensturz;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

/**
 * A placeholder fragment containing a simple view.
 */
public class HartgeldFragment extends Fragment {

    private static double summeHartgeld = 0.0;

    // Array mit den Wertigkeiten der Münzen
    private Double[] wertMuenzen = {0.01, 0.02, 0.05, 0.10, 0.20, 0.50, 1.0, 2.0};

    // Array mit EditText-IDs der Münzen
    private String[] hartgeldAnzahlET = {"editText_1CentAnzahl", "editText_2CentAnzahl", "editText_5CentAnzahl",
            "editText_10CentAnzahl", "editText_20CentAnzahl", "editText_50CentAnzahl", "editText_1EuroAnzahl",
            "editText_2EuroAnzahl"};

    // Array mit den Anzahlen der Münzen
    private Integer[] anzahlMuenzen = new Integer[8];

   // private View myRootView;

    public HartgeldFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_hartgeld, container, false);

        //myRootView = rootView;

        return rootView;
    }


    @Override
    public void onResume(){
        super.onResume();
        summeHartgeld = 0.0;
        //anzahlMunzenArrayFuellen();
    }

    @Override
    public void onPause(){
        super.onPause();
        //getView().getRootView();
        //getParentFragment().onPause();
        //summeHartgeld = 0.0;
        anzahlMunzenArrayFuellen();

            }


    public void anzahlMunzenArrayFuellen() {
        for (int i = 0; i < hartgeldAnzahlET.length; i++) {
            // String für die EditText-IDs der Münzen: EditText_1CentAnzahl usw
            String eTIDs = hartgeldAnzahlET[i];

            int resID = getResources().getIdentifier(eTIDs, "id", "de.pgoldberg.kassensturz");
            EditText muenze = (EditText) getView().findViewById(resID);

            // Wenn kein Wert eingegeben wurde 0 in anzahlMuenzen[] schreiben
            if (muenze.getText().toString().isEmpty()) {
                anzahlMuenzen[i] = 0;
            }
            // Ansonsten Wert übernehmen
            else
                anzahlMuenzen[i] = Integer.parseInt(muenze.getText().toString());

        }
        summeHartgeld();
    }

    /**
     * Summme des Hartgelds mittels Anzahl * Wert berechnen
     */
    public void summeHartgeld() {
        for (int i = 0; i < wertMuenzen.length; i++) {
            summeHartgeld = summeHartgeld + (anzahlMuenzen[i] * wertMuenzen[i]);
        }
    }

    public static double getSummeHartgeld() {

        return summeHartgeld;
    }
}