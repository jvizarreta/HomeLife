package com.proyectofinal.homelife.Entidad;

import android.graphics.Bitmap;
import android.os.Parcel;
import android.os.Parcelable;

import java.sql.Blob;

public class Pago implements Parcelable {
    private int id;
    private String concepto;
    private int  numerodeposito;
    private Double   monto;
    private Blob foto;
    private int idusuario;


    public Blob getFoto() {
        return foto;
    }

    public void setFoto(Blob foto) {
        this.foto = foto;
    }




    public Pago(int	id,String concepto,int numerodeposito,double monto,Blob foto,int idusuario ) {
        this.id=id;
        this.concepto=concepto;
        this.numerodeposito=numerodeposito;
        this.monto=monto;
        this.foto=foto;
        this.idusuario=idusuario;
    }

    public Pago(String concepto,int numerodeposito,double monto,Blob foto,int idusuario ) {
        this.id=id;
        this.concepto=concepto;
        this.numerodeposito=numerodeposito;
        this.monto=monto;
        this.foto=foto;
        this.idusuario=idusuario;
    }


    protected Pago(Parcel in) {
        id = in.readInt();
        concepto = in.readString();
        numerodeposito = in.readInt();
        if (in.readByte() == 0) {
            monto = null;
        } else {
            monto = in.readDouble();
        }
        foto = in.readParcelable(Bitmap.class.getClassLoader());
        idusuario = in.readInt();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(concepto);
        dest.writeInt(numerodeposito);
        if (monto == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeDouble(monto);
        }
        dest.writeParcelable((Parcelable) foto, flags);
        dest.writeInt(idusuario);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Pago> CREATOR = new Creator<Pago>() {
        @Override
        public Pago createFromParcel(Parcel in) {
            return new Pago(in);
        }

        @Override
        public Pago[] newArray(int size) {
            return new Pago[size];
        }
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getConcepto() {
        return concepto;
    }

    public void setConcepto(String concepto) {
        this.concepto = concepto;
    }

    public int getNumerodeposito() {
        return numerodeposito;
    }

    public void setNumerodeposito(int numerodeposito) {
        this.numerodeposito = numerodeposito;
    }

    public Double getMonto() {
        return monto;
    }

    public void setMonto(Double monto) {
        this.monto = monto;
    }



    public int getIdusuario() {
        return idusuario;
    }

    public void setIdusuario(int idusuario) {
        this.idusuario = idusuario;
    }

}
