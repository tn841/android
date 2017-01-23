package com.itwill.a09adapterview;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ListView listView;
    String[] poketArr={"피카츄", "라이츄", "파이리", "꼬부기", "버터플", "야도란", "피존투", "또가스", "이상해씨", "폴리곤", "망키", "리자몽", "별가사리", "아쿠스타", "프리저", "썬더"};
    ArrayList<Poketmon> poketList = new ArrayList<Poketmon>();

    private void init(){
        poketList.add(new Poketmon("피카츄", R.drawable.pica));
        poketList.add(new Poketmon("라이츄", R.drawable.raich));
        poketList.add(new Poketmon("파이리", R.drawable.paili));
        poketList.add(new Poketmon("꼬부기", R.drawable.gobu));
        poketList.add(new Poketmon("버터플", R.drawable.butter));
        poketList.add(new Poketmon("야도란", R.drawable.yado));
        poketList.add(new Poketmon("피존투", R.drawable.pizon));
        poketList.add(new Poketmon("또가스", R.drawable.ddoga));
        poketList.add(new Poketmon("캐터피", R.drawable.caterpi));
        poketList.add(new Poketmon("단데기", R.drawable.dandea));
        poketList.add(new Poketmon("뿔충이", R.drawable.bulchung));
        poketList.add(new Poketmon("독침붕", R.drawable.dokchimp));
        poketList.add(new Poketmon("피카츄", R.drawable.pica));
        poketList.add(new Poketmon("라이츄", R.drawable.raich));
        poketList.add(new Poketmon("파이리", R.drawable.paili));
        poketList.add(new Poketmon("꼬부기", R.drawable.gobu));
        poketList.add(new Poketmon("버터플", R.drawable.butter));
        poketList.add(new Poketmon("야도란", R.drawable.yado));
        poketList.add(new Poketmon("피존투", R.drawable.pizon));
        poketList.add(new Poketmon("또가스", R.drawable.ddoga));
        poketList.add(new Poketmon("캐터피", R.drawable.caterpi));
        poketList.add(new Poketmon("단데기", R.drawable.dandea));
        poketList.add(new Poketmon("뿔충이", R.drawable.bulchung));
        poketList.add(new Poketmon("독침붕", R.drawable.dokchimp));
        poketList.add(new Poketmon("피카츄", R.drawable.pica));
        poketList.add(new Poketmon("라이츄", R.drawable.raich));
        poketList.add(new Poketmon("파이리", R.drawable.paili));
        poketList.add(new Poketmon("꼬부기", R.drawable.gobu));
        poketList.add(new Poketmon("버터플", R.drawable.butter));
        poketList.add(new Poketmon("야도란", R.drawable.yado));
        poketList.add(new Poketmon("피존투", R.drawable.pizon));
        poketList.add(new Poketmon("또가스", R.drawable.ddoga));
        poketList.add(new Poketmon("캐터피", R.drawable.caterpi));
        poketList.add(new Poketmon("단데기", R.drawable.dandea));
        poketList.add(new Poketmon("뿔충이", R.drawable.bulchung));
        poketList.add(new Poketmon("독침붕", R.drawable.dokchimp));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = (ListView) findViewById(R.id.list_view);

        Context context = this.getApplicationContext();

        //case1 - 디폴트 어뎁터 사용 (알아서 setText해주는 매커니즘이 있다.)
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter(context, R.layout.list_child_edittext, poketArr);
        //listView.setAdapter(arrayAdapter);


        //case2 - 커스텀 어뎁터 사용
        this.init();
        PoketAdapter poketAdapter = new PoketAdapter(context, 0, poketList);
        listView.setAdapter(poketAdapter);


    }




    public class PoketAdapter extends ArrayAdapter<Poketmon>{
        public PoketAdapter(Context context, int resource, ArrayList arrayList) {
            super(context, resource, arrayList);
        }

        @Override
        public int getCount() {
            Log.e("Count", ""+super.getCount());
            return super.getCount();
        }

        @NonNull
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View view = getLayoutInflater().inflate(R.layout.list_child_relative, null);

            Poketmon item = poketList.get(position);
            String poketName = item.getPoketName();
            int img_id = item.getImg_id();

            TextView textView = (TextView)view.findViewById(R.id.poket_name);
            textView.setText(poketName);

            ImageView imageView = (ImageView)view.findViewById(R.id.poket_img);
            imageView.setImageResource(img_id);

            Log.e("call", "call");
            return view;
        }

        @Nullable
        @Override
        public Poketmon getItem(int position) {
            return super.getItem(position);
        }

    }//class PocketAdapter
}
