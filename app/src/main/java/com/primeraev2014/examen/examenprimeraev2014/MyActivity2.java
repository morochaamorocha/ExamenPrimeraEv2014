package com.primeraev2014.examen.examenprimeraev2014;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.ContextWrapper;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Xml;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlSerializer;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;


public class MyActivity2 extends Activity {

    static ArrayList<Datos> alumnos;
    static ArrayList<Bitmap> datos;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getActionBar().setHomeButtonEnabled(true);
        getActionBar().setIcon(R.drawable.icon_activity);

        mostrarToast();

        alumnos=new ArrayList<Datos>();
        datos=new ArrayList<Bitmap>();
        setContentView(R.layout.activity_my2);

       try {
           LeerXml("alumnos.xml");
          crearArrayImagenes();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        }

    }

    static  void crearArrayImagenes() {

        for(Datos x: MyActivity2.alumnos) {
            datos.add(x.foto);
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.my2, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){
            case R.id.item_menu_guardar:
                escribirXML("alumnos.xml");
                return true;
            case R.id.item_menu_salir:
                Intent intent = new Intent(getApplicationContext(), MyActivity.class);
                startActivity(intent);
                finish();
                return true;
            case R.id.item_men_horarios:
                Toast.makeText(getApplicationContext(), "Esta opción aun no ha sido implementada.", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.item_menu_alumnos:
                añadirFragmenLista();
                return true;
            case R.id.item_submenu1_asc:

                return true;
            case R.id.item_submenu1_desc:

                return true;
            case R.id.item_submenu2_asc:

                return true;
            case R.id.item_submenu2_desc:

                return true;
            case android.R.id.home:
                try {
                    LeerXml("alumnossegur.xml");
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (XmlPullParserException e) {
                    e.printStackTrace();
                }
                escribirXML("alumnos.xml");
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);

        }
    }

    void LeerXml(String fichero) throws FileNotFoundException, XmlPullParserException {
        XmlPullParser parser = Xml.newPullParser();
        FileInputStream fin = null;
        String nombre = "";
        String foto = null;
        ContextWrapper cw;
        ArrayList<Modulos> notas = new ArrayList<Modulos>();
        Modulos modulo;
        Bitmap imagen = null;

        try {
            fin = openFileInput(fichero);
            parser.setInput(fin,null);
            int evento = parser.getEventType();


            while (evento != XmlPullParser.END_DOCUMENT)
            {
                switch (evento) {
                    case XmlPullParser.START_TAG:
                        if (parser.getName().equals("nombre")) {

                            nombre = parser.nextText();

                        } else if (parser.getName().equals("uri")){

                            foto = parser.nextText();
                            cw = new ContextWrapper(getBaseContext());
                            String x = "/data/data/" + cw.getPackageName().toString() + "/" + "imagenes" + "/" + foto;
                            imagen = BitmapFactory.decodeFile(x);

                       }
                        else if (parser.getName().equals("modulo")){

                            int aux = Integer.parseInt(parser.getAttributeValue(parser.getNamespace(), "nota"));
                            notas.add(new Modulos(parser.nextText(), aux));
                        }
                        break;
                    case  XmlPullParser.END_TAG:
                        if(parser.getName().equals("alumno")) {

                            Datos alumno = new Datos(nombre, notas, imagen, foto);

                            if(alumno.nombre != null){
                                alumnos.add(alumno);
                            }
                        }

                }
                evento = parser.next();
            }
            fin.close();
        } catch (XmlPullParserException e) { e.printStackTrace();}

        catch (FileNotFoundException e) { e.printStackTrace(); }
        catch (IOException e) { e.printStackTrace(); }
    }

    void escribirXML(String url){

        FileOutputStream fileOutputStream = null;
        XmlSerializer serializer = Xml.newSerializer();


            try {
                fileOutputStream = openFileOutput(url, MODE_PRIVATE);
                serializer.setOutput(fileOutputStream, "UTF-8");
                serializer.startDocument(null, true);
                serializer.setFeature("http://xmlpull.org./v1/doc/features.html#ident-output", true);

                //Tag grupo
                serializer.startTag(null, "grupo");

                for (Datos x : alumnos) {
                    //Tag alumno
                    serializer.startTag(null, "alumno");

                    //Tag nombre
                    serializer.startTag(null, "nombre");
                    serializer.text(x.getNombre());
                    serializer.endTag(null, "nombre");

                    //Tag notas
                    serializer.startTag(null, "notas");

                    for (Modulos m : x.notasModulo) {

                        //Tag modulo
                        serializer.startTag(null, "modulo");
                        serializer.attribute(null, "nota", m.getNota());
                        serializer.text(m.getNombreModulo());
                        serializer.endTag(null, "modulo");
                    }

                    serializer.endTag(null, "notas");
                    serializer.endTag(null, "alumno");
                }

                serializer.endTag(null, "grupo");
                serializer.endDocument();
                serializer.flush();
                fileOutputStream.close();

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

    }
    private void mostrarToast(){

        Toast mToast = new Toast(getApplicationContext());

        LayoutInflater inflater = getLayoutInflater();
        View view = inflater.inflate(R.layout.m_toast, (ViewGroup)findViewById(R.id.container));

        TextView txtToast = (TextView)view.findViewById(R.id.lbl_toast);
        txtToast.setText("Bienvenido al sistema!! Seleccione la opción correspondiente del menú");

        mToast.setDuration(Toast.LENGTH_SHORT);
        mToast.setGravity(Gravity.CENTER,0,0);
        mToast.setView(view);
        mToast.show();
    }


    private void añadirFragmenLista(){

        FragmentManager manager = getFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        FragmentLista fragmentLista = new FragmentLista();
        transaction.add(R.id.contenedor2, fragmentLista);
        transaction.commit();
    }
}
