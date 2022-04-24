package br.com.alura.controller;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import br.com.alura.modelo.AgendamentoEmail;
import br.com.alura.servico.AgendamentoEmailServico;

/*
 * Controlador para gerenciar as requisições HTTP do cliente e 
 * as respostas para ele.
 * O endpoint que este controlador vai gerenciar é o "emails"	
 */
@Path("emails")
public class AgendamentoEmailController {

	/*
	 * Injeção do EJB que implementa os serviços 
	 * das regras de negócio
	 */
	@Inject
	AgendamentoEmailServico agendamentoEmailServico;
	
	/*
	 * Método para devolver um objeto que representa
	 * uma lista de e-mails, para uma requisição GET,
	 * no formato JSON
	 */
	@GET
	@Produces(value = MediaType.APPLICATION_JSON)
	public Response listar() {
		return Response.ok(agendamentoEmailServico.listar()).build();
	}
	
	/*
	 * Método para criar um objeto que representa
	 * um novo e-mail enviado pelo cliente,
	 * através de uma requisiçaõ POST,
	 * consumindo um conteúdo JSON.
	 */
	@POST
	@Consumes(value = MediaType.APPLICATION_JSON)
	public Response inserir(AgendamentoEmail ae) {
		agendamentoEmailServico.inserir(ae);
		return Response.status(Status.CREATED).build();
	}
}
