package padroes_de_projeto.strategy;

import java.util.ArrayList;
import java.util.List;

import padroes_de_projeto.main.Usuario;

public class MainStrategy {
	public static void main(String[] args) {
		Usuario usuario = new Usuario("george", 30);
		
		List<ICadastro> validacoes = new ArrayList<ICadastro>();
		validacoes.add(new MinimoTresCaracteresImpl());
		validacoes.add(new MaximoQuinzeCaracteresImpl());
		validacoes.add(new MinimoDezoitoAnosImpl());
		
		validacoes.forEach(valicao -> valicao.valida(usuario));
	}
}
