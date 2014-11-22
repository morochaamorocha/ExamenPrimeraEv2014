package com.primeraev2014.examen.examenprimeraev2014;

import android.content.Context;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by rals1_000 on 21/11/2014.
 */
public class MiControlLogin extends LinearLayout {


    private EditText txtUsuario, txtContrasena;
    private Button btnLogin;
    private TextView lblMensaje;

    private OnLoginListener listener;

    public MiControlLogin(Context context) {
        super(context);
        inicializar();
    }

    public MiControlLogin(Context context, AttributeSet attrs) {
        super(context, attrs);
        inicializar();
    }

    private void inicializar(){

        String infService = Context.LAYOUT_INFLATER_SERVICE;

        LayoutInflater inflater = (LayoutInflater)getContext().getSystemService(infService);
        inflater.inflate(R.layout.m_ctrl_login, this, true);

        txtUsuario = (EditText)findViewById(R.id.txt_usuario);
        txtContrasena = (EditText)findViewById(R.id.txt_contrasena);
        btnLogin = (Button)findViewById(R.id.btn_login);
        lblMensaje = (TextView)findViewById(R.id.lbl_mensaje);

        asignarEventos();
    }

    public void setOnLoginListener(OnLoginListener l)
    {
        listener = l;
    }

    private void asignarEventos()
    {
        btnLogin.setOnClickListener(new OnClickListener()
        {
            public void onClick(View v) {

                listener.onLogin(txtUsuario.getText().toString(), txtContrasena.getText().toString());
            }
        });
    }

    public void setMensaje(String msg)
    {
        lblMensaje.setText(msg);
    }

}
