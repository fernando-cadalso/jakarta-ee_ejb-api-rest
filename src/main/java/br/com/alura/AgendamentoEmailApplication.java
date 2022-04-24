package br.com.alura;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

/*
 * Contexto intermediário entre o contexto da aplicação
 * e o recurso que será acessado. O padrão é uma /.
 */
@ApplicationPath("/api")
public class AgendamentoEmailApplication extends Application{

}
