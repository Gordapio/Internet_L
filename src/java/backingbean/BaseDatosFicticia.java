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
        listaValoraciones=new ArrayList<>();
        
        //USUARIOS
        Usuarioreg u=new Usuarioreg();
        u.setNickname("Paco");
        u.setContraseña("123");
        u.setRol(Usuarioreg.tipoRol.normal);
        listaUsuarios.add(u);
        
        Usuarioreg us=new Usuarioreg();
        us.setNickname("Periodista1");
        us.setNombre("Paquito");
        us.setContraseña("123");
        us.setRol(Usuarioreg.tipoRol.periodista);
        listaUsuarios.add(us);
        
        Usuarioreg uss=new Usuarioreg();
        uss.setNombre("Pepito");
        uss.setNickname("Periodista2");
        uss.setContraseña("123");
        uss.setRol(Usuarioreg.tipoRol.periodista);
        listaUsuarios.add(uss);
        
        Usuarioreg use=new Usuarioreg();
        use.setNickname("Normal");
        use.setContraseña("123");
        use.setRol(Usuarioreg.tipoRol.normal);
        listaUsuarios.add(use);
        
        Usuarioreg admin=new Usuarioreg();
        admin.setNickname("admin");
        admin.setContraseña("admin");
        admin.setRol(Usuarioreg.tipoRol.administrador);
        listaUsuarios.add(admin);
        
        // Eventos
        Evento e= new Evento();
        e.setNombre("Normal");
        e.setDescripcion("Cagontusmuela");
        e.setDestacado(Boolean.FALSE);
        e.setLocalidad("Mérida");
        e.setVisible(Boolean.TRUE);
        e.setPropuesto(Boolean.FALSE);
        e.setLikes(new ArrayList<>());
        e.setFecha_inicio(Date.valueOf("2015-03-31"));
        e.setTe(Evento.tipoEvento.cultural);
        listaEventos.add(e);
        
        Evento e1= new Evento();
        e1.setNombre("Más pronto");
        e1.setDescripcion("Cagontusmuela");
        e1.setDestacado(Boolean.FALSE);
        e1.setLocalidad("Alozaina");
        e1.setVisible(Boolean.TRUE);
        e1.setPropuesto(Boolean.FALSE);
        e1.setLikes(new ArrayList<>());
        e1.setFecha_inicio(Date.valueOf("2015-03-30"));
        e1.setTe(Evento.tipoEvento.otro);
        listaEventos.add(e1);
        
        Evento ev= new Evento();
        ev.setNombre("Destacado");
        ev.setDestacado(Boolean.TRUE);
        ev.setLocalidad("Bonela");
        ev.setLikes(new ArrayList<>());
        ev.setFecha_inicio(Date.valueOf("2016-03-31"));
        ev.setVisible(Boolean.TRUE);
        ev.setPropuesto(Boolean.FALSE);
        ev.setTe(Evento.tipoEvento.empresarial);
        listaEventos.add(ev);
        
        Evento eve= new Evento();
        eve.setNombre("Con likes");
        eve.setDestacado(Boolean.FALSE);
        eve.setLikes(listaUsuarios);
        eve.setDescripcion("enga amo");
        eve.setFecha_inicio(Date.valueOf("2016-03-31"));
        eve.setLocalidad("Churripena");
        eve.setVisible(Boolean.TRUE);
        eve.setPropuesto(Boolean.FALSE);
        eve.setTe(Evento.tipoEvento.social);
        listaEventos.add(eve);
    }
    
    public ArrayList<Evento> getEventos(){return listaEventos;}
    public ArrayList<Usuarioreg> getUsuarios(){return listaUsuarios;}
    public ArrayList<Valoracion> getValoraciones(){return listaValoraciones;}
    public void addEvento(Evento e){listaEventos.add(e);}
    public void addUsuario(Usuarioreg u){listaUsuarios.add(u);}
    public Evento getEvento(){return listaEventos.get(0);}
}
