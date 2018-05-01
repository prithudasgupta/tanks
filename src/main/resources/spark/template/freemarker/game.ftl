

<#assign content>
    <canvas id="canvas"></canvas>

    <div id="sideMenu">
        side menu
        <header id="timer"> 0:00 </header>
        <button id="menu"> menu </button>
    </div>

    <div id="endGame">
        <center>
            <h3> Game over! </h3>

        <button id="Main">
            Return to Main Menu
        </button>
        <br>
        <button>
            Retry?
        </button>
        <h1 id="enemyKill"></h1>
        <h1 id="time"></h1>
        <button> Next Level </button>
        </center>
    </div>

<style>

    body {
        background-image: url("sprites/wall.png");
        background-repeat: repeat;
    }

    #sideMenu {
        position: absolute;
        right: 0;
        /*width: 360px;*/
        float: right;
        height: 100%;
        background-image: url("/sprites/menu.png");
        background-repeat: repeat;
        z-index: 99;
    }

    #endGame {
        position: absolute;
        top: 15%;
        left: 20%;
        width: 50%;
        height: 50%;
        background-color: darkorange;
        background-repeat: repeat;
        display: none;
        z-index: 100;
    }

</style>
</#assign>
<#include "main.ftl">
<script src="/js/gameMain.js"></script>