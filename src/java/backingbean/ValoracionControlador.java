/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package backingbean;

import java.util.ArrayList;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import javax.inject.Inject;

import jpa.Valoracion;

/**
 *
 * @author gordo
 */
@Named(value = "vc")
@RequestScoped

public class ValoracionControlador {

    private String comentario;
    private int puntuacion;

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public int getPuntuacion() {
        return puntuacion;
    }

    public void setPuntuacion(int puntuacion) {
        this.puntuacion = puntuacion;
    }
    
    @Inject
    UsuarioControlador user;
    
    @Inject
    EventoControlador ev;

    
    public void addValoracion(Valoracion va){
        ev.getEselected().getValoraciones().add(va);
    }
    
    
    public ArrayList<Valoracion> verValoraciones(){
        
        
        return ev.getEselected().getValoraciones();
        
    }
    
    
    
     public String creaValoracion(){
        
        FacesContext ctx = FacesContext.getCurrentInstance(); 
         
        if(haValorado()){
        
        ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Ya ha valorado", "Ya ha valorado"));
        
           
        } else{
            
         
        Valoracion val = new Valoracion();
         
        val.setAutor(user.getUser());
        val.setComentario(comentario);
        val.setPuntuacion(puntuacion);
         
        addValoracion(val);
               }
        
        return "evento.xhtml";
        }
     
     
     public boolean haValorado(){
         
         boolean encontrado = false;
         int i = 0;
         
         if(ev.getEselected().getValoraciones().isEmpty() || ev.getEselected().getValoraciones() == null){
             return encontrado;
         } else {
             
         
         while(!encontrado && i<ev.getEselected().getValoraciones().size()){
             
             if(ev.getEselected().getValoraciones().get(i).getAutor().equals(user.getUser())){
                 encontrado = true;
             }
             
         }
                  }

         return encontrado;
         
         
     }
        
    
     
     
}
