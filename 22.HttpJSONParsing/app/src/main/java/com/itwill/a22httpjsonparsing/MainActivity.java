package com.itwill.a22httpjsonparsing;

import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.JsonReader;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.Buffer;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ListView listView;
    ArrayAdapter<String> arrayAdapter;
    Adapter adapter;
    ArrayList<String> contactArrayList = new ArrayList();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = (ListView) findViewById(R.id.listView);
        arrayAdapter = new ArrayAdapter(this, R.layout.contact_list_child, contactArrayList){
            @Override
            public int getCount() {
                return super.getCount();
                // return contactArrayList.size();
            }

            @Nullable
            @Override
            public Object getItem(int position) {
                return super.getItem(position);
                //return contactArrayList.get(position);
            }

            @NonNull
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                return super.getView(position, convertView, parent);
            }
        };



        listView.setAdapter(arrayAdapter);

        new Thread(){
            @Override
            public void run() {
                parseJSON();
            }
        }.start();
    }

    Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case 0:
                    String receiveStr = (String) msg.obj;
                    contactArrayList.add(receiveStr);
                    arrayAdapter.notifyDataSetChanged();
                    break;
                case 1:
                    ArrayList<String> receiveList =  (ArrayList<String>)msg.obj;
                    contactArrayList = receiveList; //참조를 잃어버리게함.. 주소값 복사, 재정의를 하지 않았을 경우에 잃어버리게됨.. 21.XML과 비교
                    //Log.e("ddd",contactArrayList.toString());
                    arrayAdapter.addAll(contactArrayList);  //참조를 잃어버렸기 때문에 참조를 넣어줘야함..
                    arrayAdapter.notifyDataSetChanged();
                    break;
                case 9:
                    break;
            }
        }
    };

    private void parseJSON(){
        try{
            String urlStr = "http://api.androidhive.info/contacts/";
            URL url = new URL(urlStr);
            InputStream in = url.openStream();
            InputStreamReader isR = new InputStreamReader(in);
            BufferedReader br = new BufferedReader(isR);

            StringBuffer sb = new StringBuffer();
            while(true){
                String readLine = br.readLine();
                if(readLine == null){
                    break;
                }
                sb.append(readLine+"\n");
            }
            //Log.e("data", sb.toString());
            JSONObject jsonObject = new JSONObject(sb.toString());
            JSONArray jsonArray = jsonObject.getJSONArray("contacts");
            ArrayList<String> contactArrayList=new ArrayList<String>();
            for (int i=0;i<jsonArray.length();i++){
                JSONObject contactsJO = jsonArray.getJSONObject(i);
                String id=contactsJO.getString("id");
                String name=contactsJO.getString("name");
                String email=contactsJO.getString("email");
                String address=contactsJO.getString("address");
                String gender=contactsJO.getString("gender");
                JSONObject phoneJO=contactsJO.getJSONObject("phone");
                String mobile=phoneJO.getString("mobile");
                String home=phoneJO.getString("home");
                String office=phoneJO.getString("office");

                String contactStr=id+","+name+","+email+","+address+","+
                        gender+","+mobile+","+","+home+","+office;
                Log.e("contactStr", contactStr.toString());
                contactArrayList.add(contactStr);

                Message msg=new Message();
                msg.what=0;
                msg.obj=contactStr;
                handler.sendMessage(msg);
            }//end for

            Message msg=new Message();
            msg.what=1;
            msg.obj=contactArrayList;
            //handler.sendMessage(msg);

        }catch(Exception e){
            e.printStackTrace();
            Message msg=new Message();
            msg.what=9;
            msg.obj=e;
            handler.sendMessage(msg);
        }
    }
}
