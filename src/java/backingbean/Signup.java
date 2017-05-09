/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package backingbean;

import javax.inject.Named;
import javax.enterprise.context.Dependent;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import jpa.Usuarioreg;

/**
 *
 * @author Omars
 */
@Named(value = "signup")
@RequestScoped
public class Signup {

    /**
     * Creates a new instance of Signup
     */
    
    @Inject
    private BaseDatosFicticia bd;
    
    private String nickname;

    public String getNickname() {
        return nickname;
    }
     private String contraseña;
     
      public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }
    
    private String contraseña2;

    public String getContraseña2() {
        return contraseña2;
    }

    public void setContraseña2(String contraseña2) {
        this.contraseña2 = contraseña2;
    }
    
    public Signup() {
    }
    

    public String crearUsuario(){
        
        FacesContext ctx = FacesContext.getCurrentInstance();

        if("".equals(Signup.this.getNickname())){
            ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Usuario vacio", "Usuario vacio"));
            return "sign-up.xhtml";
        }
        
        if(!Signup.this.getContraseña().equals(Signup.this.getContraseña2())){
            ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Contraseñas no coinciden", "Contraseñas no coinciden"));
            return "sign-up.xhtml";
        }
        
        int i=0;
        boolean encontrado = false;
        
        
        
        while(!encontrado && i<bd.getUsuarios().size()){
        
            if(bd.getUsuarios().get(i).getNickname().equalsIgnoreCase(nickname)){
                  
                encontrado = true;
                ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "El Usuario ya se encuentra en la base de datos", "El Usuario ya se encuentra en la base de datos"));
                return "sign-up.xhtml";
                
            }   
            i++;
        }
        
        
        Usuarioreg u = new Usuarioreg();
        u.setNickname(nickname);
        u.setContraseña(contraseña);
        u.setRol(Usuarioreg.tipoRol.normal);
        bd.getUsuarios().add(u);
        return "index.xhtml";
       
        
        // Implementar este método
        
      
          

        
    }
}

