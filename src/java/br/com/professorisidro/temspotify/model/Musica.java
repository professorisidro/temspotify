/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.professorisidro.temspotify.model;

/**
 *
 * @author isidro
 */
public class Musica implements java.io.Serializable{
    private int id;
    private String titulo;
    private String artista;
    private String album;
    private int    estilo;
    private String linkMP3;

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the titulo
     */
    public String getTitulo() {
        return titulo;
    }

    /**
     * @param titulo the titulo to set
     */
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    /**
     * @return the artista
     */
    public String getArtista() {
        return artista;
    }

    /**
     * @param artista the artista to set
     */
    public void setArtista(String artista) {
        this.artista = artista;
    }

    /**
     * @return the album
     */
    public String getAlbum() {
        return album;
    }

    /**
     * @param album the album to set
     */
    public void setAlbum(String album) {
        this.album = album;
    }

    /**
     * @return the estilo
     */
    public int getEstilo() {
        return estilo;
    }

    /**
     * @param estilo the estilo to set
     */
    public void setEstilo(int estilo) {
        this.estilo = estilo;
    }

    /**
     * @return the linkMP3
     */
    public String getLinkMP3() {
        return linkMP3;
    }

    /**
     * @param linkMP3 the linkMP3 to set
     */
    public void setLinkMP3(String linkMP3) {
        this.linkMP3 = linkMP3;
    }
    
    
}
