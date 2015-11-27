package de.pgoldberg.kassensturz;


import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.support.v4.app.DialogFragment;

public class Kassenuebersicht extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kassenuebersicht);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fabNeueKasse = (FloatingActionButton) findViewById(R.id.fabNeueKasse);
        fabNeueKasse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*Intent neueKasse = new Intent(Kassenuebersicht.this, NeueKasse.class);
                showDialog(neueKasse);*/
                DialogFragment neueKasse = new NeueKasse();
                neueKasse.show(getSupportFragmentManager(), "neueKasse");
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
}
