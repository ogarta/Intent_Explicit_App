package com.svute.intentexplicit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TableRow;

import java.util.ArrayList;

public class GalleryImageActivity extends AppCompatActivity {
    private TableLayout tableLayout;
    private double numImage = 17;
    private int numRow, numImageNow = 0;
    String[] arrNameImages;
    String idImage;
    String imgGet;
    ArrayList<ImageView> imageArrayList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery_image);

        tableLayout = findViewById(R.id.tableLayout);
        arrNameImages = getResources().getStringArray(R.array.arr_images);
        //Tính số hàng cần tạo kết quả làm tròn lên: 2.3->3
        numRow = (int) Math.ceil(numImage/3);

        Intent intentGet = getIntent();
        if(intentGet != null){
            // Nhận vị trí index trong StringArr từ activity MainActivity: Hình đã RanDom lúc đầu
            imgGet = intentGet.getStringExtra("imgRandomMain");

        }

        for (int i = 0; i < numRow; i++) {
            // Tạo 1 hàng trong TableLayout
            TableRow tableRow = new TableRow(this);
            tableRow.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT,
                TableRow.LayoutParams.WRAP_CONTENT));

            for (int j = 0; j < 3; j++){
                // Tạo 3 cột trong TableLayout
                if(numImageNow <= numImage){
                    // Kiểm tra vị trí cuối cùng của StringArray
                    //Tạo ra 1 view: imageView
                    ImageView imageView = new ImageView(this);
                    // Tạo sự kiện click
                    imageView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            // Gửi vị trí index trong StringArr từ activity GalleyImage: Hình đã RanDom lúc đầu và Hình vừa chọn
                            Intent intent = new Intent(GalleryImageActivity.this,MainActivity.class);
                            // Lấy vị trí của image trong imageArrayList cũng là vị trí index trong StringArray của bức hình: imageArrayList.indexOf(imageView)
                            intent.putExtra("imagePick",String.valueOf(imageArrayList.indexOf(imageView)));
                            intent.putExtra("imgRandomMain",imgGet);
                            startActivity(intent);
                            finish();
                        }
                    });
                    // Thêm imageView vào imageArrayList
                    imageArrayList.add(imageView);
                    imageView.setImageResource(getResources().getIdentifier(arrNameImages[numImageNow], "drawable", getPackageName()));
                    //Thêm hình vào trong cột
                    tableRow.addView(imageView);
                    numImageNow++;
                }
            }
            //thêm hàng vừa mới tạo vào tableLayout
            tableLayout.addView(tableRow);
        }
    }

}