package com.example.myapplication3m1d;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView=findViewById(R.id.listview);
        ArrayList<Summary> datas=new ArrayList<>();
        datas.add(new Summary("简单","1.两数之和","题解16,152","通过率52.3%","1.给定一个整数数组nums 和一个整数目标值target ，请你在该数组中找出和为目标值target的那两个整数，并返回它们的数组下标。\n\n" +
                "你可以假设每种输入只会对应一个答案。但是，数组中同一个元素在答案里不能重复出现。\n\n" +
                "你可以按任意顺序返回答案。\n"));
        datas.add(new Summary("中等","1.两数相加","题解6,152","48.4%","2.给定一个整数数组nums 和一个整数目标值target ，请你在该数组中找出和为目标值target的那两个整数，并返回它们的数组下标。\n\n" +
                "你可以按任意顺序返回答案。\n"));
        datas.add(new Summary("困难","1.两数乘积","题解12,752","通过率32.7%","3.给定一个整数数组nums 和一个整数目标值target ，请你在该数组中找出和为目标值target的那两个整数，并返回它们的数组下标。\n\n" +
                "你可以假设每种输入只会对应一个答案。但是，数组中同一个元素在答案里不能重复出现。\n\n" +
                "你可以按任意顺序返回答案。\n"));
        datas.add(new Summary("简单","1.两数比较","题解18,142","通过率56.6%","4.给定一个整数数组nums 和一个整数目标值target ，请你在该数组中找出和为目标值target的那两个整数，并返回它们的数组下标。\n\n" +
                "你可以假设每种输入只会对应一个答案。但是，数组中同一个元素在答案里不能重复出现。\n\n" +
                "你可以按任意顺序返回答案。\n"));
        MAdapter adapter=new MAdapter(getApplicationContext(),0,datas);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent=new Intent(MainActivity.this,DetailActivity.class);
                intent.putExtra("data", datas.get(i));
                startActivity(intent);
            }
        });
    }
}