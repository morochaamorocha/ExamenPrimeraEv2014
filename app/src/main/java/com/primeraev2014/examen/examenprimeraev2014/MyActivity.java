package com.primeraev2014.examen.examenprimeraev2014;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Xml;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlSerializer;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;


public class MyActivity extends Activity {


    private MiControlLogin controlLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);

        getActionBar().setIcon(R.drawable.icon_casestudy);

        controlLogin = (MiControlLogin)findViewById(R.id.ctrl_login);
        controlLogin.setOnLoginListener(new OnLoginListener() {
            @Override
            public void onLogin(String usuario, String contrasena) {

                if (usuario.equals("demo") && contrasena.equals("demo")){

                    Intent intent =new Intent(getApplicationContext(), MyActivity2.class);
                    startActivity(intent);
                    finish();
                }
                else {
                    controlLogin.setMensaje("Vuelva a intentarlo.");
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.my, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        return super.onOptionsItemSelected(item);
    }
}
