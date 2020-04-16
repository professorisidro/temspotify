/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.professorisidro.temspotify.dao;

import br.com.professorisidro.temspotify.model.Musica;
import br.com.professorisidro.temspotify.model.PlayList;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author isidro
 */
public class PlayListDAO implements GenericDAO {

    private DataSource dataSource;

    public PlayListDAO(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public void create(Object o) {
        try {
            PlayList pl = (PlayList) o;
            String SQL = "INSERT INTO tblPlaylist VALUES (null, ?, ?)";
            PreparedStatement stm = dataSource.getConnection().prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS);
            stm.setString(1, pl.getTitulo());
            stm.setInt(2, pl.getUsuario().getId());
            int res = stm.executeUpdate();
            if (res == 0) {
                throw new RuntimeException("Nao foi possivel incluir playlist!");
            }
            ResultSet rs = stm.getGeneratedKeys();
            if (rs.next()) {
                pl.setId(rs.getInt(1));
            }
        } catch (SQLException ex) {
            System.out.println("Erro ao cadastrar PlayList " + ex.getMessage());
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
        try {
            String SQL = "SELECT * FROM tblPlaylist WHERE idUsuario = ?";
            Integer idUser = (Integer) o;
            PreparedStatement stm = dataSource.getConnection().prepareStatement(SQL);
            stm.setInt(1, idUser.intValue());
            ResultSet rs = stm.executeQuery();
            ArrayList<Object> list = new ArrayList<Object>();
            while (rs.next()) {
                PlayList pl = new PlayList();
                pl.setId(rs.getInt("idPlaylist"));
                pl.setTitulo(rs.getString("titulo"));
                list.add(pl);
            }
            rs.close();
            stm.close();
            return list;
        } catch (SQLException ex) {
            System.out.println("Erro ao recuperar Playlists " + ex.getMessage());
        }
        return null;
    }

    public PlayList readPlaylistDetailsById(int id) {
        PlayList playlist = null;
        try {
            String SQL = "select tblPlaylist.idPlaylist as idPlaylist, "
                    + "       tblPlaylist.idUsuario  as idUsuario,  "
                    + "       tblPlaylist.titulo     as pl_titulo, "
                    + "	      tblMusica.idMusica     as idMusica, "
                    + "	      tblMusica.titulo       as mu_titulo, "
                    + "       tblMusica.artista      as artista, "
                    + "       tblMusica.album        as album, "
                    + "       tblMusica.estilo       as estilo, "
                    + "       tblMusica.linkMP3      as linkMP3 "
                    + "   from  "
                    + "     tblPlaylist "
                    + "     left outer join tblMusicaPlaylist using (idPlaylist) "
                    + "	 left outer join tblMusica using (idMusica) "
                    + "     where idPlaylist = ?";
            PreparedStatement stm = dataSource.getConnection().prepareStatement(SQL);
            stm.setInt(1, id);
            ResultSet rs = stm.executeQuery();
            rs.next();
            do {
                if (playlist == null) {
                    playlist = new PlayList();
                    playlist.setMusicas(new ArrayList<Musica>());
                    playlist.setId(rs.getInt("idPlaylist"));
                    playlist.setTitulo(rs.getString("pl_titulo"));
                }
                if (rs.getString("mu_titulo") != null) {
                    Musica musica = new Musica();
                    musica.setId(rs.getInt("idMusica"));
                    musica.setTitulo(rs.getString("mu_titulo"));
                    musica.setArtista(rs.getString("artista"));
                    musica.setEstilo(rs.getInt("estilo"));
                    musica.setAlbum(rs.getString("album"));
                    musica.setLinkMP3(rs.getString("linkMP3"));
                    playlist.getMusicas().add(musica);
                }

            } while (rs.next());
            return playlist;

        } catch (Exception ex) {
            System.out.println("Erro ao recuperar detalhes da Playlist " + ex.getMessage());
        }
        return null;

    }
    
    public boolean createMusicaPlaylist(int idPlaylist, int idMusica){
        try{
            String SQL = "insert into tblMusicaPlaylist values (?, ?)";
            PreparedStatement stm = dataSource.getConnection().prepareStatement(SQL);
            stm.setInt(1, idPlaylist);
            stm.setInt(2, idMusica);
            int resultado = stm.executeUpdate();
            if (resultado == 1){
                return true;
            }
        }
        catch(SQLException ex){
            System.out.println("Erro ao inserir "+ex.getMessage());
        }
        return false;
        
    }

}
