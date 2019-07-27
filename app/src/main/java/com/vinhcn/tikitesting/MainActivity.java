package com.vinhcn.tikitesting;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.vinhcn.tikitesting.Adapter.KeysAdapter;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    RecyclerView mRecyclerView;
    List<String> list;
    String json = "[  \n" +
            "   \"xiaomi\",\n" +
            "   \"bitis hunter\",\n" +
            "   \"bts\",\n" +
            "   \"balo\",\n" +
            "   \"bitis hunter x\",\n" +
            "   \"tai nghe\",\n" +
            "   \"harry potter\",\n" +
            "   \"anker\",\n" +
            "   \"iphone\",\n" +
            "   \"balo nữ\",\n" +
            "   \"nguyễn nhật ánh\",\n" +
            "   \"đắc nhân tâm\",\n" +
            "   \"ipad\",\n" +
            "   \"senka\",\n" +
            "   \"tai nghe bluetooth\",\n" +
            "   \"son\",\n" +
            "   \"maybelline\",\n" +
            "   \"laneige\",\n" +
            "   \"kem chống nắng\",\n" +
            "   \"anh chính là thanh xuân của em\"\n" +
            "]";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mRecyclerView = findViewById(R.id.rcv);
        mRecyclerView.setAdapter(new KeysAdapter(getNames(json), new KeysAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Toast.makeText(MainActivity.this, getNames(getResources().getString(R.string.json)).get(position), Toast.LENGTH_SHORT).show();
            }
        }));
        LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        mRecyclerView.setLayoutManager(layoutManager);
    }

    List<String> getNames(String json){
        if (list==null){
            JSONArray jsonArray  = null;
            list  = new ArrayList<>();
            try {
                jsonArray = new JSONArray(json);
                for (int i = 0; i < jsonArray.length(); i++){
                    list.add(jsonArray.getString(i));
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return list;
    }
}
