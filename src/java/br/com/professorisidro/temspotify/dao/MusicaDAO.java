/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.professorisidro.temspotify.dao;

import br.com.professorisidro.temspotify.model.Musica;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author isidro
 */
public class MusicaDAO implements GenericDAO{
    private DataSource dataSource;
    public MusicaDAO(DataSource dataSource){
        this.dataSource = dataSource;
    }

    @Override
    public void create(Object o) {
        try{
            if (o instanceof Musica){
                Musica musica = (Musica)o;
                String SQL = "INSERT INTO tblMusica VALUES (null, ?, ?, ?, ?, ?)";
                PreparedStatement stm = dataSource.getConnection().prepareStatement(SQL);
                stm.setString(1, musica.getTitulo());
                stm.setString(2, musica.getArtista());
                stm.setString(3, musica.getAlbum());
                stm.setInt(4, musica.getEstilo());
                stm.setString(5, musica.getLinkMP3());
                stm.executeUpdate();
                stm.close();
            }
        }
        catch(SQLException ex){
            ex.printStackTrace();
        }
    }

    @Override
    public void update(Object o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(Object o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Object> read(Object o) {
        try{
            String SQL = "Select * from tblMusica order by titulo";
            PreparedStatement stm = dataSource.getConnection().prepareStatement(SQL);
            ResultSet rs = stm.executeQuery();
            List<Object> lista = new ArrayList<Object>();
            while (rs.next()){
                Musica musica = new Musica();
                musica.setId(rs.getInt("idMusica"));
                musica.setTitulo(rs.getString("titulo"));
                musica.setArtista(rs.getString("artista"));
                musica.setAlbum(rs.getString("album"));
                musica.setEstilo(rs.getInt("estilo"));
                musica.setLinkMP3(rs.getString("linkMP3"));
                lista.add(musica);
            }
            return lista;
        }
        catch(SQLException ex){
            System.out.println("Erro ao recuperar acervo de musicas "+ex.getMessage());
        }
        return null;
    }
    
    
}
