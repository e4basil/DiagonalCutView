package com.sprdh.android.diagonalcutview;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.basil.android.diagonalcutview.DiagonalView;

public class MainActivity extends AppCompatActivity {

    private DiagonalView diagonalView;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        ActionBar();
        diagonalView = (DiagonalView) findViewById(R.id.diagonal_view);
        diagonalView.setAngle(15);
        diagonalView.setDiagonalGravity(DiagonalView.LEFT);
        diagonalView.setBackgroundColor(Color.WHITE);
    }

    private void ActionBar() {
        if (getSupportActionBar() == null) {
            setSupportActionBar(toolbar);

        }
    }
}
