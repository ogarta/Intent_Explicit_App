package com.svute.intentexplicit;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    String[] arrNameImages;
    ImageView imgRandom, imgPick;
    TextView txtScore;
    Random random;
    int valueImgRandom = -1;
    int indexRandom;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imgPick = findViewById(R.id.imgPick);
        imgRandom = findViewById(R.id.imgRandom);
        txtScore = findViewById(R.id.textViewScore);

        random = new Random();
        arrNameImages = getResources().getStringArray(R.array.arr_images);

        Intent intentGet = getIntent();
        if(intentGet.getStringExtra("imgRandomMain") != null){
            // Nhận vị trí index trong StringArr từ activity GalleyImage: Hình đã RanDom lúc đầu và Hình vừa chọn
            imgPick.setImageResource(getResources().getIdentifier(arrNameImages[Integer.parseInt(intentGet.getStringExtra("imagePick"))],"drawable",getPackageName() ));
            indexRandom = Integer.parseInt(intentGet.getStringExtra("imgRandomMain"));
            imgRandom.setImageResource(getResources().getIdentifier(arrNameImages[indexRandom], "drawable", getPackageName()));

            if(intentGet.getStringExtra("imagePick").equals(intentGet.getStringExtra("imgRandomMain"))){
                //So sánh vị trí index trong StringArr của 2 tấm hình Random và chọn
                Score.score+=1;
                txtScore.setText("Điểm: "+ Score.score+"");

            }

        }
        else{
            randomImage(imgRandom);
        }

        imgPick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, GalleryImageActivity.class);
                intent.putExtra("imgRandomMain",indexRandom+"");
                startActivity(intent);

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
}