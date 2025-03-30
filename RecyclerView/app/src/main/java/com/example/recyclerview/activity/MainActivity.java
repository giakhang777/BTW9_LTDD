package com.example.recyclerview.activity;

import android.os.Bundle;
import androidx.appcompat.widget.SearchView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.recyclerview.R;
import com.example.recyclerview.adapter.IconAdapter;
import com.example.recyclerview.model.IconModel;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    RecyclerView rcIcon;
    List<IconModel> arrayList1;
    IconAdapter iconAdapter;
    SearchView searchView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        rcIcon = findViewById(R.id.rcIcon);
        arrayList1 = new ArrayList<>();

        arrayList1.add(new IconModel(R.drawable.icon1, "jfdjfdjf djfdh"));
        arrayList1.add(new IconModel(R.drawable.icon2, "sdddf sdfsf"));
        arrayList1.add(new IconModel(R.drawable.icon3, "sdddf sdfss"));
        arrayList1.add(new IconModel(R.drawable.icon4, "dfgfhvh sxdff"));
        arrayList1.add(new IconModel(R.drawable.icon5, "jfdjfdjf djfdh"));
        arrayList1.add(new IconModel(R.drawable.icon6, "sdddf sdfsf"));
        arrayList1.add(new IconModel(R.drawable.icon1, "jfdjfdjf djfdh"));
        arrayList1.add(new IconModel(R.drawable.icon2, "sdddf sdfsf"));
        arrayList1.add(new IconModel(R.drawable.icon3, "sdddf sdfss"));
        arrayList1.add(new IconModel(R.drawable.icon4, "dfgfhvh sxdff"));
        arrayList1.add(new IconModel(R.drawable.icon5, "jfdjfdjf djfdh"));
        arrayList1.add(new IconModel(R.drawable.icon6, "sdddf sdfsf"));

        GridLayoutManager gridLayoutManager = new GridLayoutManager(MainActivity.this, 1, GridLayoutManager.VERTICAL, false);
        rcIcon.setLayoutManager(gridLayoutManager);
        iconAdapter = new IconAdapter(getApplicationContext(), arrayList1);
        rcIcon.setAdapter(iconAdapter);
        searchView = findViewById(R.id.searchView);
        searchView.clearFocus();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                filterListener(newText);
                return true;
            }
        });

    }
    private void filterListener(String text) {
        List<IconModel> list = new ArrayList<>();
        for (IconModel iconModel : arrayList1) {
            if (iconModel.getDesc().toLowerCase().contains(text.toLowerCase())) {
                list.add(iconModel);
            }
        }
        if (list.isEmpty()) {
            Toast.makeText(this, "Không có dữ liệu", Toast.LENGTH_SHORT).show();
        } else {
            iconAdapter.setListenerList(list);
        }
    }
}