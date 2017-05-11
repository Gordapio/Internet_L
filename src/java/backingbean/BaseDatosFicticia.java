/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package backingbean;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import jpa.Evento;
import jpa.Usuarioreg;
import jpa.Valoracion;

/**
 *
 * @author gordo
 */
@Named
@ApplicationScoped
public class BaseDatosFicticia {
    private ArrayList<Evento> listaEventos;
    private ArrayList<Usuarioreg> listaUsuarios;
    private ArrayList<Valoracion> listaValoraciones;
    /**
     * Creates a new instance of BaseDatosFicticia
     */
    public BaseDatosFicticia() {
        listaEventos=new ArrayList<>();
        listaUsuarios=new ArrayList<>();
        ArrayList<Valoracion> valoraciones=new ArrayList<>();
         ArrayList<Valoracion> valoracione=new ArrayList<>();
         ArrayList<Valoracion> valoracioness=new ArrayList<>();
        
        /*Valoracion val = new Valoracion();
        val.setAutor(null);
        val.setComentario("");
        val.setPuntuacion(0);
        valoraciones.add(val);*/
        
        Evento e= new Evento();
        e.setNombre("Fiesta1");
        e.setDescripcion("Cagontusmuela");
        e.setLocalidad("Alozaina");
        e.setVisible(Boolean.TRUE);
        e.setPropuesto(Boolean.FALSE);
        //e.setFecha_inicio((Date) Calendar.getInstance().getTime());
        e.setFecha_inicio(Date.valueOf("2015-03-31"));
        e.setValoraciones(valoraciones);
        listaEventos.add(e);
        
        /*Valoracion va = new Valoracion();
        va.setAutor(null);
        va.setComentario("");
        va.setPuntuacion(0);
        valoracione.add(va);*/
        
        Evento ev= new Evento();
        ev.setNombre("Feria");
        ev.setDescripcion("YEAH");
        ev.setLocalidad("Bonela");
        ev.setVisible(Boolean.TRUE);
        ev.setPropuesto(Boolean.FALSE);
        ev.setFecha_inicio(Date.valueOf("2017-08-20"));
        ev.setValoraciones(valoracione);
        listaEventos.add(ev);
        
        //eventos propuestos
        Evento eve= new Evento();
        eve.setNombre("Fiestaasasdasdasdas");
        eve.setDescripcion("enga amo");
        eve.setLocalidad("Churripena");
        eve.setVisible(Boolean.FALSE);
        eve.setPropuesto(Boolean.TRUE);
        eve.setFecha_inicio(Date.valueOf("2017-05-05"));
        e.setValoraciones(valoracioness);
        listaEventos.add(eve);
        
        
        //USUARIOS
        Usuarioreg u=new Usuarioreg();
        u.setNickname("Paco");
        u.setContraseña("123");
        u.setId_usuario(2L);
        u.setRol(Usuarioreg.tipoRol.normal);
        listaUsuarios.add(u);
        
        Usuarioreg uz=new Usuarioreg();
        uz.setNickname("Paca");
        uz.setContraseña("123");
        uz.setId_usuario(6L);
        uz.setRol(Usuarioreg.tipoRol.normal);
        listaUsuarios.add(uz);
        
        Usuarioreg us=new Usuarioreg();
        us.setNickname("Periodista1");
        us.setNombre("Isidro");
        us.setApellidos("González Almodovar");
        us.setContraseña("123");
        us.setId_usuario(3L);
        us.setRol(Usuarioreg.tipoRol.periodista);
        listaUsuarios.add(us);
        
        Usuarioreg uss=new Usuarioreg();
        uss.setNickname("Periodista2");
        uss.setNombre("Rodrigo");
        uss.setApellidos("Sereno Pérez");
        uss.setContraseña("123");
        uss.setId_usuario(4L);
        uss.setRol(Usuarioreg.tipoRol.periodista);
        listaUsuarios.add(uss);
        
        Usuarioreg use=new Usuarioreg();
        use.setNickname("Normal");
        use.setContraseña("123");
        use.setId_usuario(5L);
        use.setRol(Usuarioreg.tipoRol.normal);
        listaUsuarios.add(use);
        
        Usuarioreg admin=new Usuarioreg();
        admin.setNickname("admin");
        admin.setContraseña("admin");
        admin.setId_usuario(1L);
        admin.setRol(Usuarioreg.tipoRol.administrador);
        listaUsuarios.add(admin);
    }
    
    public ArrayList<Evento> getEventos(){return listaEventos;}
    public ArrayList<Usuarioreg> getUsuarios(){return listaUsuarios;}
    public ArrayList<Valoracion> getValoraciones(){return listaValoraciones;}
    public void addEvento(Evento e){listaEventos.add(e);}
    public void addUsuario(Usuarioreg u){listaUsuarios.add(u);}
    public Evento getEvento(){return listaEventos.get(0);}
}
