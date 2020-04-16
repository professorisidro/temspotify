/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.professorisidro.temspotify.controller;

import br.com.professorisidro.temspotify.dao.DataSource;
import br.com.professorisidro.temspotify.dao.PlayListDAO;
import br.com.professorisidro.temspotify.model.PlayList;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author isidro
 */
public class PlaylistDetailsServlet extends HttpServlet {

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
        if (request.getSession().getAttribute("Usuario") != null) {
            try {
                DataSource dataSource = new DataSource();
                PlayListDAO plDao = new PlayListDAO(dataSource);
                System.out.println("Recebido id = "+request.getParameter("id"));
                int id = Integer.parseInt(request.getParameter("id"));
                PlayList playList = plDao.readPlaylistDetailsById(id);
                if (playList != null) {
                    request.getSession().setAttribute("PlayList", playList);
                    paginaDestino = "/playlistdetails.jsp";

                } else {
                    request.setAttribute("erroSTR", "Erro ao recuperar Playlist!");
                }
            } catch (Exception ex) {
                request.setAttribute("erroSTR", "Erro Inesperado!");
                ex.printStackTrace();
            }
        }
        else{
            request.setAttribute("erroSTR","Voce nao esta conectado!");
        }
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(paginaDestino);
        dispatcher.forward(request, response);

    }

}
