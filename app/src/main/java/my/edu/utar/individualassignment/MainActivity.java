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



public class MainActivity extends AppCompatActivity {

    private EditText num1Edit, num2Edit;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Comparing Sections");

    //Comparing Sections
    num1Edit = findViewById(R.id.num1Edit);
    num2Edit = findViewById(R.id.num2Edit);


    Button compareB = findViewById(R.id.compareB);


    compareB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View V){
                compareNumbers();
            }
        });

    }
            // Menu bar for choosing sections
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

            // options for each sections in different page
            @Override
            public boolean onOptionsItemSelected(@NonNull MenuItem item){
                super.onOptionsItemSelected(item);
                if(item.getItemId()==R.id.Order){
                    Intent intent = new Intent(this, MainActivity2.class);
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

            private void compareNumbers(){
                String num1Str = num1Edit.getText().toString();
                String num2Str = num2Edit.getText().toString();

                if (!num1Str.isEmpty() && !num2Str.isEmpty()){
                    int num1 = Integer.parseInt(num1Str);
                    int num2 = Integer.parseInt(num2Str);

                    String result = compare(num1,num2);
                    showToast(result);
                }
                else{
                    showToast("Please enter both numbers");
                }
            }
            // functions for comparing two numbers
             public static String compare(int num1, int num2){
              if (num1 > num2){
                return num1 + " is greater than " + num2;
              }
              else if (num1 < num2){
                return num1 + " is less than " + num2;
              }
              else {
                return "Both numbers (" + num1 + ") are equal";
              }
           }

           // Displays a short-duration toast message with the given output string.
            private void showToast(String output){
                Toast.makeText(this, output, Toast.LENGTH_SHORT).show();
            }
}


