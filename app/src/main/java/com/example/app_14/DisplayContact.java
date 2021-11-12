package com.example.app_14;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class DisplayContact extends AppCompatActivity {
    private DBHelper mydb;
    TextView name;
    TextView phone;
    TextView email;
    TextView place;
    TextView street;
    int id_To_Update=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.display_contact);
        name =(TextView) findViewById(R.id.etxName);
        phone =(TextView) findViewById(R.id.etxPhone);
        email =(TextView) findViewById(R.id.etxEmail);
        place =(TextView) findViewById(R.id.etxCtee);
        street =(TextView) findViewById(R.id.etxStreet);
        mydb=new DBHelper(this);
        Bundle extras=getIntent().getExtras();
        int Value = extras.getInt("id");
        if(Value!=-1){
            if(Value>0){
                Cursor rs=mydb.getData(Value);
                id_To_Update=Value;
                rs.moveToFirst();
                String namee=rs.getString((int)rs.getColumnIndex("name"));
                String phonee=rs.getString((int)rs.getColumnIndex("phone"));
                String emaill=rs.getString((int)rs.getColumnIndex("email"));
                String streett=rs.getString((int)rs.getColumnIndex("street"));
                String cteee=rs.getString((int)rs.getColumnIndex("place"));
                if(!rs.isClosed()){
                    rs.close();
                }
                Button b=(Button) findViewById(R.id.btnSave);
                b.setVisibility(View.INVISIBLE);
                name.setText((CharSequence) namee);
                name.setFocusable(false);
                name.setClickable(false);

                phone.setText((CharSequence) phonee);
                phone.setFocusable(false);
                phone.setClickable(false);

                email.setText((CharSequence) emaill);
                email.setFocusable(false);
                email.setClickable(false);

                street.setText((CharSequence) streett);
                street.setFocusable(false);
                street.setClickable(false);

                place.setText((CharSequence) cteee);
                place.setFocusable(false);
                place.setClickable(false);
            }
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.display_contact_menu,menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        super.onOptionsItemSelected(item);
        switch (item.getItemId()){
            case R.id.itmEdit:
                Button b=(Button) findViewById(R.id.btnSave);
                b.setVisibility(View.VISIBLE);
                name.setEnabled(true);
                name.setFocusableInTouchMode(true);
                name.setClickable(true);

                phone.setEnabled(true);
                phone.setFocusableInTouchMode(true);
                phone.setClickable(true);

                email.setEnabled(true);
                email.setFocusableInTouchMode(true);
                email.setClickable(true);

                street.setEnabled(true);
                street.setFocusableInTouchMode(true);
                street.setClickable(true);

                place.setEnabled(true);
                place.setFocusableInTouchMode(true);
                place.setClickable(true);
                return true;
            case R.id.itmDelete:
                AlertDialog.Builder builder=new AlertDialog.Builder(this);
                builder.setMessage("Xóa thiệt luôn?").setPositiveButton("Ừ", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int id) {
                        mydb.deleteContact(id_To_Update);
                        Toast.makeText(getApplicationContext(),"Xóa thàn công",Toast.LENGTH_SHORT).show();
                        Intent i=new Intent(getApplicationContext(),com.example.app_14.MainActivity.class);
                        startActivity(i);
                    }
                }).setNegativeButton("à không", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
                AlertDialog d = builder.create();
                d.setTitle("Xác nhận xóa");
                d.show();
                return true;
            default:return super.onOptionsItemSelected(item);
        }
    }
    public void run(View view){
        Bundle extras=getIntent().getExtras();
        int Value=extras.getInt("id");
        if(Value!=-1){
            if(Value>0){
                if(mydb.updateContact(id_To_Update,name.getText().toString(),phone.getText().toString(),email.getText().toString(),street.getText().toString(),place.getText().toString())){
                    Toast.makeText(getApplicationContext(),"Cập nhật thành công",Toast.LENGTH_SHORT).show();
                    Intent i=new Intent(getApplicationContext(),com.example.app_14.MainActivity.class);
                    startActivity(i);
                }
            }else{
                Toast.makeText(getApplicationContext(),"Cập nhật Không thành công"+extras,Toast.LENGTH_SHORT).show();
            }
        }else{
            if(mydb.insertConatct(name.getText().toString(),phone.getText().toString(),email.getText().toString(),street.getText().toString(),place.getText().toString())){
                Toast.makeText(getApplicationContext(),"Thêm mới thành công",Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(getApplicationContext(),"Thêm mới Không thành công",Toast.LENGTH_SHORT).show();
            }
            Intent i=new Intent(getApplicationContext(),com.example.app_14.MainActivity.class);
            startActivity(i);
        }
    }
}