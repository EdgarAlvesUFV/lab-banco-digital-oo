import java.util.ArrayList;
import java.util.List;

public class Main {

	public static void main(String[] args) {
		Cliente venilton = new Cliente();
		venilton.setNome("Venilton");
		
		Conta cc = new ContaCorrente(venilton);
		Conta poupanca = new ContaPoupanca(venilton);
		Banco banco = new Banco("EdBank");
		List<Conta> contas = new ArrayList<>();
		contas.add(poupanca);
		contas.add(cc);
		banco.setContas(contas);

		cc.depositar(100);
		cc.transferir(100, poupanca);
		
		cc.imprimirExtrato();
		poupanca.imprimirExtrato();

		banco.filtrarContasComSaldoAcimaDe(50);
		banco.filtrarContasPorCliente(venilton);
		
	}

}
