package com.svute.intentexplicit;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    String[] arrNameImages;
    ImageView imgRandom, imgPick;
    TextView txtScore;
    Random random;
    int valueImgRandom = -1;
    int indexRandom;
    int REQUEST_CODE = 123;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imgPick = findViewById(R.id.imgPick);
        imgRandom = findViewById(R.id.imgRandom);
        txtScore = findViewById(R.id.textViewScore);

        random = new Random();
        arrNameImages = getResources().getStringArray(R.array.arr_images);
        randomImage(imgRandom);

        imgPick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, GalleryImageActivity.class);
                intent.putExtra("imgRandomMain", indexRandom + "");
                startActivityForResult(intent, REQUEST_CODE);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.item_random:
                randomImage(imgRandom);
                imgPick.setImageResource(R.drawable.nophoto);
                break;
        }
        return true;
    }

    private void randomImage(ImageView imageView) {
        indexRandom = random.nextInt(arrNameImages.length);
        valueImgRandom = getResources().getIdentifier(arrNameImages[indexRandom], "drawable", getPackageName());
        imageView.setImageResource(valueImgRandom);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (resultCode == RESULT_OK && requestCode == REQUEST_CODE && data != null) {
            String resourceReceive = data.getStringExtra("imagePick");
            imgPick.setImageResource(Integer.parseInt(resourceReceive));
            if (resourceReceive.equals(String.valueOf(valueImgRandom))) {
                Score.score += 1;
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        randomImage(imgRandom);
                    }
                },1000);
            } else {
                Toast.makeText(this, "Sai rồi!!", Toast.LENGTH_SHORT).show();
                Score.score = 0;
            }
            txtScore.setText("Điểm: " + Score.score + "");
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}