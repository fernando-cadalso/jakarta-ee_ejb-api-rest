package br.com.alura.servico;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.inject.Inject;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;

import br.com.alura.modelo.AgendamentoEmail;

/*
 * Implementa a interface para realizar o
 * monitoramento da fila de e-mails e realizar
 * o envio quando um novo item for adicionado na fila.
 * 
 * A anotação classifica a classe como um MDB, e as
 * configurações informam qual a fila será monitorada e
 * qual o seu tipo.
 */
@MessageDriven(activationConfig = {
		@ActivationConfigProperty(propertyName = "destinationLookup", propertyValue = "java:/jms/queue/EmailQueue"),
		@ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue") })
public class AgendamentoEmailMDB implements MessageListener {
	/*
	 * Injeta a classe que representa os serviços da aplicação
	 */
	@Inject
	private AgendamentoEmailServico aeServico;

	@Override
	public void onMessage(Message message) {
		/*
		 * Define qual classe vai compor o corpo da mensagem.
		 */
		try {
			AgendamentoEmail ae = message.getBody(AgendamentoEmail.class);
			aeServico.enviar(ae);
		} catch (JMSException e) {
			throw new RuntimeException(e);
		}
	}
}
