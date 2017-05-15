/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package backingbean;

import java.sql.Date;
import java.util.ArrayList;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import jpa.Evento;
import jpa.Evento.tipoEvento;

/**
 *
 * @author gordo
 */
@Named(value = "be")
@RequestScoped
public class BusquedaEvento {

    @Inject
    BaseDatosFicticia bd;
    @Inject
    EventoControlador ec;
    
    //Atributos para el filtro
    private boolean factivado;//filtro activado
    private String f_nombre;
    private String f_localidad;
    private String f_tipo; //en un switch se arregla
    private Date f_fecha;

    public boolean isFactivado() {
        return factivado;
    }

    public void setFactivado(boolean factivado) {
        this.factivado = factivado;
    }

    public String getF_nombre() {
        return f_nombre;
    }

    public void setF_nombre(String f_nombre) {
        this.f_nombre = f_nombre;
    }

    public String getF_localidad() {
        return f_localidad;
    }

    public void setF_localidad(String f_localidad) {
        this.f_localidad = f_localidad;
    }

    public String getF_tipo() {
        return f_tipo;
    }

    public void setF_tipo(String f_tipo) {
        this.f_tipo = f_tipo;
    }

    public Date getF_fecha() {
        return f_fecha;
    }

    public void setF_fecha(Date f_fecha) {
        this.f_fecha = f_fecha;
    }

    public BusquedaEvento() {
    }
    
    private boolean filtraNombre(Evento e){
        if (f_nombre==null || f_nombre.equals(""))//""
            return true;
        else
            return f_nombre.equalsIgnoreCase(e.getNombre());
    }

    private boolean filtraLocalidad(Evento e){
        if (f_localidad==null)//""
            return true;
        else
            return f_localidad.equalsIgnoreCase(e.getLocalidad());
    }
    
    private boolean filtraFecha(Evento e){
        if (f_fecha==null)//""
            return true;
        else
            return f_fecha.equals(e.getFecha_inicio());
    }
    
    private boolean filtraTipo(Evento e){
        tipoEvento tipo=null;
        
        if (f_tipo==null || f_tipo.equals(""))
            return true;
        else 
            switch (f_tipo){
                case "Social" : tipo=tipoEvento.social; break;
                case "Cultural" : tipo=tipoEvento.cultural; break;
                case "Empresarial" : tipo=tipoEvento.empresarial; break;
                case "Otro" : tipo=tipoEvento.otro; break;
            }
        if (tipo!=null)
            return tipo.equals(e.getTe());
        else return false;
    }
    
    public ArrayList<Evento> filtraEventos(){
        ArrayList<Evento> buscados = new ArrayList<>();
        
        for (Evento e : bd.getEventos()){
            if (e.getVisible() 
                    && filtraNombre(e) && filtraLocalidad(e)
                    && filtraFecha(e) && filtraTipo(e))
                buscados.add(e);
        }
        
        return ec.ordenarEventos(buscados);
    }
    
    public String buscarEventos(){
        return "faces/busqueda.xhtml";
    }
    
}
