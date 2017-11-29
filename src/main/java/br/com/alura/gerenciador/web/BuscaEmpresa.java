package br.com.alura.gerenciador.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.alura.gerenciador.Empresa;
import br.com.alura.gerenciador.dao.EmpresaDAO;

@WebServlet(urlPatterns = "/busca")
public class BuscaEmpresa extends HttpServlet {
	private String filtro;
	
	public BuscaEmpresa() {
	    System.out.println("Instanciando uma Servlet do tipo BuscaEmpresa "
	            + this);
	}
	
	@Override
	public void init() throws ServletException {
	    super.init();
	    System.out.println("Inicializando a Servlet " + this);
	}

	@Override
	public void destroy() {
	    super.destroy();
	    System.out.println("Destruindo a Servlet " + this);
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
	        throws ServletException, IOException {

	    PrintWriter writer = resp.getWriter();
	    writer.println("<html>");
	    writer.println("<body>");
	    writer.println("Resultado da busca:<br/>");

	    filtro = req.getParameter("filtro");
	    
	    try {
	        Thread.sleep(10000);
	    } catch (InterruptedException e) {
	        e.printStackTrace();
	    }
	    
	    Collection<Empresa> empresas = new EmpresaDAO()
	            .buscaPorSimilaridade(filtro);

	    writer.println("<ul>");
	    for (Empresa empresa : empresas) {
	        writer.println("<li>" + empresa.getId() + ": " + empresa.getNome() + "</li>");
	    }
	    writer.println("</ul>");

	    writer.println("</body>");
	    writer.println("</html>");
	}
}