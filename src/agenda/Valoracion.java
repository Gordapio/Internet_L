/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package agenda;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 *
 * @author User
 */
@Entity
public class Valoracion implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id_valoracion;
    private String comentario;
    @Column(nullable = false)
    private int puntuacion;
    @ManyToOne
    private Usuarioreg autor;
    
    public Valoracion(){}
    
    public  void setUsuarioreg(Usuarioreg autor){
        this.autor = autor;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id_valoracion != null ? id_valoracion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Valoracion)) {
            return false;
        }
        Valoracion other = (Valoracion) object;
        if ((this.id_valoracion == null && other.id_valoracion != null) || (this.id_valoracion != null && !this.id_valoracion.equals(other.id_valoracion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "agenda.Valoracion[ id=" + id_valoracion + " ]";
    }
    
}