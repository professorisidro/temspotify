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

        <style>
            .musica{
                font-family: Verdana;
                display: block;
                font-style: bold;
                height: 45px;
                width: 100%;
                margin: 10px;
                color: black;
                padding-left: 50px;
                padding-top: 15px;
                background-color: #66ff66;
                border-radius: 5px;
                background-image: url('./images/play.png');
                background-repeat: no-repeat;
                background-size: 45px 45px;
                background-position: left top;
            }
            #musicplayer{
                width: 100%;
                position: fixed;
                bottom: 0;
            }
            #nowPlaying{
                width:100%;
                border-radius: 10px;
                height: 50px;
                background-color: #ccff66;
                color: black;
            }
            
            #playerContent{
                margin-bottom: 80px;
            }

        </style>
        <script type="text/javascript">
            var musics = new Array(); // lista de musicas
            var repeat = false;       // se a playlist vai ficar em moto repeat
            var currentSong = 0;
            var totalMusicas = 0;
            var URL = "http://localhost:8084/temspotify/";
            var player;


            function setupPlayer() {
                var divMusicas = document.getElementById("playerContent");
                var filhos = divMusicas.childNodes;
                for (i = 0; i < filhos.length; i++) {
                    if (filhos[i].nodeName === "DIV") {
                        musics.push(filhos[i].id);
                        totalMusicas++;
                    }
                }
                console.log(musics);
                console.log(repeat);
                console.log(totalMusicas);

                player = document.getElementById("musicplayer");
                // colocando a musica inicial
                player.src = URL + musics[0];
                document.getElementById("nowPlaying").innerHTML="Now Playing:" + document.getElementById(musics[currentSong]).innerHTML;

                // funcao para quando terminar a musica
                player.onended = function () {
                    if (currentSong < musics.length - 1) {
                        currentSong = currentSong + 1;
                        player.src = URL + musics[currentSong];
                        player.play();
                    } else {
                        if (repeat) {
                            console.log("estou em modo REPEAT");
                            currentSong = 0;
                            player.src = URL + musics[currentSong];
                            player.play();
                        } else {
                            alert("Fim das musicas");
                        }
                    }
                    console.log("Musica atual = " + currentSong);
                    document.getElementById("nowPlaying").innerHTML="Now Playing:" + document.getElementById(musics[currentSong]).innerHTML;
                }
            }

            function changeRepeat() {
                repeat = !repeat;
                if (repeat) {
                    document.getElementById("imgRepeat").src = "images/repeat_green.png";
                } else {
                    document.getElementById("imgRepeat").src = "images/repeat_gray.png";
                }
            }
            function play(objetoMusica) {
                console.log(objetoMusica.id);
                for (i = 0; i < musics.length; i++) {
                    if (musics[i] === objetoMusica.id) {
                        currentSong = i;

                        player.src = URL + musics[currentSong];
                        player.play();
                        
                        document.getElementById("nowPlaying").innerHTML="Now Playing:" + document.getElementById(musics[currentSong]).innerHTML;

                    }
                }
            }
        </script>

    </head>
    <body onLoad="setupPlayer();">

        <div class="container-fluid">
            <div class="row">
                <div class="col-md-12">
                    <a href="playlists"><img src="images/logo.png" class="rounded mx-auto d-block" width="15%" align="center"/></a>
                    <img id="imgRepeat" src="images/repeat_gray.png" class="rounded mx-auto d-block" width="5%" onclick="changeRepeat();" align="center"/>
                </div>
            </div>
        </div>
        <div id="playerContent">
            <c:forEach var="music"   items="${PlayList.musicas}">
                <div id="${music.linkMP3}"  class="musica" onclick="play(this);">
                    ${music.titulo} (${music.artista})
                </div>
            </c:forEach>
        </div>

        
        <div id="playerdiv">
            <div id="nowPlaying"> Now Playing: </div>
            
            <audio id="musicplayer" controls controlsList="nodownload" src=""/>
        </div>



        <script src="js/jquery.min.js"></script>
        <script src="js/bootstrap.min.js"></script>
        <script src="js/scripts.js"></script>

    </body>
</html>
