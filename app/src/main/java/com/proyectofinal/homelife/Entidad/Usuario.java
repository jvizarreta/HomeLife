package com.proyectofinal.homelife.Entidad;
import android.os.Parcel;
import android.os.Parcelable;
public class Usuario implements Parcelable {

    private int	id;
    private String nrodocumento;
    private String nombres;
    private String apellidos;
    private int celular;
    private String departamento;
    private String password;
    private String passwordconfirmacion;
    public Usuario() {

    }

    public Usuario(int	id,String nrodocumento,String nombres,String apellidos,int celular,String departamento,String password,String passwordconfirmacion) {
        this.id=id;
        this.nrodocumento=nrodocumento;
        this.nombres=nombres;
        this.apellidos=apellidos;
        this.celular=celular;
        this.departamento=departamento;
        this.password=password;
        this.passwordconfirmacion=passwordconfirmacion;
    }

    public Usuario(String nrodocumento,String nombres,String apellidos,int celular,String departamento,String password,String passwordconfirmacion) {
        this.nrodocumento=nrodocumento;
        this.nombres=nombres;
        this.apellidos=apellidos;
        this.celular=celular;
        this.departamento=departamento;
        this.password=password;
        this.passwordconfirmacion=passwordconfirmacion;
    }


    protected Usuario(Parcel in) {
        id = in.readInt();
        nrodocumento = in.readString();
        nombres = in.readString();
        apellidos = in.readString();
        celular = in.readInt();
        departamento = in.readString();
        password = in.readString();
        passwordconfirmacion = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(nrodocumento);
        dest.writeString(nombres);
        dest.writeString(apellidos);
        dest.writeInt(celular);
        dest.writeString(departamento);
        dest.writeString(password);
        dest.writeString(passwordconfirmacion);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Usuario> CREATOR = new Creator<Usuario>() {
        @Override
        public Usuario createFromParcel(Parcel in) {
            return new Usuario(in);
        }

        @Override
        public Usuario[] newArray(int size) {
            return new Usuario[size];
        }
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNrodocumento() {
        return nrodocumento;
    }

    public void setNrodocumento(String nrodocumento) {
        this.nrodocumento = nrodocumento;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public int getCelular() {
        return celular;
    }

    public void setCelular(int celular) {
        this.celular = celular;
    }

    public String getDepartamento() {
        return departamento;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPasswordconfirmacion() {
        return passwordconfirmacion;
    }

    public void setPasswordconfirmacion(String passwordconfirmacion) {
        this.passwordconfirmacion = passwordconfirmacion;
    }





}
