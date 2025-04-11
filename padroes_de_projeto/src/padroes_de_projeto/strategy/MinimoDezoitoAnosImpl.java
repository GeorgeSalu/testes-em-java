package padroes_de_projeto.strategy;

import padroes_de_projeto.main.Usuario;

public class MinimoDezoitoAnosImpl implements ICadastro {

	@Override
	public void valida(Usuario usuario) {
		if(usuario.getIdade() < 18) {
			throw new RuntimeException("Nome nao pode ser menor de idade");
		}
	}

}
