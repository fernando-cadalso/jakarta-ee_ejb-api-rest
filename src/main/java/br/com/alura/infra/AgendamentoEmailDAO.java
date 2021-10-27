package br.com.alura.infra;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.com.alura.modelo.AgendamentoEmail;

@Stateless
public class AgendamentoEmailDAO {

	@PersistenceContext
	EntityManager em;

	public List<AgendamentoEmail> listar() {
		return em.createQuery("SELECT ae FROM AgendamentoEmail ae", AgendamentoEmail.class).getResultList();
	}
	
	public void inserir(AgendamentoEmail agendamentoEmail) {
		
		em.persist(agendamentoEmail);
	}
}
