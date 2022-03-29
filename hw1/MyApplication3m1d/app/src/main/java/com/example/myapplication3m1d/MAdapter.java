package com.example.myapplication3m1d;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class MAdapter extends ArrayAdapter<Summary> {
    public MAdapter(@NonNull Context context, int resource, @NonNull List<Summary> objects) {
        super(context, resource, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Summary summary=getItem(position);
        View view = LayoutInflater.from(getContext()).inflate(R.layout.summary_item, null);
        TextView difficulty=view.findViewById(R.id.difficuly);//难度
        TextView title=view.findViewById(R.id.title);//题目
        TextView problemSolutionNum=view.findViewById(R.id.pro_su_num);//题解
        TextView passRate=view.findViewById(R.id.pass_rate);//通过率
        difficulty.setText(summary.getDifficulty());
        title.setText(summary.getTitle());
        problemSolutionNum.setText(summary.getProblemSolutionNum());
        passRate.setText(summary.getPassRate());
        if(summary.getDifficulty().equals("简单"))
            difficulty.setTextColor(0xff09DA2C);
        if(summary.getDifficulty().equals("中等"))
            difficulty.setTextColor(0xffCD9E10);
        if(summary.getDifficulty().equals("困难"))
            difficulty.setTextColor(0xffFF0016);
        return view;

    }
}
