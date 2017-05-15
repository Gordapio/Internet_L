/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package backingbean;

import java.io.Serializable;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Objects;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import javax.inject.Inject;
import jpa.Evento;
import jpa.Evento.tipoEvento;
import jpa.Valoracion;

/**
 *
 * @author gordo
 */
@Named(value = "ec")
@ApplicationScoped
public class EventoControlador{
    
    @Inject
    BaseDatosFicticia bd;    

    
    //evento seleccionado para mostrar
    private Evento eselected;

    public Evento getEselected() {
        return eselected;
    }

    public void setEselected(Evento eselect) {
        this.eselected = eselect;
    }

    public String seleccionaEvento(Evento e){
        eselected=e;
        return "evento.xhtml";
    }
    
    
    //Atributos para el filtro
    private boolean factivado;//filtro activado
    private String f_nombre;
    private String f_localidad;
    private String f_tipo;
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
    
    public ArrayList<String> getLocalidades(){
        ArrayList<String> local = new ArrayList<>();
       for (Evento e : bd.getEventos())
            local.add(e.getLocalidad());        
        return local;
    }  
    
    public ArrayList<String> getLocalidadesNoProp(){
        ArrayList<String> local = new ArrayList<>();
       for (Evento e : bd.getEventos())
           if(!e.getPropuesto() && e.getVisible())
            local.add(e.getLocalidad());        
        return local;
    }    
    
    public ArrayList<Date> getFechasNoProp(){
        ArrayList<Date> date = new ArrayList<>();
       for (Evento e : bd.getEventos())
           if(!e.getPropuesto() && e.getVisible())
            date.add(e.getFecha_inicio());        
        return date;
    }    
    
    /**
     * Creates a new instance of EventoControlador
     */
    public EventoControlador(){
        factivado=false;
    }
    
    /*Devuelve todos los eventos ordenados en el siguiente orden
        Destacados
        Mayor número likes
        Fecha
    
      Descarta los eventos pasados*/
    public ArrayList<Evento> verEventos(){
        ArrayList<Evento> eventos = new ArrayList<>();
        for (Evento e : bd.getEventos())
            if (e.getVisible() && !e.getPropuesto()){
                eventos.add(e);
            }
        return eventos;
        
    }
    
    public ArrayList<Evento> eventosPropuestos(){
        ArrayList<Evento> propuestos = new ArrayList<>();
        for (Evento e : bd.getEventos())
            if (e.getVisible() && e.getPropuesto()){
                propuestos.add(e);
            }
        return propuestos;
    }
    
    
    /*Crea un evento con las características proporcionadas. El evento, antes de
    publicarse, necesitará ser validado por un periodista*/
    public String crearEvento(String name, int tlf, String org){
        Evento e = new Evento();
        e.setNombre(name);
        e.setTlf_contacto(tlf);
        e.setOrganizador(org);
        bd.addEvento(e);
        return null;// dependiendo de si está identificado largamos a una pagina u otra
    }
    
    /*Periodista da el visto bueno a un evento para ser publicado*/
    public String validarEvento(long id){
        int i=0;
        boolean updated=false;
        while (!updated){
            if (bd.getEventos().get(i).getId_evento() == id){
                bd.getEventos().get(i).setVisible(true);
                bd.getEventos().get(i).setPropuesto(false);
                updated=true;
            }else i++;
        }
        
        return "proponer-evento.xhtml";
    }
    
    public String validarEvento(){
        
        eselected.setPropuesto(false);
        eselected.setVisible(true);
        
        return "proponer-evento.xhtml";
    }
    
    /*Periodista modifica las características de un evento. NECESITAMOS UNA 
    ESTRUCTURA PARA RECOGER LOS DATOS DE UN EVENTO. Misma clase evento?*/
    public String modificarEvento(long id, String nombre){
        int i=0;
        boolean updated=false;
        while (!updated){
            if (Objects.equals(bd.getEventos().get(i).getId_evento(), id)){
                bd.getEventos().get(i).setNombre(nombre);
                updated=true;
            }else i++;
        }
        
        return null;
    }

    /*Periodista elimina un evento de la agenda (no visible?)*/
    public String eliminarEvento(long id){
        int i=0;
        boolean updated=false;
        while (!updated){
            if (Objects.equals(bd.getEventos().get(i).getId_evento(), id)){
                bd.getEventos().get(i).setVisible(false);
                updated=true;
            }else i++;
        }
        return "index.xhtml";
    }
    
    public String eliminarEventoPropuesto(){
        
        bd.getEventos().remove(eselected);
        return "proponer-evento.xhtml";
        
    }
    
    public String modificarEv(){
        return null;
    }

    public String destacarEvento(Long id){
        int i=0;
        boolean updated=false;
        while (!updated){
            if (Objects.equals(bd.getEventos().get(i).getId_evento(), id)){
                bd.getEventos().get(i).setDestacado(true);
                updated=true;
            }else i++;
        }
        return null;
    }

    // ORDENAR
     /*Devuelve solo los eventos que se ajusten al filtro ordenados de forma habitual*/
    public ArrayList<Evento> filtrarEventos(String n, String l, tipoEvento t, Date f, double p){
        ArrayList<Evento> filtrados = new ArrayList<Evento>();
        
        for (Evento e : bd.getEventos())
            if (n!=null)
                if (n.equals(e.getNombre()))
                    filtrados.add(e);
       
        return ordenarEventos(filtrados); //cómo juntamos esto con la página?
        // devuelve la página y la otra página llama a esto?
    }
    
    private ArrayList<Evento> soloVisibles(ArrayList<Evento> des){
        ArrayList<Evento> ord = new ArrayList<>();
        
        for (Evento e : des)
            if (e.getVisible())
                ord.add(e);                
        
        return ord;
    }
    
    private ArrayList<Evento> soloDestacados(ArrayList<Evento> des){
        ArrayList<Evento> ord = new ArrayList<>();
       
        for (Evento e : des)
            if (e.getDestacado())
                ord.add(e);                
        
        return ord;
    }
    
    private ArrayList<Evento> soloLikes(ArrayList<Evento> des){
        ArrayList<Evento> ord = new ArrayList<>();
        
        for (Evento e : des)
            if (!e.getDestacado() && e.getLikes().size()>0)
                ord.add(e);                
        
        return ord;
    }
    
    private ArrayList<Evento> soloFechas(ArrayList<Evento> des){
        ArrayList<Evento> ord = new ArrayList<>();
        
        for (Evento e : des)
            if (!e.getDestacado() && e.getLikes().size()<=0)
                ord.add(e);                
        
        return ord;
    }
    
    private ArrayList<Evento> ordenaFecha(ArrayList<Evento> des){
         Collections.sort(des, new Comparator<Evento>(){
            @Override
            public int compare(Evento ev1, Evento ev2){
                if (ev1.getFecha_inicio().before(ev2.getFecha_inicio()))
                    return -1;
                else if (ev1.getFecha_inicio().after(ev2.getFecha_inicio()))
                    return 1;
                else
                    return 0;
            }
        });
                
        return des;
    }
    
    private ArrayList<Evento> ordenaLike(ArrayList<Evento> des){
        
        Collections.sort(des, new Comparator<Evento>(){
            @Override
            public int compare(Evento ev1, Evento ev2){
                if (ev1.getLikes().size()>ev2.getLikes().size())
                    return -1;
                else if (ev1.getLikes().size()<ev2.getLikes().size())
                    return 1;
                else
                    return 0;
            }
        });
                
        return des;
    }
    
    private ArrayList<Evento> ordenaDestacado(ArrayList<Evento> des){
        return ordenaLike(des);
    }

    /*Para ordenar eventos*/
    public ArrayList<Evento> ordenarEventos(ArrayList<Evento> des){
        ArrayList<Evento> ord = new ArrayList<>();
        ArrayList<Evento> destacados,masLikes,recientes; // Estos arrays son mutuamente excluyentes, luego se concatenan
        
        //ord = soloVisibles(ord); //Solo los visibles
        //Hace falta clonar?? Creo no, solo referencia
        destacados=soloDestacados(des);
        masLikes=soloLikes(des);
        recientes=soloFechas(des);
        
        destacados = ordenaDestacado(destacados); //Devuelve los destacados ya ordenados
        masLikes = ordenaLike(masLikes); //Devuelve los que tienen mas likes ya ordenados
        recientes = ordenaFecha(recientes); //Devuelve los más recientes
        
        for (Evento e : destacados)
            ord.add(e);
        for (Evento e : masLikes)
            ord.add(e);
        for (Evento e : recientes)
            ord.add(e);

        return ord;    
    }
    
    
    
}
