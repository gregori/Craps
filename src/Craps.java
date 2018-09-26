import java.security.SecureRandom;

public class Craps {
	private static final SecureRandom numerosAleatorios = new SecureRandom();
	
	// tipo enum com constantes que representam o estado do jogo
	private enum Estado { CONTINUA, VENCEU, PERDEU };
	
	// constantes que representam lançamentos comuns dos dados
	private static final int SNAKE_EYES = 2;
	private static final int TREY = 3;
	private static final int SETE = 7;
	private static final int YO_NZE = 11;
	private static final int BOX_CARS = 12;
	
	// joga uma partida de craps
	public void jogar()
	{
		int meuPonto = 0; // pontos se não ganhar ou perder na 1a. rolagem
		Estado estadoDoJogo; // pode conter CONTINUA, VENCEU ou PERDEU
		int somaDosDados = jogaDados(); // primeira rolagem dos dados
		
		// determina o estado do jogo e a pontuação com base no primeiro lançamento
		switch (somaDosDados)
		{
		case SETE: // ganha com 7 no primeiro lançamento
		case YO_NZE: // ganha com 11 no primeiro lancamento
			estadoDoJogo = Estado.VENCEU;
			break;
		case SNAKE_EYES: // perde com 2 no primeiro lançamento
		case TREY: // perde com 3 no primeiro lançamento
		case BOX_CARS: // perde com 12 no primeiro lançamento
			estadoDoJogo = Estado.PERDEU;
			break;
		default: // não ganhou nem perdeu, registra a pontuação
			estadoDoJogo = Estado.CONTINUA; // jogo não terminou
			meuPonto = somaDosDados; // informa a pontuação
			System.out.printf("Ponto é %d\n", meuPonto);
			break;
		} // fim do switch
		
		// enquanto o jogo não estiver completo
		while (estadoDoJogo == Estado.CONTINUA) // nem VENCEU nem PERDEU
		{
			somaDosDados = jogaDados(); // lança os dados novamente
			
			// determona o estado do jogo
			if (somaDosDados == meuPonto) // vitória por pontuação
			{
				estadoDoJogo = Estado.VENCEU;
			} else
			{
				if (somaDosDados == SETE) // perde obtendo 7 antes de atingir a pontuação
				{
					estadoDoJogo = Estado.PERDEU;
				}
			}
		} // fim do while
		
		// exibe uma mensagem ganhou ou perdeu
		if (estadoDoJogo == Estado.VENCEU)
			System.out.println("Jogador venceu");
		else
			System.out.println("Jogador perdeu");
	} // fim do método jogar
	
	public int jogaDados()
	{
		//seleciona valores aleatórios do dado
		int dado1 = 1 + numerosAleatorios.nextInt(6); // primeiro lançamento do dado
		int dado2 = 1 + numerosAleatorios.nextInt(6); // segundo lançamento do dado
		
		int soma = dado1 + dado2; // soma dos valores dos dados
		
		// exibe os resultados desse lançamento
		exibeResultados(dado1, dado2, soma);
		
		return soma;
	} // fim do método jogaDados
	
	public void exibeResultados(int dado1, int dado2, int soma)
	{
		System.out.printf("Jogador lançou %d + %d = %d\n", dado1, dado2, soma);
	}
} // fim da classe Craps
