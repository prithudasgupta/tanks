

<#assign content>
    <canvas id="canvas"></canvas>

    <div id="sideMenu">
        <center>
            <header id="levNumber"> Game Level : </header>
        <header id="timer"> Time : 0:00 </header>
        <header id="kills"> Kills : 0 </header>
        <button id="menu" onclick="visitPage('Main')"> Menu </button>

            <table id="leaders" style="width:100%">
                <tr>
                    <th>User</th>
                    <th>Time</th>
                </tr>
            </table>

        </center>
    </div>

    <div id="endGame">
        <center>
            <h3 id="result"> GAME WON!</h3>

        <button class="endBut" id="Main" onclick="visitPage('Main')">
            Return to Main Menu
        </button>

        <button class="endBut" id="retry" onclick="visitPage('')">
            Retry?
        </button>
        <header id="enemyKill"> </header>
        <header id="time"> </header>
        <header id="bestTime"> Best Time : 00:00 </header>
        <button class="endBut" id="next" onclick="visitPage('Next')"> Next Level </button>
        </center>
    </div>

<style>

    body {
        background-image: url("/sprites/menu.png");
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

    #timer {
        color: white;
        font-size: xx-large;
        top: 12%;
        left: 15%;
        position: absolute;
    }

    #levNumber {
        color: white;
        font-size: xx-large;
        top: 4%;
        left: 15%;
        position: absolute;
    }

    #kills {
        color: white;
        font-size: xx-large;
        top: 20%;
        left: 15%;
        position: absolute;
    }

    #menu {
        display: block;
        margin-right: 25%;
        width: 50%;
        height: 45px;
        top: 30%;
    }

    .endBut {
        display: block;
        width: 40%;
        height: 45px;
        padding: none;

    }

    th, td {
        padding: 5px;
        text-align: center;
    }

    #leaders {
        position: absolute;
        top: 50%;
        font-size: x-large;
    }

</style>
</#assign>
<#include "main.ftl">
<script src="/js/gameMain.js"></script>