package com.example.user.kalkulatorgeometri;


import android.app.NotificationManager;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.NotificationCompat;
import android.text.InputType;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    Spinner spinner;
    EditText inputOne;
    EditText inputTwo;
    String[] bangun;
    int loc;
    double l;
    double k;
    String p;
    String s;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        spinner = (Spinner)findViewById(R.id.spinner);
        inputTwo = (EditText)findViewById(R.id.inputTwo);
        inputOne = (EditText)findViewById(R.id.inputOne);
        bangun = getResources().getStringArray(R.array.bangun);
        //loc = spinner.getSelectedItemPosition();
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, bangun);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                loc = spinner.getSelectedItemPosition();
                if(loc == 1){
                    inputTwo.setEnabled(false);
                }else{
                    inputTwo.setEnabled(true);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


    }

    public void hitungHasil(View view) {
        Float i1 = Float.parseFloat(inputOne.getText().toString());
        Float i2;
        String test = inputTwo.getText().toString();
        String bentuk;
        if(!test.isEmpty()) {
            i2 = Float.parseFloat(inputTwo.getText().toString());
        }else{
            i2 = Float.valueOf(0);
        }
        loc = spinner.getSelectedItemPosition();
        bentuk = bangun[loc];
        if(loc == 0){
            l=i1*i2;
            k=2*(i1+i2);
        }else{
            if (loc == 1){
                l=Math.PI*i1*i1;
                k=Math.PI*2*i1;
            }else{
                if(loc == 2){
                    l=(i1*i2)/2;
                    k=i1+i2+Math.sqrt((i1*i1)+(i2*i2));
                }
            }
        }
        TextView luas = (TextView)findViewById(R.id.textView2);
        TextView kll = (TextView)findViewById(R.id.textView3);
        luas.setText("Luas dari bangun datar: "+l);
        kll.setText("Keliling dari bangun datar: "+k);
        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(this);
        mBuilder.setSmallIcon(R.drawable.notification);
        mBuilder.setContentTitle(bentuk);
        mBuilder.setContentText("Luas: "+l+" dan Keliling: "+k);
        NotificationManager mNotificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        mNotificationManager.notify(001, mBuilder.build());
    }
}
