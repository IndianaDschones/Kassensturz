package de.pgoldberg.kassensturz;


import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.support.v4.app.DialogFragment;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class Kassenuebersicht extends AppCompatActivity implements NeueKasse.NoticeDialogListener {

    private ListView listView;
    List<String> kassennamen = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kassenuebersicht);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        /**
         * NeueKasse erstellen
         * Es erscheint der AlterDialog mit dem Textfeld zum anlegen einer neuen Kasse
         */
        FloatingActionButton fabNeueKasse = (FloatingActionButton) findViewById(R.id.fabNeueKasse);
        fabNeueKasse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Methode zum Dialogaufruf
                showNoticeDialog();
            }
        });

        Button neuerKassensturz = (Button) findViewById(R.id.button_neuerKassensturz);
        neuerKassensturz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent neuerKassensturz = new Intent(Kassenuebersicht.this, NeuerKassensturz.class);
                startActivity(neuerKassensturz);
            }
        });

        listView = (ListView) findViewById(R.id.listViewKassen);

        ArrayAdapter<String> kassennamenArrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, kassennamen);
        listView.setAdapter(kassennamenArrayAdapter);

        //ToDo Hinweis anzeigen, wenn keine Kassen angelegt
        if (kassennamen.isEmpty()) {
            TextView empty = (TextView) findViewById(R.id.kassenuebersichtEmptyState);
            empty.setText("Sie haben noch keine Kassen angelegt.");
        }
    }

    public void showNoticeDialog() {
        // Create an instance of the dialog fragment and show it
        DialogFragment dialog = new NeueKasse();
        dialog.show(getSupportFragmentManager(), "NeueKasse");
    }


    // The dialog fragment receives a reference to this Activity through the
    // Fragment.onAttach() callback, which it uses to call the following methods
    // defined by the NoticeDialogFragment.NoticeDialogListener interface
    @Override
    public void onDialogPositiveClick(DialogFragment dialog) {
        // User touched the dialog's positive button
        EditText et_Kasse = (EditText) dialog.getDialog().findViewById(R.id.et_Kassenname);
        String kasse = et_Kasse.getText().toString();
        //ToDo Meldung über ungültigen Namen IN EditText auslagern
        if (kasse.length() <= 0) {
            et_Kasse.setError(getString(R.string.error_kassenname));
        } else {
            kassennamen.add(kasse.trim());
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_kassenuebersicht, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void snackbar(String message) {
        Snackbar.make(findViewById(R.id.listViewKassen), message, Snackbar.LENGTH_SHORT)
                .show();
    }
}
