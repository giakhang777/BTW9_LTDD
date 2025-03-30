package com.example.viewfipper.activity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import com.example.viewfipper.R;
import com.example.viewfipper.adapter.SliderAdapter;
import com.example.viewfipper.model.ImageSlider;

import java.util.ArrayList;
import java.util.List;

import me.relex.circleindicator.CircleIndicator3;

public class SliderActivity extends AppCompatActivity {

    private ViewPager2 viewPager2;
    private CircleIndicator3 indicator;
    private SliderAdapter adapter;
    private List<ImageSlider> imagesList;
    private Handler handler = new Handler(Looper.getMainLooper());
    private Runnable runnable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_slider);

        viewPager2 = findViewById(R.id.viewPager);
        indicator = findViewById(R.id.indicator);

        // Thêm dữ liệu hình ảnh
        imagesList = new ArrayList<>();
        imagesList.add(new ImageSlider(R.drawable.quangcao));
        imagesList.add(new ImageSlider(R.drawable.coffee));
        imagesList.add(new ImageSlider(R.drawable.companypizza));
        imagesList.add(new ImageSlider(R.drawable.themoingon));

        adapter = new SliderAdapter(this, imagesList);

        viewPager2.setAdapter(adapter);
        indicator.setViewPager(viewPager2);

        // Tự động chuyển slide
        autoSlideImages();
    }

    private void autoSlideImages() {
        runnable = new Runnable() {
            @Override
            public void run() {
                int currentItem = viewPager2.getCurrentItem();
                int totalItem = imagesList.size() - 1;

                if (currentItem < totalItem) {
                    viewPager2.setCurrentItem(currentItem + 1);
                } else {
                    viewPager2.setCurrentItem(0);
                }

                handler.postDelayed(this, 3000); // Chuyển ảnh sau 3 giây
            }
        };
        handler.postDelayed(runnable, 3000);

        // Dừng auto slide khi người dùng tương tác
        viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                handler.removeCallbacks(runnable);
                handler.postDelayed(runnable, 3000);
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        handler.removeCallbacks(runnable);
    }
}