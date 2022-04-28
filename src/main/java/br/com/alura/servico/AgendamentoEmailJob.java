package br.com.alura.servico;

import java.util.List;

import javax.annotation.Resource;
import javax.ejb.Schedule;
import javax.ejb.Singleton;
import javax.inject.Inject;
import javax.jms.JMSConnectionFactory;
import javax.jms.JMSContext;
import javax.jms.Queue;

import br.com.alura.modelo.AgendamentoEmail;

@Singleton
public class AgendamentoEmailJob {

	@Inject
	private AgendamentoEmailServico aeServico;

	/*
	 * Injeta uma fábrica de conexão com JMS
	 * 
	 *
	 * Implementa um contexto para o JMS
	 *
	 */
	@Inject
	@JMSConnectionFactory("java:jboss/DefaultJMSConnectionFactory")
	private JMSContext context;
	/*
	 * Especifica qual fila será usada para enviar as mensagens JMS. Esse recurso
	 * foi criado no servidor WildFly, com esse comando em sua cli: jms-queue add
	 * --queue-address=EmailQueue --entries=java:/jms/queue/EmailQueue
	 * 
	 * E define um atributo para representar a fila.
	 */
	@Resource(mappedName = "java:/jms/queue/EmailQueue")
	private Queue queue;

	@Schedule(hour = "*", minute = "*", second = "*/10")
	public void enviarEmail() {
		/*
		 * Leio a lista de e-mails não agendados
		 */
		List<AgendamentoEmail> listarNaoAgendados = aeServico.listarNaoAgendados();
		/*
		 * Percorro a lista de agendamento e envio cada item e depois atualizo o estado
		 * do atributo "agendado".
		 * 
		 * Chama o contexto JMS, informa qual fila será alimentada e o conteúdo da fila.
		 *
		 * Atualiza o estado de cada e-mail colocado na fila.
		 *
		 */
		listarNaoAgendados.forEach(emailNaoAgendado -> {
			context.createProducer().send(queue, emailNaoAgendado);
			aeServico.alterar(emailNaoAgendado);
		});
	}

}
