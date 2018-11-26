package com.gavhane.b.vivek.fibonacciseriesapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AbsListView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import static android.icu.lang.UCharacter.GraphemeClusterBreak.V;

public class MainActivity extends AppCompatActivity {
    
    
    // All the comments are added inline

    EditText et1;
    Button b1,b2;
    TextView tv1;
    ListView lv;

     int x,p;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        et1 = (EditText) findViewById(R.id.et1);                // to take number input from user upto which App have to calculate Fibonacci series 

        tv1 = (TextView) findViewById(R.id.tv1);                // to show upto what number we calculated Fibonacci series after clicking calculate buttton
        tv1.setVisibility(View.INVISIBLE);

        b1 = (Button) findViewById(R.id.b1);                    // Button to to calculate Fibonacci series after it's click
        b2 = (Button) findViewById(R.id.b2);                    // Button to allow user to retype the number to calculate
        b2.setVisibility(View.INVISIBLE);                       


        for (int i=0;i<51;i++)                                  // for loop to memorise the array of already calculated Fibonacci numbers
        {
            F[i] = -1;                                          //  used status -1 to know if the Fibonacci is already calculated in memory
        }

        b1.setOnClickListener(new View.OnClickListener() {      // button b1 on-click listener
            @Override
            public void onClick(View view) {
                String result = et1.getText().toString();        // get number in the String format from et1 edit text field
                x = Integer.parseInt(result);                    // convert above number from String to integer


                try                                              // Use try catch block to handle exceptions to prevent App from crashing in case invalid inputs
                {
                    ListView resultsListViewNew = (ListView) findViewById(R.id.lv);                         // List view of android to display results to UI
                    LinkedHashMap<String,String> nameAddressesNew = new LinkedHashMap<String,String>();     // Linked hashmap to store results in an order in which they are entered

                    for (int p=0;p<=x;p++) {                                                                // for loop to enter fibonacci values till the number x which user has entered

                        if (p == 2) {
                            nameAddressesNew.put("f(" + 2 + ") =", "                   " + 0);
                        } else if (p == 1) {
                            nameAddressesNew.put("f(" + 1 + ") =", "                   " + 1);
                        } else if (p == 0) {
                            nameAddressesNew.put("f(" + 0 + ") =", "                   " + 1);
                        } else {
                            nameAddressesNew.put("f(" + p + ") =", "                   " + Fib(p));
                        }
                    }

                    List<TreeMap<String, String>> listItemsNew = new ArrayList<>();

                    SimpleAdapter adapterNew = new SimpleAdapter(MainActivity.this,listItemsNew , R.layout.list_item,           // simple adapter with 5 arguments describing and connecting main 
                            new String[]{ "First Line","Second Line"}, new int[]{R.id.text1, R.id.text2});

                    Iterator it = nameAddressesNew.entrySet().iterator();                                // taking all the values of linked hashmap and iterate it to enter those in treemap to give them designed fonts through xml
                    while (it.hasNext())                                                                 // while loop to iterate through all these values
                    {
                        TreeMap<String, String> resultsMap = new TreeMap<>();
                        Map.Entry pair = (Map.Entry)it.next();
                        resultsMap.put("First Line", pair.getKey().toString());                          // all keys will get first line format
                        resultsMap.put("Second Line", pair.getValue().toString());                       // all values will get second line format
                        listItemsNew.add(resultsMap);                                                    // put the resulted values in our List
                    }

                    resultsListViewNew.setAdapter(adapterNew);                                            // apply our customised adapter along with all the data to List view
                }
                catch (Exception e)
                {
                    Toast.makeText(getApplicationContext(), "Bitte geben Sie die Zahl bis 50 ein ", Toast.LENGTH_LONG).show();          // if user exceeds the limit then instead of crashing of an App this message will be displayed as toast
                }
                
                
                
               // Below code is for setting of buttons and text fields visibility after calculating the fibonacci series by clicking button 1 

                tv1.setVisibility(View.VISIBLE);
                tv1.setText("Fibonacci series bis:"+ x);

                b2.setVisibility(View.VISIBLE);

                b1.setVisibility(View.INVISIBLE);
                et1.setVisibility(View.INVISIBLE);

                }
        });
        
             // Below code is for setting of buttons and text fields visibility after calculating the fibonacci series by clicking button 2 to allow user to re-enter number for new fibonacci series

        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                et1.setVisibility(View.VISIBLE);
                b1.setVisibility(View.VISIBLE);

                b2.setVisibility(View.INVISIBLE);
                tv1.setVisibility(View.INVISIBLE);
            }
        });

    }

    int[] F = new int[51];                          // this int array stores already calculated fibonacci values to use them for next time to minimise the calculation time   (improved performance by memorisation technique)

    int Fib(int n) {                                // method to calculate fibonacci number,  passed object is a number that user will enter upto which we need to calculate the fibonacci series.

        if (n <= 1) {                               // to take fibonacci values as equal to entered number n   for 0 and 1  equal to 0 and 1 respectively
            return n;
        }
        if (F[n] != -1) {                           // if F[n] status is not -1 means fibonacci is already calculated. So directly return already calculated value. No need to calculate it again
            return F[n];
        }
        F[n] = Fib(n - 1) + Fib(n - 2);             // fibonacci formula to calculate fibonacci of number = n
        return F[n];
    }
}

