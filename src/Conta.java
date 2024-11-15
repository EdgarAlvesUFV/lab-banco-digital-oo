import java.util.ArrayList;
import java.util.List;

public abstract class Conta implements IConta {
	
	private static final int AGENCIA_PADRAO = 1;
	private static int SEQUENCIAL = 1;

	protected int agencia;
	protected int numero;
	protected double saldo;
	protected Cliente cliente;
	protected List<String> historicoTransacoes = new ArrayList<>();

	public Conta(Cliente cliente) {
		this.agencia = Conta.AGENCIA_PADRAO;
		this.numero = SEQUENCIAL++;
		this.cliente = cliente;
	}

	@Override
	public void sacar(double valor) {
		if (valor <= 0) {
			throw new IllegalArgumentException("O valor de saque deve ser maior que zero.");
		}
		if (valor > saldo) {
			throw new IllegalArgumentException("Saldo insuficiente.");
		}
		saldo -= valor;
		this.registrarTransacao("Saque", valor);
	}


	@Override
	public void depositar(double valor) {
		if (valor <= 0) {
			throw new IllegalArgumentException("O valor de depósito deve ser maior que zero.");
		}
		saldo += valor;
		this.registrarTransacao("Depósito", valor);
	}
	

	@Override
	public void transferir(double valor, IConta contaDestino) {
		this.validarTransferencia(valor, contaDestino);
		this.sacar(valor);
		contaDestino.depositar(valor);
		this.registrarTransacao("Transferência", valor);
	}

	private void validarTransferencia(double valor, IConta contaDestino) {
		if (valor <= 0) {
			throw new IllegalArgumentException("O valor de transferência deve ser maior que zero.");
		}
		if (contaDestino == null) {
			throw new IllegalArgumentException("A conta destino não pode ser nula.");
		}
		if (this == contaDestino) {
			throw new IllegalArgumentException("Não é possível transferir para a mesma conta.");
		}
		if (valor > this.saldo) {
			throw new IllegalArgumentException("Saldo insuficiente para realizar a transferência.");
		}
	}

	private void registrarTransacao(String tipo, double valor) {
		historicoTransacoes.add(String.format("%s de R$ %.2f", tipo, valor));
	}

	public int getAgencia() {
		return agencia;
	}

	public int getNumero() {
		return numero;
	}

	public double getSaldo() {
		return saldo;
	}

	protected void imprimirInfosComuns() {
		System.out.println(String.format("Titular: %s", this.cliente.getNome()));
		System.out.println(String.format("Agencia: %d", this.agencia));
		System.out.println(String.format("Numero: %d", this.numero));
		System.out.println(String.format("Saldo: %.2f", this.saldo));
	}
}
