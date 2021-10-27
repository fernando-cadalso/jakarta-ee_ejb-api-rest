package br.com.alura.servico;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import br.com.alura.infra.AgendamentoEmailDAO;
import br.com.alura.modelo.AgendamentoEmail;

@Stateless
public class AgendamentoEmailServico {

	@Inject
	private AgendamentoEmailDAO dao;
	
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
	
	
}
