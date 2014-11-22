package com.primeraev2014.examen.examenprimeraev2014;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;

import java.util.ArrayList;


class Modulos {
   String nombreModulo;
   int nota;

    Modulos(){}
    Modulos(String nombreModulo, int nota) {
        this.nombreModulo = nombreModulo;
        this.nota = nota;
    }

    @Override
    public String toString() {
        return "\n"+nombreModulo +" "+ nota;
    }
}
public class Datos implements Comparable {

    String nombre;
    ArrayList<Modulos> notasModulo;
    String uri;
    Bitmap foto;

    public  Datos(){}

    public Datos(String nombre, ArrayList notasModulo) {
        this.nombre = nombre;
        this.notasModulo = notasModulo;
    }

    public Datos(String nombre, Bitmap foto, String uri) {
        this.nombre = nombre;
        this.foto = foto;
        this.uri=uri;
    }
    public Datos(String nombre, String uri) {
        this.nombre = nombre;
        this.uri=uri;
    }

    public Datos(String nombre, ArrayList notasModulo, Bitmap foto, String uri) {
        this.nombre = nombre;
        this.notasModulo = notasModulo;
        this.foto = foto;
        this.uri=uri;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public ArrayList<Modulos> getNotasModulo() {
        return notasModulo;
    }

    public void setNotasModulo(ArrayList<Modulos> notasModulo) {
        this.notasModulo = notasModulo;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public Bitmap getFoto() {
        return foto;
    }

    public void setFoto(Bitmap foto) {
        this.foto = foto;
    }

    @Override
    public String toString() {
        String aux="";
        for (Modulos x: notasModulo) aux+=x.toString();

        return "\n\n"+nombre + aux;
    }

    @Override
    public int compareTo(Object o) {
        Datos a=(Datos)o;
        if(this.nombre==a.nombre) return 0;
        else if(nombre.compareTo(a.nombre)>0)return 1;
        else  return -1;
    }
}
