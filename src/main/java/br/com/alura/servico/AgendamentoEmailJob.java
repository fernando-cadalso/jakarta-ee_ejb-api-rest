package br.com.alura.servico;

import java.util.List;

import javax.ejb.Schedule;
import javax.ejb.Singleton;
import javax.inject.Inject;

import br.com.alura.modelo.AgendamentoEmail;

@Singleton
public class AgendamentoEmailJob {

	@Inject
	private AgendamentoEmailServico aeServico;
	
	@Schedule(hour = "*", minute = "*", second = "*/10")
	public void enviarEmail() {
		/*
		 * Leio a lista de e-mails não agendados
		 */
		List<AgendamentoEmail> listarNaoAgendados = aeServico.listarNaoAgendados();
		/*
		 * Percorro a lista de agendamento e envio cada item 
		 * e depois atualizo o estado do atributo "agendado".
		 */
		listarNaoAgendados.forEach(emailNaoAgendado -> {
			
			aeServico.enviar(emailNaoAgendado);
			aeServico.alterar(emailNaoAgendado);
		});
	}
	
}
