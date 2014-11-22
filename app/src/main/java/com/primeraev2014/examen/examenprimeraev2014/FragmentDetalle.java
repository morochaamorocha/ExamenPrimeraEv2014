package com.primeraev2014.examen.examenprimeraev2014;

import android.app.Activity;
import android.app.ListFragment;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;


public class FragmentDetalle extends ListFragment {


    private static final String ARG_PARAM1 = "param1";

    private OnFragmentInteractionListener mListener;

    private int alumno;


    public FragmentDetalle() {
    }

    public static FragmentDetalle newInstance(int alumno){

        FragmentDetalle fragmentDetalle = new FragmentDetalle();
        Bundle bundle = new Bundle();
        bundle.putInt(ARG_PARAM1, alumno);
        fragmentDetalle.setArguments(bundle);
        return fragmentDetalle;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null){
            alumno = getArguments().getInt(ARG_PARAM1);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_modulo, container, false);

        setListAdapter(new MiAdapter(MyActivity2.alumnos));


        TextView lblNombreAlumno = (TextView)view.findViewById(R.id.lbl_nombre_alumno);
        lblNombreAlumno.setText(MyActivity2.alumnos.get(alumno).getNombre());

        registerForContextMenu(lblNombreAlumno);
        return view;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (OnFragmentInteractionListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
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
        }
        return super.onContextItemSelected(item);
    }

    public void setEmptyText(CharSequence emptyText) {
        View emptyView = getListView().getEmptyView();

        if (emptyView instanceof TextView) {
            ((TextView) emptyView).setText(emptyText);
        }
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        public void onFragmentInteraction(String id);
    }

    class MiAdapter extends ArrayAdapter<Datos>{

        public MiAdapter(ArrayList<Datos> objects) {
            super(getActivity(), R.layout.adapter_modulos, objects);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            ViewHolder holder;

            if (convertView == null){
                LayoutInflater inflater = LayoutInflater.from(getContext());
                convertView = inflater.inflate(R.layout.adapter_modulos, null);

                holder = new ViewHolder();
                holder.lblModulo = (TextView)convertView.findViewById(R.id.lbl_modulo);
                holder.lblNota = (TextView)convertView.findViewById(R.id.lbl_nota);

                convertView.setTag(holder);
            }
            else {
                holder = (ViewHolder)convertView.getTag();
            }

            holder.lblModulo.setText(MyActivity2.alumnos.get(alumno).notasModulo.get(position).nombreModulo);
            holder.lblNota.setText(MyActivity2.alumnos.get(alumno).notasModulo.get(position).nota);


            return convertView;
        }
    }

    static class ViewHolder{

        TextView lblModulo, lblNota;
    }
}
