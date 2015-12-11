package de.pgoldberg.kassensturz;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * A placeholder fragment containing a simple view.
 */
public class SummeFragment extends Fragment {

   // private View mySummeRootView;

    public SummeFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_summe, container, false);

/*        // Textfeld "summeHargeld" als Hartgeld referenzieren
        TextView Hartgeld = (TextView) rootView.findViewById(R.id.summeHartgeld);
        // Summe/Text (aus HargeldFragment.summeHartgeld()) setzen
        Hartgeld.setText(String.valueOf(HartgeldFragment.getSummeHartgeld()));*/

        //mySummeRootView = rootView;

        return rootView;
    }

    @Override
    public void onStart(){
        super.onStart();
        HartgeldFragment.getSummeHartgeld();
        TextView Hartgeld = (TextView) getView().findViewById(R.id.summeHartgeld);
        Hartgeld.setText(String.format(String.valueOf(HartgeldFragment.getSummeHartgeld()),"%.2f"));
    }
}
