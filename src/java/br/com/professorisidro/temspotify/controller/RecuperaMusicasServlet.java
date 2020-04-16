/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.professorisidro.temspotify.controller;

import br.com.professorisidro.temspotify.dao.DataSource;
import br.com.professorisidro.temspotify.dao.MusicaDAO;
import br.com.professorisidro.temspotify.model.Usuario;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author isidro
 */
public class RecuperaMusicasServlet extends HttpServlet {

    
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String paginaDestino = "/error.jsp";
        try{
            Usuario usuario = (Usuario)request.getSession().getAttribute("Usuario");
            if (usuario == null){
                request.setAttribute("erroSTR","Usuario nao conectado!");
            }
            else{
                DataSource dataSource = new DataSource();
                MusicaDAO mdao = new MusicaDAO(dataSource);
                List<Object> lista = mdao.read(null);
                if (lista == null){
                    request.setAttribute("erroSTR", "Erro ao recupear musicas do Banco de Dados");
                }
                else{
                    String idPlaylist = request.getParameter("idplaylist");
                    request.setAttribute("idPlaylist", idPlaylist);
                    request.setAttribute("ListaMusicas",lista);
                    paginaDestino = "/minhasmusicas.jsp";
                }
                dataSource.getConnection().close();
            }
            
        }
        catch(Exception ex){
            System.out.println("Erro ao montar pagina de musicas "+ex.getMessage());
            request.setAttribute("erroSTR", "Erro ao montar pagina de musicas");
        }
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(paginaDestino);
        dispatcher.forward(request, response);
    
    }
    

   
}
