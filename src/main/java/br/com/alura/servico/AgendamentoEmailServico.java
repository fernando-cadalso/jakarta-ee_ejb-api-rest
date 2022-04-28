package br.com.alura.servico;

import java.util.List;
import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.inject.Inject;

import br.com.alura.infra.AgendamentoEmailDAO;
import br.com.alura.modelo.AgendamentoEmail;

@Stateless
public class AgendamentoEmailServico {

	@Inject
	private AgendamentoEmailDAO dao;
	/*
	 * Lê o nome da classe que que será monitorada.
	 */
	private static final Logger LOGGER = Logger.getLogger(AgendamentoEmail.class.getName());
	
	public List<AgendamentoEmail> listar(){
		
		return dao.listar();
	}
	
	public void inserir(AgendamentoEmail ae) {
		/*
		 * Regra do negócio
		 */
		ae.setAgendado(false);
		/*
		 * Persiste no SGBD
		 */
		dao.inserir(ae);
	}
	
	public List<AgendamentoEmail> listarNaoAgendados(){
		return dao.listarNaoAgendado();
	}
	
	public void alterar(AgendamentoEmail ae) {
		/*
		 * Regra do negócio
		 * Altera cadastro do e-mail para agendado=true
		 */
		ae.setAgendado(true);
		dao.alterar(ae);
	}
	/*
	 * Método para simular o envio de e-mail.
	 */
	public void enviar(AgendamentoEmail ae) {
		try {
			Thread.sleep(7000);
			LOGGER.info("E-mail " + ae.getEmail() + " foi enviado.");
		} catch (Exception e) {
			LOGGER.warning(e.getMessage());
		}
	}
	
	
}
