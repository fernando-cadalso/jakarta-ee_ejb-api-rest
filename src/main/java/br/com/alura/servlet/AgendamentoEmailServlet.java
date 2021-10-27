package br.com.alura.servlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.alura.modelo.AgendamentoEmail;
import br.com.alura.servico.AgendamentoEmailServico;

@WebServlet("/emails")
public class AgendamentoEmailServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	@Inject
	private AgendamentoEmailServico servico;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		PrintWriter pw = resp.getWriter();
		servico.listar().forEach(resposta -> pw
				.print("Lista de emails disponíveis: " + resposta.getEmail() + " - " + resposta.getAssunto()));
		pw.close();
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		/*
		 * Recebe os dados enviados no corpo da requisição POST.
		 */
		BufferedReader leitorRequisicao = req.getReader();
		/*
		 * Separa cada parte do corpo do texto separado por vírgula.
		 */
		String[] partesEmail = leitorRequisicao.readLine().split(",");
		AgendamentoEmail agendamentoEmail = new AgendamentoEmail();
		agendamentoEmail.setEmail(partesEmail[0]);
		agendamentoEmail.setAssunto(partesEmail[1]);
		agendamentoEmail.setMensagem(partesEmail[2]);
		/*
		 * Persiste o novo agendamento.
		 */
		servico.inserir(agendamentoEmail);
	}
}
