package com.example.app_14;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.view.Menu;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private ListView obj;
    DBHelper mydb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mydb=new DBHelper(this);
        ArrayList al=mydb.getAllContacts();
        ArrayAdapter ald=new ArrayAdapter(this, android.R.layout.simple_list_item_1,al);
        obj=(ListView)findViewById(R.id.Lisview1);
        obj.setAdapter(ald);
        obj.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                int id_t_s=i;
                Bundle databd=new Bundle();
                databd.putInt("id",id_t_s);
                Intent it=new Intent(getApplicationContext(),com.example.app_14.DisplayContact.class);
                it.putExtras(databd);
                startActivity(it);
            }
        });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.main,menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        super.onOptionsItemSelected(item);
        switch (item.getItemId()){
            case R.id.itmAdd:
                Bundle databundle=new Bundle();
                databundle.putInt("id",-1);
                Intent ite=new Intent(getApplicationContext(),com.example.app_14.DisplayContact.class);
                ite.putExtras(databundle);
                startActivity(ite);
                return true;
            default:return super.onOptionsItemSelected(item);
        }
    }

    public boolean onKeyDown(int keycode, KeyEvent event){
        if(keycode==KeyEvent.KEYCODE_BACK){
            moveTaskToBack(true);
        }
        return  super.onKeyDown(keycode,event);
    }
}