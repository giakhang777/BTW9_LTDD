package com.example.viewflipper_bt.activity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import com.example.viewflipper_bt.R;
import com.example.viewflipper_bt.adapter.ImageAdapter;
import com.example.viewflipper_bt.model.ImageModel;
import com.example.viewflipper_bt.model.MessageModel;
import com.example.viewflipper_bt.service.APIService;

import java.util.List;

import me.relex.circleindicator.CircleIndicator3;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    private ViewPager2 viewPager;
    private CircleIndicator3 indicator;
    private ImageAdapter adapter;
    private Handler handler;
    private Runnable autoSlideRunnable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewPager = findViewById(R.id.viewPager);
        indicator = findViewById(R.id.indicator);

        handler = new Handler(Looper.getMainLooper());

        loadImagesFromApi();
    }

    private void startAutoSlide() {
        if (adapter != null && adapter.getItemCount() > 0) {
            autoSlideRunnable = () -> {
                int nextItem = (viewPager.getCurrentItem() + 1) % adapter.getItemCount();
                viewPager.setCurrentItem(nextItem, true);
                handler.postDelayed(autoSlideRunnable, 3000);
            };
            handler.postDelayed(autoSlideRunnable, 3000);
        }
    }

    private void loadImagesFromApi() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://app.iotstar.vn:8081/appfoods/flipper/coffee.jpg/") // phải kết thúc bằng /
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        APIService apiService = retrofit.create(APIService.class);

        apiService.LoadImageSlider(0).enqueue(new Callback<MessageModel>() {
            @Override
            public void onResponse(Call<MessageModel> call, Response<MessageModel> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<ImageModel> images = response.body().getData();
                    if (images != null && !images.isEmpty()) {
                        adapter = new ImageAdapter(images, MainActivity.this);
                        viewPager.setAdapter(adapter);
                        indicator.setViewPager(viewPager);
                        startAutoSlide(); // bắt đầu auto slide khi đã có adapter
                    }
                } else {
                    Toast.makeText(MainActivity.this, "Không lấy được dữ liệu!", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<MessageModel> call, Throwable t) {
                t.printStackTrace();
                Toast.makeText(MainActivity.this, "Lỗi kết nối API!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    protected void onPause() {
        super.onPause();
        handler.removeCallbacks(autoSlideRunnable);
    }

    @Override
    protected void onResume() {
        super.onResume();
        startAutoSlide();
    }
}
