package com.itwill.menu;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater mi= getMenuInflater();
        mi.inflate(R.menu.main_menu,menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.email_menu:
                Toast.makeText(getApplicationContext(),"email",Toast.LENGTH_SHORT).show();
                break;
            case R.id.map_menu:
                Toast.makeText(getApplicationContext(),"map",Toast.LENGTH_SHORT).show();
                break;
            case R.id.sms_menu:
                Toast.makeText(getApplicationContext(),"sms",Toast.LENGTH_SHORT).show();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void closeOptionsMenu() {
        super.closeOptionsMenu();
    }

    @Override
    public void openOptionsMenu() {
        super.openOptionsMenu();
    }
}
