/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.professorisidro.temspotify.controller;

import br.com.professorisidro.temspotify.dao.DataSource;
import br.com.professorisidro.temspotify.dao.PlayListDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author isidro
 */
public class IncluirNaPlaylistServlet extends HttpServlet {

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
        String paginaResultado = "/result.jsp";
        DataSource dataSource = null;
        try {
            int idPlaylist = Integer.parseInt(request.getParameter("idplaylist"));
            int idMusica = Integer.parseInt(request.getParameter("idmusica"));
            dataSource = new DataSource();
            PlayListDAO plDAO = new PlayListDAO(dataSource);
            if (plDAO.createMusicaPlaylist(idPlaylist, idMusica)) {
                request.setAttribute("strRESULT", "OK");
            }
            dataSource.getConnection().close();
        } 
        catch (Exception ex) {
            if (dataSource != null) {
                try {
                    dataSource.getConnection().close();
                } 
                catch (SQLException ex2) {
                    System.out.println("Nao fechei a conexao");
                }
            }
            System.out.println("Erro ao inserir " + ex.getMessage());
            request.setAttribute("strRESULT", "ERRO");
        }

        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(paginaResultado);
        dispatcher.forward(request, response);

    }

}
