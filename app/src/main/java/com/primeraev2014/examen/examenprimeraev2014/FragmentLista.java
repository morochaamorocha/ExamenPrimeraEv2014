package com.primeraev2014.examen.examenprimeraev2014;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;


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
        FragmentDetalle fragmentDetalle = FragmentDetalle.newInstance(p);
        fragmentDetalle.setEmptyText("Seleccione un alumno");
        transaction.add(R.id.contenedor1, fragmentDetalle);
        transaction.commit();
    }

    public void ordenarAsc(){
        Collections.sort(MyActivity2.datos, new Sorter(-1));
        adapter.notifyDataSetChanged();
    }

    public void ordenarDesc(){
        Collections.sort(MyActivity2.datos, new Sorter(1));
        adapter.notifyDataSetChanged();
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

    static class Sorter implements Comparator<Object> {
        int order=-1;
        Sorter(int order){
            this.order=order;
        }

        public int compare(Object ob1,Object ob2){
            if(ob1.toString().compareTo(ob2.toString())==0) return 0;
            else if(ob1.toString().compareTo(ob2.toString())<0)
                return order;
            else
                return(-1*order);
        }

    }

}
