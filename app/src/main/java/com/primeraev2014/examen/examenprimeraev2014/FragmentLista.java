package com.primeraev2014.examen.examenprimeraev2014;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.app.ListFragment;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridLayout;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;
import java.util.zip.Inflater;


public class FragmentLista extends Fragment implements AdapterView.OnItemClickListener
{

    MyArrayAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view;
        super.onCreateView(inflater, container, savedInstanceState);

        view = inflater.inflate(R.layout.layout_lista, container, false);
        GridView lista = (GridView)view.findViewById(R.id.lista_img);
        adapter = new MyArrayAdapter(getActivity(), R.layout.layout_imagen, MyActivity2.datos);

        lista.setAdapter(adapter);

        registerForContextMenu(lista);

        return view;
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);

        menu.add(Menu.NONE, R.id.item_context1_eliminar, Menu.NONE, "Eliminar");
        menu.add(Menu.NONE, R.id.item_context1_cancel, Menu.NONE, "Cancelar");
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {

        switch (item.getItemId()){
            case R.id.item_context1_eliminar:

                return true;
            default:
                return super.onContextItemSelected(item);
        }
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        añadirFragment(i);
    }

    private void añadirFragment(int p){

        FragmentManager manager = getFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.add(R.id.contenedor1, FragmentDetalle.newInstance(p));
        transaction.commit();
    }

    class  MyArrayAdapter extends  ArrayAdapter
    {
        View vista;
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if(convertView==null){
            LayoutInflater inflador = getActivity().getLayoutInflater();
            vista = inflador.inflate(R.layout.layout_imagen, null);}
            ImageView imagen = (ImageView)vista.findViewById(R.id.imageView);
            imagen.setImageBitmap(MyActivity2.datos.get(position));
            return vista;
        }

        MyArrayAdapter(Context context, int resource, List objects) {
            super(context, resource, objects);


        }
    }


}
