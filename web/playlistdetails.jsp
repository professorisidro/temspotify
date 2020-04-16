<!DOCTYPE html>
<jsp:useBean id="Usuario" type="br.com.professorisidro.temspotify.model.Usuario" scope="session"/>
<jsp:useBean id="PlayList" type="br.com.professorisidro.temspotify.model.PlayList" scope="session"/>
<%@taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">

        <title>.: TemSpotify by TemAula! :.</title>


        <link href="css/bootstrap.min.css" rel="stylesheet">
        <link href="css/style.css" rel="stylesheet">

    </head>
    <body>

        <div class="container-fluid">
            <div class="row">
                <div class="col-md-12">
                    <img src="images/logo.png" class="rounded mx-auto d-block" width="15%" align="center"/>
                </div>
            </div>
            <div class="row">
                <div class="col-md-12">

                    <h3 class="text-center">
                        Tem Spotify - Sua playlist na Web!
                    </h3>

                </div>

            </div>
            <div class="row">
                <div class="col-md-12">

                    <h4 class="text-center">
                        Detalhes da Playlist
                    </h4>

                </div>

            </div>

            <div class="row" id="conteudo">
                <div class="col-md-2">
                    &nbsp;                  
                </div>
                <div class="col-md-2 botao btnlink">
                    <span class="text-center"><a class="botaospt" href="novamusica">Upload</a></span>
                </div>
                <div class="col-md-2 botao btnlink">
                    <span class="text-center"><a class="botaospt" href="playlists">Playlists</a></span>
                </div>
                <div class="col-md-2  botao btnlink">
                    <span class="text-center"><a class="botaospt" href="novaplaylist">Add Playlist</a></span>
                </div>
                <div class="col-md-2  botao btnlink">
                    <span class="text-center"><a class="botaospt" href="logout">Logout</a> </span>
                </div>
                <div class="col-md-2">
                    &nbsp;
                </div>

            </div>
            <div class="row">
                <div class="col-md-2">&nbsp;</div>
                <div class="col-md-8">
                    <h4>${PlayList.titulo} <a href="player"><img id="imgplay" src="images/play.png" alt="Tocar Playlist" title="Tocar Playlist"></a></h4>
                </div>
                <div class="col-md-2">&nbsp;</div>
            </div>

            <div class="row">
                <div class="col-md-2">&nbsp;</div>
                <div class="col-md-8">
                    <h5> <a href="./recuperamusicas?idplaylist=${PlayList.id}">+ Adicionar M&uacute;sicas</a> </h5>
                </div>
                <div class="col-md-2">&nbsp;</div>
            </div>

            <c:forEach var="Musica" items="${PlayList.musicas}">
                <div class="row">
                    <div class="col-md-2">
                        &nbsp;
                    </div>
                    <div class="col-md-8">
                        <span class="tituloMusica">
                            ${Musica.titulo}
                        </span>
                        <span class="artista">
                            ${Musica.artista} (Album: ${Musica.album})
                        </span>

                    </div>
                    <div class="col-md-2">
                        &nbsp;
                    </div>
                </div>
            </c:forEach>    




        </div>
        <script src="js/jquery.min.js"></script>
        <script src="js/bootstrap.min.js"></script>
        <script src="js/scripts.js"></script>
    </body>
</html>