package my.edu.utar.individualassignment;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity3 extends AppCompatActivity {

    private Button ComposeB;
    private Button DecomposeB;
    private TextView output;
    private EditText editTextNumber1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Composing Section");


        // Composing sections
        editTextNumber1 = findViewById(R.id.editTextNumber1);
        output = findViewById(R.id.output);
        ComposeB = findViewById(R.id.ComposeB);
        DecomposeB = findViewById(R.id.DecomposeB);

        ComposeB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                composeNum();
            }
        });

        DecomposeB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                decomposeNum();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.menu, menu);

        menu.add("Exit")
                .setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(@NonNull MenuItem item) {
                        finish();
                        return false;
                    }
                });

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item){
        super.onOptionsItemSelected(item);
        if(item.getItemId()==R.id.Order){
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            return true;
        }
        else if(item.getItemId()==R.id.Composite){
            Intent intent = new Intent(this, MainActivity2.class);
            startActivity(intent);
            return true;
        }

        return false;
    }

    private void composeNum() {
        String editStr = editTextNumber1.getText().toString().trim();

        // Check if the input is not empty
        if (!editStr.isEmpty()) {
            String result = "";
            // Convert the input string into a character array
            char[] digits = editStr.toCharArray();

            for (int i = 0; i < digits.length; i++) {
                // Convert the character to its numeric value
                int num = Character.getNumericValue(digits[i]);
                int power = digits.length - i - 1;
                if (num != 0) {
                    if (!result.isEmpty()) {
                        result += " + ";
                    }
                    // the digit multiplied by 10 raised to the power of its position.
                    result += (num * (int)Math.pow(10, power));
                }
            }
            // Display the composed number in the output view to be visible.
            output.setText(result);
            output.setVisibility(View.VISIBLE);
        } else {
            Toast.makeText(this, "Please enter a number", Toast.LENGTH_SHORT).show();
        }
    }

    private void decomposeNum() {
        String editStr = editTextNumber1.getText().toString().trim();
        // Check if the input string is not empty.
        if (!editStr.isEmpty()) {
            // Remove any spaces and split by the '+' character
            String[] parts = editStr.replaceAll("\\s+", "").split("\\+");
            int sum = 0;
            for (String part : parts) {
                // Parse each part to an integer and sum it all
                sum += Integer.parseInt(part);
            }
            output.setText(String.valueOf(sum));
            output.setVisibility(View.VISIBLE);
        } else {
            Toast.makeText(this, "Please enter a number", Toast.LENGTH_SHORT).show();
        }
    }
}

