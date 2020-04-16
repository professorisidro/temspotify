/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.professorisidro.temspotify.controller;

import br.com.professorisidro.temspotify.dao.DataSource;
import br.com.professorisidro.temspotify.dao.PlayListDAO;
import br.com.professorisidro.temspotify.model.PlayList;
import br.com.professorisidro.temspotify.model.Usuario;
import java.io.IOException;
import java.util.ArrayList;
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
public class PlaylistsServlet extends HttpServlet {

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
        
        String paginaDestino = "/myplaylists.jsp";
        
        try {
            Usuario usuario = (Usuario) request.getSession().getAttribute("Usuario");
            if (usuario != null) {
                // tá logado
                if (usuario.getPlaylists() == null) { // nao tem playlist?

                    // recupero do banco
                    DataSource dataSource = new DataSource();
                    PlayListDAO plDAO = new PlayListDAO(dataSource);
                    List<Object> lista = plDAO.read(usuario.getId());
                    dataSource.getConnection().close();
                    
                    

                    // vou pasar por cada elemento do q veio do banco e vou referenciar o usuario
                    if (lista != null) {
                        ArrayList<PlayList> myPlaylists = new ArrayList<PlayList>();
                        for (Object o : lista) {
                            PlayList novaPl = (PlayList) o;
                            novaPl.setUsuario(usuario);
                            myPlaylists.add(novaPl);
                        }
                        usuario.setPlaylists(myPlaylists);

                    }

                }
                request.getSession().setAttribute("Usuario", usuario);
                paginaDestino = "/myplaylists.jsp";
            }
        } catch (Exception ex) {
            System.out.println("Erro ao recuperar Playlists " + ex.getMessage());
        }
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(paginaDestino);
        dispatcher.forward(request, response);
    }

}
