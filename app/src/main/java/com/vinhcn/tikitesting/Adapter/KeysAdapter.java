package com.vinhcn.tikitesting.Adapter;

import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.vinhcn.tikitesting.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class KeysAdapter extends RecyclerView.Adapter<KeysAdapter.RecyclerViewHolder> {

    private List<String> objects = new ArrayList<>();
    private final OnItemClickListener listener;
    String arrColor[] = {"#16702e", "#005a51", "#996c00", "#5c0a6b", "#006d90", "#974e06", "#99272e", "#89221f", "#00345d"};
    int posColor = 0;

    public interface OnItemClickListener {
        void onItemClick(int position);
    }


    public KeysAdapter(List<String> data, OnItemClickListener listener) {
        this.objects = data;
        this.listener = listener;
    }

    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.custom_items, parent, false);
        return new RecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerViewHolder holder, int position) {
        holder.bind(position, listener);

    }

    @Override
    public int getItemCount() {
        return objects.size();
    }


    public class RecyclerViewHolder extends RecyclerView.ViewHolder {
        TextView tvName;

        public RecyclerViewHolder(View itemView) {
            super(itemView);
            tvName = (TextView) itemView.findViewById(R.id.name);
        }

        public void bind(final int position, final OnItemClickListener listener) {
            String name = objects.get(position);
            int spaces = name.replaceAll("[^ ]", "").length();
            switch (spaces) {
                case 0:
                    break;
                case 1:
                    name = name.replace(" ", "\n");
                    break;
                default:
                    name = getNameEnter(name);
                    break;

            }
            tvName.setText(name);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onItemClick(position);
                }
            });

//            int idx = new Random().nextInt(arrColor.length);
//            String randomColor = (arrColor[idx]);
//            changeSolid(randomColor, tvName);

            int idx = new Random().nextInt(arrColor.length);
            String randomColor = (arrColor[idx]);
            if (posColor < arrColor.length){
                changeSolid(arrColor[posColor], tvName);
            }else {
                posColor = 0;
                changeSolid(arrColor[posColor], tvName);
            }

            posColor++;
        }
    }

    private String getNameEnter(String name) {
        int index = 0;
        List<Integer> res = new ArrayList<>();
        while ((index = name.indexOf(' ', index + 1)) > 0) {
            res.add(index);
        }
       if (res.size()%2==0){
           //        "anh chính là thanh xuân của em";
//         3
//         9
//        12
//        18
//        23
//        27
           int pos1 = res.get(res.size()/2 - 1);
           int pos2 = res.get(res.size()/2);
           Log.d("VinhCNLog: ", pos1 + "  " +  pos2);
           String pos2toFirst = name.substring(0, pos2);
           String pos1toEnd = name.substring(pos1, name.length());
           Log.d("VinhCNLog: ", pos2toFirst + "  " +  pos1toEnd);
           if (pos2toFirst.length() >= pos1toEnd.length()){
               name = replace(name, pos1, '\n');
           }else {
               name = replace(name, pos2, '\n');

           }
       }else {
           //        "anh chính là thanh xuân của";
//         3
//         9
//        12
//        18
//        23
           int pos1 = res.get(res.size()/2 - 1);
           int pos2 = res.get(res.size()/2);
           String pos2toFirst = name.substring(0, pos2);
           String pos1toEnd = name.substring(pos2, name.length());
           Log.d("VinhCNLog: ", pos2toFirst + "  " +  pos1toEnd);
           if (pos2toFirst.length() <= pos1toEnd.length()){
               name = replace(name, pos2, '\n');

           }else {
               name = replace(name, pos1, '\n');
           }
       }
        return name;

    }

    public String replace(String str, int index, char replace){
        if(str==null){
            return str;
        }else if(index<0 || index>=str.length()){
            return str;
        }
        char[] chars = str.toCharArray();
        chars[index] = replace;
        return String.valueOf(chars);
    }
//    bitis hunter x

    void changeSolid(String color, TextView tv){
        GradientDrawable myGrad = (GradientDrawable)tv.getBackground();
        myGrad.setColor(Color.parseColor(color));
    }
}
