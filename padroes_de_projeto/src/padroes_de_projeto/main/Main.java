package padroes_de_projeto.main;

public class Main {
	public static void main(String[] args) {
		Usuario usuario = new Usuario("george", 18);
		
		if(usuario.getNome().length() < 3) {
			throw new RuntimeException("Nome nao pode ter menos de 3 caracteres");
		}
		
		if(usuario.getNome().length() > 15) {
			throw new RuntimeException("Nome nao pode ter mais de 15 caracteres");
		}
		
		if(usuario.getIdade() < 18) {
			throw new RuntimeException("Nome nao pode ser menor de idade");
		}
		
		if(usuario.getIdade() > 60) {
			throw new RuntimeException("Nome nao pode ter mais de 60 anos");
		}
		
		cadastrar(usuario);
	}

	private static void cadastrar(Usuario usuario) {
		System.out.println("usuario salvo com sucesso "+usuario.getNome());
	}
}
