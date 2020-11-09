//#region Imports
package com.r6.mensajeria;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.*;

import org.hibernate.annotations.GenericGenerator;

import com.r6.service.SistemaDao;
import com.r6.service.UsuarioDao;


//#endregion 
//Many to One con Sistema
//One to many con contactos
//Many to Many con Correos(Para remitente)
@Entity
@Table(name = "Tblusuario")
@NamedQueries(value = {
		@NamedQuery(
			name="Usuario.find",
			query="SELECT u FROM Usuario u WHERE u.idUsuario = :idParam"),
		@NamedQuery(
			name="Usuario.findAll",
			query="SELECT u FROM Usuario u ORDER BY u.idUsuario")
	
})


public class Usuario implements Serializable {

    //Atributos
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @GenericGenerator(name = "native", strategy = "native")
    @Column(name = "Id_usuario")
    private Integer idUsuario;

    @ManyToOne
    @JoinColumn(name = "Id_user_sys", referencedColumnName = "Id_sistema")
    private Sistema sistema;

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL)
    private Set<Contacto> contactos;


    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "Tbl_remitente",
            joinColumns = {
                @JoinColumn(name = "Id_usuario", referencedColumnName = "Id_usuario")},
            inverseJoinColumns = {
                @JoinColumn(name = "Id_correo", referencedColumnName = "Id_correo")})
    private Set<Correo> correos;

    @ManyToMany(mappedBy = "usuarioscopiados")
    private Set<Correo> correoscopiados;

    private String correo;

    private String contrasenia;

    private Boolean admin;
    
    private Boolean superUser;
    
    private Boolean activo;
    
    public Usuario(){};
    
    
    
    public Usuario(Integer idSistema, String correo, String contrasenia, Boolean admin, Boolean superUser, Boolean activo) {
		super();

		SistemaDao sisDao = new SistemaDao();
		
		for(Sistema s: sisDao.getAll()) {
        	if (s.getId() == idSistema) {
        		this.sistema = s;
        	}
			
        }


		this.correo = correo;
		this.contrasenia = contrasenia;
		this.admin = admin;
		this.superUser = superUser;
		this.activo = activo;
	}



	//<editor-fold defaultstate="collapsed" desc="Getters y Setters">

    public Set<Correo> getCorreos() {
        return correos;
    }

    //<editor-fold defaultstate="collapsed" desc="Getters y Setters">
    public void setCorreos(Set<Correo> correos) {
        this.correos = correos;
    }

    public Set<Correo> getCorreoscopiados() {
        return correoscopiados;
    }

    public Set<Correo> getRemitentesCorreo() {
        return correos;
    }

    public void setRemitentesCorreo(Set<Correo> remitentesCorreo) {
        this.correos = remitentesCorreo;
    }


    
    

    public void setCorreoscopiados(Set<Correo> correoscopiados) {
        this.correoscopiados = correoscopiados;
    }

    public Integer getIdUsuario() {
        return this.idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Sistema getSistema() {
        return this.sistema;
    }

    public void setSistema(Sistema sistema) {
        this.sistema = sistema;
    }

    public String getCorreo() {
        return this.correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getContrasenia() {
        return this.contrasenia;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }

    public Set<Contacto> getContactos() {
        return this.contactos;
    }

    public void setContactos(Set<Contacto> contactos) {
        this.contactos = contactos;
    }

	public Boolean getAdmin() {
		return admin;
	}

	public void setAdmin(Boolean admin) {
		this.admin = admin;
	}

	public Boolean getActivo() {
		return activo;
	}

	public void setActivo(Boolean activo) {
		this.activo = activo;
	}

	public Boolean getSuperUser() {
		return superUser;
	}

	public void setSuperUser(Boolean superUser) {
		this.superUser = superUser;
	}

	public void actualizarUsuario(Integer idUsuario){
		UsuarioDao usDao = new UsuarioDao();
		this.idUsuario = idUsuario;
		
		for(Usuario us: usDao.getAll()) {
			if(us.getIdUsuario()==this.idUsuario) {
		
				//Orden para crear usuario (Integer idSistema, String correo, String contrasenia, Boolean admin, Boolean superUser, Boolean activo)
				this.correo = us.getCorreo();
				this.contrasenia = us.getContrasenia();
				this.admin = us.getAdmin();
				this.superUser = us.getSuperUser();
				this.activo = us.getActivo();
				this.sistema = us.getSistema();
				
			}
		}
		
	}
	
	public void nuevoSistemaUsuario(Integer idSistema) {
		
		SistemaDao sisDao = new SistemaDao();
		
		for(Sistema s: sisDao.getAll()) {
        	if (s.getId() == idSistema) {
        		this.sistema = s;
        	}
			
        }
		
	}
	
    //</editor-fold>
}
