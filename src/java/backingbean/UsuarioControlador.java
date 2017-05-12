/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package backingbean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Objects;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import jpa.Usuarioreg;
import jpa.Usuarioreg.tipoRol;

/**
 *
 * @author gordo
 */
@Named(value = "usuarioControlador")
@SessionScoped
public class UsuarioControlador implements Serializable{

    @Inject
    BaseDatosFicticia bd;
    
    Usuarioreg user;
    Usuarioreg periodistaSelected;

    public Usuarioreg getPeriodistaSelected() {
        return periodistaSelected;
    }

    public void setPeriodistaSelected(Usuarioreg periodistaSelected) {
        this.periodistaSelected = periodistaSelected;
    }
    
     public Usuarioreg getUser() {
        return user;
    }

    public void setUser(Usuarioreg user) {
        this.user = user;
    }
    /**
     * Creates a new instance of UsuarioControlador
     */
    public UsuarioControlador() {
    }
    
    /*Hay que pasarle una ED con los campos. FUNDAMENTAL EL ROL*/
    public String crearUsuario(String nickname, String password, tipoRol rol){
        Usuarioreg u=new Usuarioreg();
        u.setNickname(nickname);
        u.setContraseña(password);
        u.setRol(rol);
        bd.addUsuario(u);
        return null;}
    
    /*Supongo que será útil. Dado un usuario, dice su rol*/
    public tipoRol dimeRol(long id){
        int i=0;
        boolean encontrado=false;
        while (!encontrado){
            if (Objects.equals(bd.getUsuarios().get(i).getId_usuario(), id))
                encontrado=true;
            else i++;
            
        }
        return bd.getUsuarios().get(i).getRol();
    }
    
    /*Modifica un usuario. Habría que comprobar permisos?*/
    public String modificarUsuario(){return null;}
    
    public String muestraUsuarios(){return null;}
    
    public ArrayList<Usuarioreg> muestraPeriodistas(){
        ArrayList<Usuarioreg> p = new ArrayList<Usuarioreg>(); 
        for (Usuarioreg u : bd.getUsuarios()){
            if (u.getRol().equals(tipoRol.periodista)){
            
                p.add(u);
            }
        }
        return p;
    } //Aquí misma duda que con el filtro, no?
    
    public String seleccionaPeriodistas(Usuarioreg p){
        periodistaSelected=p;
        return "modificar-datos.xhtml";
    }
    
    
    public String borrarPeriodista(Usuarioreg p){
        
        bd.getUsuarios().remove(p);
        periodistaSelected = null;
        
        return "mostrar-periodistas.xhtml";
        
    }
    
    public String borrarUsuario(Usuarioreg u){
        
        bd.getUsuarios().remove(u);
        user = null;
        
        return "index.xhtml";
        
    }
    
}