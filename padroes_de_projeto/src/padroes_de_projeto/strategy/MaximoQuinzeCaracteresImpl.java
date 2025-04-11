package padroes_de_projeto.strategy;

import padroes_de_projeto.main.Usuario;

public class MaximoQuinzeCaracteresImpl implements ICadastro {

	@Override
	public void valida(Usuario usuario) {
		if(usuario.getNome().length() > 15) {
			throw new RuntimeException("Nome nao pode ter mais de 15 caracteres");
		}
	}

}
