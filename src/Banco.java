import java.util.ArrayList;
import java.util.List;

public class Banco {

	private String nome;
	private List<Conta> contas;

	public Banco(String nome){
		this.contas = new ArrayList<>();
		this.nome = nome;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public List<Conta> getContas() {
		return contas;
	}

	public void setContas(List<Conta> contas) {
		this.contas = contas;
	}

	public void filtrarContasComSaldoAcimaDe(double valor) {
		System.out.println("Contas com saldo acima de R$: " + valor);
        contas.stream()
              .filter(conta -> conta.getSaldo() > valor) // Filtra as contas com saldo acima de 'valor'
              .forEach(conta -> System.out.println(String.format("Agência: %d | Conta: %d | Cliente: %s",
                                                                conta.getAgencia(),
                                                                conta.getNumero(),
																conta.cliente.getNome())));
    }

	public void filtrarContasPorCliente(Cliente cliente) {
		System.out.println("Contas registradas em nome de " + cliente.getNome());
        contas.stream()
				.filter(conta -> conta.cliente.getNome().equals(cliente.getNome()))
				.forEach(conta -> System.out.println(String.format("Agência: %d | Conta: %d",
																conta.getAgencia(),
																conta.getNumero())));
    }
}
