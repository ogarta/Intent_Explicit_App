package com.svute.intentexplicit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TableRow;

import java.util.ArrayList;

public class Test extends AppCompatActivity {

    private TableLayout tableLayout;
    private double numImage = 17;
    private int numRow, numImageNow = 0;
    String[] arrNameImages;
    ArrayList<ImageView> imageArrayList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        tableLayout = findViewById(R.id.tableLayout);
        arrNameImages = getResources().getStringArray(R.array.arr_images);
        numRow = (int) Math.ceil(numImage/3);

        for (int i = 0; i < numRow; i++)
        {
            TableRow tableRow = new TableRow(this);
            tableRow.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT,
                    TableRow.LayoutParams.WRAP_CONTENT));
            for (int j = 0; j < 3; j++)
            {
                if(numImageNow <= numImage){
                    ImageView imageView = new ImageView(this);
                    Log.d("TAG", numImageNow+"");
                    imageView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Log.d("TAG", String.valueOf(imageArrayList.indexOf(imageView)));
                        }
                    });
                    imageArrayList.add(imageView);
                    imageView.setImageResource(getResources().getIdentifier(arrNameImages[numImageNow], "drawable", getPackageName()));
                    tableRow.addView(imageView);
                    numImageNow++;
                }

            }
            tableLayout.addView(tableRow);
        }
        Log.d("TAG", imageArrayList.toString());
    }
}