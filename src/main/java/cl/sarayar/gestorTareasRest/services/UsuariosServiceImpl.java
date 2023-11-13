package cl.sarayar.gestorTareasRest.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import cl.sarayar.gestorTareasRest.config.auth.UserDetailsImpl;
import cl.sarayar.gestorTareasRest.entities.Usuario;
import cl.sarayar.gestorTareasRest.repositories.UsuariosRepository;

@Service
public class UsuariosServiceImpl implements UsuariosService{


	private UsuariosRepository usRepo;

	@Autowired
	public UsuariosServiceImpl( UsuariosRepository usRepo){
		this.usRepo = usRepo;
	}
	@Override
	public Usuario save(Usuario usuario) {

		return this.usRepo.save(usuario);
	}

	@Override
	public List<Usuario> getAll() {

		return this.usRepo.findAll();
	}

	@Override
	public Usuario findByCorreo(String correo) {
	
		return this.usRepo.findByCorreo(correo).orElse(null);
	}

	@Override
	public Usuario findById(String id) {

		return this.usRepo.findById(id).orElse(null);
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
	
		Usuario usuario = this.findByCorreo(username);
		return usuario!= null ? new UserDetailsImpl(usuario):null;
	}

	@Override
	public Boolean existsByCorreo(String correo) {
		return this.usRepo.existsByCorreo(correo);
	}
}
