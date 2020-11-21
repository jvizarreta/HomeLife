package com.proyectofinal.homelife.Entidad;

import android.os.Parcel;
import android.os.Parcelable;

import java.time.DateTimeException;
import java.util.Date;

public class Reserva<DateTime> implements Parcelable {
    private int id;
    private int idambiente;
    private DateTime  fecha;
    private int estadoautoriza;
    private int idusuario;

    public Reserva(int	id,int idambiente,DateTime fecha,int estadoautoriza,int idusuario) {
        this.id=id;
        this.idambiente=idambiente;
        this.fecha=fecha;
        this.estadoautoriza=estadoautoriza;
        this.idusuario=idusuario;
    }
    public Reserva(int idambiente,DateTime fecha,int estadoautoriza,int idusuario) {
        this.idambiente=idambiente;
        this.fecha=fecha;
        this.estadoautoriza=estadoautoriza;
        this.idusuario=idusuario;
    }


    protected Reserva(Parcel in) {
        id = in.readInt();
        idambiente = in.readInt();
        estadoautoriza = in.readInt();
        idusuario = in.readInt();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeInt(idambiente);
        dest.writeInt(estadoautoriza);
        dest.writeInt(idusuario);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Reserva> CREATOR = new Creator<Reserva>() {
        @Override
        public Reserva createFromParcel(Parcel in) {
            return new Reserva(in);
        }

        @Override
        public Reserva[] newArray(int size) {
            return new Reserva[size];
        }
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdambiente() {
        return idambiente;
    }

    public void setIdambiente(int idambiente) {
        this.idambiente = idambiente;
    }

    public DateTime getFecha() {
        return fecha;
    }

    public void setFecha(DateTime fecha) {
        this.fecha = fecha;
    }

    public int getEstadoautoriza() {
        return estadoautoriza;
    }

    public void setEstadoautoriza(int estadoautoriza) {
        this.estadoautoriza = estadoautoriza;
    }

    public int getIdusuario() {
        return idusuario;
    }

    public void setIdusuario(int idusuario) {
        this.idusuario = idusuario;
    }
}
