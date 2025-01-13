package com.example.DTO.DTOEmpleado;

public class DTOEmpleado {

    private String Cedula;
    private String Nombres_Completos;
    private String Estado;

    public String getCedula() {
        return Cedula;
    }

    public void setCedula(String cedula) {
        Cedula = cedula;
    }

    public String getNombres_Completos() {
        return Nombres_Completos;
    }

    public void setNombres_Completos(String nombres_Completos) {
        Nombres_Completos = nombres_Completos;
    }

    public String getEstado() {
        return Estado;
    }

    public void setEstado(String estado) {
        Estado = estado;
    }
}
