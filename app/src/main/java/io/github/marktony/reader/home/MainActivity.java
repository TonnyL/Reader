package io.github.marktony.reader.home;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import io.github.marktony.reader.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportFragmentManager().beginTransaction().replace(R.id.main_container, new QsbkFragment()).commit();

    }

}
