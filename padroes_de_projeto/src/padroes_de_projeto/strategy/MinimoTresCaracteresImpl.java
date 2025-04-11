package padroes_de_projeto.strategy;

import padroes_de_projeto.main.Usuario;

public class MinimoTresCaracteresImpl implements ICadastro {

	@Override
	public void valida(Usuario usuario) {
		if(usuario.getNome().length() < 3) {
			throw new RuntimeException("Nome nao pode ter menos de 3 caracteres");
		}
	}

}
