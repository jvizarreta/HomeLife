package com.proyectofinal.homelife.network.services;

import android.content.Context;
import android.widget.Toast;

import com.proyectofinal.homelife.network.HomeLifePreference;
import com.proyectofinal.homelife.network.ApiServiceSingleton;

public class Base {
    public HomeLifePreference mHomeLifePreference;
    protected ApiServiceSingleton client;
    protected Context context;

    public String URL_BASE = "https://apiprueba.aios.pe";

    public String URL_REGISTRO = URL_BASE + "/users/register";

    public String URL_AUTH_LOGIN = URL_BASE + "/auth/token/auth";
    public String URL_AUTH_VERIFY = URL_BASE + "/auth/token/auth/verify";

    public String URL_AMBIENTES_LIST = URL_BASE + "/orders/ambiente";
    public String URL_RESERVA = URL_BASE + "/orders/reserva";
    public String URL_PAGO = URL_BASE + "/orders/pago";
    public String URL_MOVIMIENTOS = URL_BASE + "/orders/movimientos";

    /*
    public String URL_ADDRESS_LIST = URL_BASE + "/users/address";



    public String URL_REGISTER_ADDRESS = URL_BASE + "/users/address";

    public String URL_CARD = URL_BASE + "/users/card";


    public String URL_ORDER_PRODUCT = URL_BASE + "/orders/product";
     */

    public Base(Context context) {
        this.context = context;
        this.client = client.getInstance(context);
        this.mHomeLifePreference = new HomeLifePreference(context);

    }
}
