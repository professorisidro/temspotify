/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.professorisidro.temspotify.controller;

import br.com.professorisidro.temspotify.model.PlayList;
import br.com.professorisidro.temspotify.model.Usuario;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author isidro
 */
public class PlayerServlet extends HttpServlet {

    
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
        String paginaDestino="/error.jsp";
        Usuario usuario = (Usuario)request.getSession().getAttribute("Usuario");
        if (usuario != null){
            PlayList playList = (PlayList)request.getSession().getAttribute("PlayList");
            if (playList != null){
                paginaDestino = "/player.jsp";
            }
            else{
                request.setAttribute("erroSTR", "Playlist nao econtrada!");
            }
        }
        else{
            request.setAttribute("erroSTR","Erro! Usuario nao conectado!");
        }
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(paginaDestino);
        dispatcher.forward(request, response);       
    }  
}
