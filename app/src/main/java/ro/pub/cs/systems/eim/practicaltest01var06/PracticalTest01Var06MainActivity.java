package ro.pub.cs.systems.eim.practicaltest01var06;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;

public class PracticalTest01Var06MainActivity extends AppCompatActivity {

    private static final int SECONDARY_ACTIVITY_REQUEST_CODE = 1;
    private int cumulativeScore = 0;
    Button play, compute;
    TextView number1, number2, number3;
    CheckBox checkBox1, checkBox2, checkBox3;

    private ActivityResultLauncher<Intent> activityResultLauncher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practical_test01_var06_main);
        play = findViewById(R.id.button);
        compute = findViewById(R.id.button2);
        number1 = findViewById(R.id.number1);
        number2 = findViewById(R.id.number2);
        number3 = findViewById(R.id.number3);
        checkBox1 = findViewById(R.id.checkBox2);
        checkBox2 = findViewById(R.id.checkBox1);
        checkBox3 = findViewById(R.id.checkBox3);

        number1.setText("0");
        number2.setText("0");
        number3.setText("0");

        activityResultLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if (result.getResultCode() == RESULT_OK && result.getData() != null) {
                        // Obținem scorul din activitatea secundară
                        int score = result.getData().getIntExtra("score", 0);
                        cumulativeScore += score;  // Adunăm scorul
                        String result1 = "Scor cumulat: " + cumulativeScore;
                        Toast.makeText(PracticalTest01Var06MainActivity.this, result1, Toast.LENGTH_SHORT).show();
                    }
                }
        );

        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Verificăm dacă fiecare checkbox nu este bifat înainte de a modifica valoarea
                if (!checkBox1.isChecked()) {
                    number1.setText(generateRandomValue());
                }
                if (!checkBox2.isChecked()) {
                    number2.setText(generateRandomValue());
                }
                if (!checkBox3.isChecked()) {
                    number3.setText(generateRandomValue());
                }

                String result = "Numere: " + number3.getText().toString() + ", "
                        + number1.getText().toString() + ", "
                        + number2.getText().toString();
                Toast.makeText(PracticalTest01Var06MainActivity.this, result, Toast.LENGTH_SHORT).show();
            }
        });

        compute.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Calculăm câte checkbox-uri sunt bifate
                int checkedCount = 0;
                if (checkBox1.isChecked()) checkedCount++;
                if (checkBox2.isChecked()) checkedCount++;
                if (checkBox3.isChecked()) checkedCount++;

                int score = 0;
                if (checkedCount == 0 && areNumbersEqual()) {
                    score = 100;
                } else if (checkedCount == 1 && areNumbersEqual()) {
                    score = 50;
                } else if (checkedCount == 2 && areNumbersEqual()) {
                    score = 10;
                }

                Intent intent = new Intent(getApplicationContext(), PracticalTest01Var06SecondaryActivity.class);
                intent.putExtra("Gained ", score);
                startActivity(intent);
            }
        });
    }

    private boolean areNumbersEqual() {
        String num1 = number1.getText().toString();
        String num2 = number2.getText().toString();
        String num3 = number3.getText().toString();

        if (num1.equals(num2) && num2.equals(num3)) {
            return true;
        }

        if (num1.equals("*") && num2.equals("*") ||
                num1.equals("*") && num3.equals("*") ||
                num2.equals("*") && num3.equals("*")) {
            return true;
        }

        if (num1.equals("*") && num2.equals(num3)) {
            return true;
        }
        if (num2.equals("*") && num1.equals(num3)) {
            return true;
        }
        if (num3.equals("*") && num1.equals(num2)) {
            return true;
        }

        return false;
    }

    private String generateRandomValue() {
        Random random = new Random();
        int value = random.nextInt(4); // 0, 1, 2, or 3
        switch (value) {
            case 0: return "1";
            case 1: return "2";
            case 2: return "3";
            case 3: return "*";
            default: return "0";
        }
    }

}