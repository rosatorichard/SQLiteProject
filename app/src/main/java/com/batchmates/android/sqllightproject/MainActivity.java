package com.batchmates.android.sqllightproject;

import android.content.Intent;
import android.graphics.Bitmap;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import java.io.ByteArrayOutputStream;

public class MainActivity extends AppCompatActivity {

    private static final int REQUEST_IMAGE_CAPTURE = 1;
    private EditText etName,etPhone,etBirthday;
    private ImageView imageView;
    private byte[] picture;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        etName=(EditText)findViewById(R.id.etName);
        etPhone=(EditText)findViewById(R.id.etPhone);
        etBirthday=(EditText)findViewById(R.id.etBirthday);
        imageView=(ImageView)findViewById(R.id.imgvPicture);

    }


    public void commitContact(View view) {

        Person person=new Person(etName.getText().toString(),etPhone.getText().toString(),etBirthday.getText().toString(),picture);

        SQLlightHandler handler= new SQLlightHandler(this);

        handler.saveContact(person);

    }

    public void showContacts(View view) {

        SQLlightHandler handler= new SQLlightHandler(this);

        handler.displayContacts();
    }

    public void deleteContact(View view) {

        Person person=new Person(etName.getText().toString(),etPhone.getText().toString(),etBirthday.getText().toString(),picture);

        SQLlightHandler handler= new SQLlightHandler(this);
        handler.deleteContact(person);


    }

    public void updateContact(View view) {

        Person person=new Person(etName.getText().toString(),etPhone.getText().toString(),etBirthday.getText().toString(),picture);

        SQLlightHandler handler= new SQLlightHandler(this);
        handler.updateContact(person);
    }

    public void takePicture(View view) {

        Intent intent=new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent,REQUEST_IMAGE_CAPTURE);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            imageView.setImageBitmap(imageBitmap);

            picture=getBytes(imageBitmap);
            Log.d(""+picture, "onActivityResult: Picture Value");
        }

    }

    public static byte[] getBytes(Bitmap bitmap) {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 0, stream);
        return stream.toByteArray();
    }

}
