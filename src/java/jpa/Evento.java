/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jpa;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

/**
 *
 * @author User
 */
@Entity
public class Evento implements Serializable {

    public enum tipoEvento{social, cultural, empresarial, otro};
    
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id_evento;
    @Column(nullable = false)
    private String nombre;
    private Date fecha_inicio;
    private Date fecha_fin;
    private Time hora_inicio;
    private Time hora_fin;
    private String descripcion;
    @Column(nullable = false)
    private String organizador;
    @Column(nullable = false)
    private int tlf_contacto;
    private Boolean destacado;
    private String localidad;
    private String direccion;
    private String complejo;
    private double precio;
    @Column(nullable = false)
    private Boolean visible;
    @Column(nullable = false)
    private Boolean propuesto;
    private tipoEvento tevento;
    @OneToMany
    private ArrayList<Valoracion> valoraciones;
    @ManyToMany
    private ArrayList<Usuarioreg> likes;
    
    public Evento(){
        
    }
        
    public void setId_evento(Long id_evento) {
        this.id_evento = id_evento;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setFecha_inicio(Date fecha_inicio) {
        this.fecha_inicio = fecha_inicio;
    }

    public void setFecha_fin(Date fecha_fin) {
        this.fecha_fin = fecha_fin;
    }

    public void setHora_inicio(Time hora_inicio) {
        this.hora_inicio = hora_inicio;
    }

    public void setHora_fin(Time hora_fin) {
        this.hora_fin = hora_fin;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setOrganizador(String organizador) {
        this.organizador = organizador;
    }

    public void setDestacado(Boolean destacado) {
        this.destacado = destacado;
    }

    public void setLocalidad(String localidad) {
        this.localidad = localidad;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public void setComplejo(String complejo) {
        this.complejo = complejo;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public void setVisible(Boolean visible) {
        this.visible = visible;
    }

    public void setPropuesto(Boolean propuesto) {
        this.propuesto = propuesto;
    }

    public void setTe(tipoEvento te) {
        this.tevento = te;
    }

    public void setValoraciones(ArrayList<Valoracion> valoraciones) {
        this.valoraciones = valoraciones;
    }

    public void setLikes(ArrayList<Usuarioreg> likes) {
        this.likes = likes;
    }    
    
    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Long getId_evento() {
        return id_evento;
    }

    public String getNombre() {
        return nombre;
    }

    public Date getFecha_inicio() {
        return fecha_inicio;
    }

    public Date getFecha_fin() {
        return fecha_fin;
    }

    public Time getHora_inicio() {
        return hora_inicio;
    }

    public Time getHora_fin() {
        return hora_fin;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public String getOrganizador() {
        return organizador;
    }

    public Boolean getDestacado() {
        return destacado;
    }

    public String getLocalidad() {
        return localidad;
    }

    public String getDireccion() {
        return direccion;
    }

    public String getComplejo() {
        return complejo;
    }

    public double getPrecio() {
        return precio;
    }

    public Boolean getVisible() {
        return visible;
    }

    public Boolean getPropuesto() {
        return propuesto;
    }

    public tipoEvento getTe() {
        return tevento;
    }

    public ArrayList<Valoracion> getValoraciones() {
        return valoraciones;
    }

    public ArrayList<Usuarioreg> getLikes() {
        return likes;
    }
    
    public void setTlf_contacto(int tlf_contacto) {
        this.tlf_contacto = tlf_contacto;
    }

    public int getTlf_contacto() {
        return tlf_contacto;
    }
    @Override
    public int hashCode() {
        int hash = 3;
        hash = 61 * hash + Objects.hashCode(this.id_evento);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Evento other = (Evento) obj;
        if (!Objects.equals(this.id_evento, other.id_evento)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Evento{" + "id_evento=" + id_evento + '}';
    }

}
