package my.edu.utar.individualassignment;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import java.util.Arrays;

public class MainActivity2 extends AppCompatActivity {

    private final EditText[] numEdit = new EditText[6];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);


        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Ordering Sections");

        // Ordering sections
        Button ascB = findViewById(R.id.ascB);
        Button descB = findViewById(R.id.descB);

        // Initialize EditText fields
        for (int i = 0; i < 6; i++) {
            int resId = getResources().getIdentifier("numEdit" + i, "id", getPackageName());
            numEdit[i] = findViewById(resId);
        }

        ascB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                orderNumbers(true);
            }
        });

        descB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                orderNumbers(false);
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
            Intent intent = new Intent(this, MainActivity3.class);
            startActivity(intent);
            return true;
        }

        return false;
    }


    // functions for sorting numbers in ascending / descending order
    private void orderNumbers(boolean asc) {
        EditText numInputEdit = findViewById(R.id.numInputEdit);
        String inputString = numInputEdit.getText().toString();

        // check if user did not enter any numbers
        if(inputString.isEmpty()){
            showToast("Please enter numbers");
            return;
        }

        String[] numberStrings = inputString.split(",");

        // not exceed 6 numbers to sort out
        int[] num = new int[6];

        if(numberStrings.length != 6){
            showToast("Please enter 6 numbers to sort");
            return;
        }

        try {
            for (int i = 0; i < numberStrings.length; i++) {
                // Convert each string to an integer and store it in the array 'num'.
                num[i] = Integer.parseInt(numberStrings[i].trim());
                // Check if the integer value is not within the range [0, 999].
                if (num[i] < 0 || num[i] > 999) {
                    // If the number is out of range, display a toast message and exit the method.
                    showToast("Please enter numbers between 0 and 999.");
                    return;
                }
            }
        } catch (NumberFormatException e) {
            //check if the input string cannot be parsed as an integer
            // if yes then exit the method
            return;
        }


        // Sort numbers
        int[] sortedNum = Arrays.copyOf(num, num.length);
        Arrays.sort(sortedNum);
        if (!asc) {
            reverseArray(sortedNum);
        }

        // Display sorted numbers
        StringBuilder result = new StringBuilder();
        for (int n : sortedNum) {
            result.append(n).append(", ");
        }
        showToast("Sorted Numbers: " + result.toString());
    }

    private void reverseArray(int[] arr) {
        int start = 0;
        int end = arr.length - 1;
        // Continue swapping elements until 'start' becomes greater than or equal to 'end'.
        while (start < end) {
            int temp = arr[start];
            arr[start] = arr[end];
            arr[end] = temp;
            start++;
            end--;
        }
    }

    private void showToast(String output) {
        Toast.makeText(this, output, Toast.LENGTH_SHORT).show();
    }
}

