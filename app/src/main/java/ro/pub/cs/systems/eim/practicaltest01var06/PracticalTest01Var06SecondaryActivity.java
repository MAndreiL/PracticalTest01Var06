package ro.pub.cs.systems.eim.practicaltest01var06;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class PracticalTest01Var06SecondaryActivity extends AppCompatActivity {

    Button ok;
    TextView gained;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practical_test01_var06_secondary);

        ok = findViewById(R.id.ok);
        gained = findViewById(R.id.gained);

        int score = getIntent().getIntExtra("Gained ", 0);
        gained.setText("Gained " + score);

        ok.setOnClickListener(v -> {
            Intent resultIntent = new Intent();
            resultIntent.putExtra("Gained", score);
            setResult(RESULT_OK, resultIntent);
            finish();
        });
    }
}