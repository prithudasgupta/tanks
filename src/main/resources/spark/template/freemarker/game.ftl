

<#assign content>
    <canvas id="canvas"></canvas>

    <div id="sideMenu">
        <center>
            <header id="levNumber"> Game Level : </header>
        <header id="timer"> Time : 0:00 </header>
        <header id="kills"> Kills : 0 </header>
        <button id="menu" onclick="visitPage('Main')"> Menu </button>
        <img id="information" src="/images/information.png"  onclick="showInformation();" style="position: absolute; top: 0; right: 0;" >

            <table id="leaders" style="width:100%">
                <tr>
                    <th>User</th>
                    <th>Time</th>
                </tr>
            </table>

        </center>
    </div>

     <div id="tankInformation">
         <img id="exitTut" src="/images/exit.png" onmouseover="this.src='/images/exit_hover.png';"
              onmouseout="this.src='/images/exit.png';" onclick="showInformation();" style="position: absolute; top: 6px; right: 9px;">
         <center><h3>Information:</h3></center>
         <div id="statT">
             <img  src="/sprites/statTankSelect.png" style="left: 10%; top: 15%; position: absolute;">
             <p style="left: 25%; top: 12%; position: absolute; width: 70%"> Stationary Tank : Will fire when in line of sight of the user. </p>
         </div>
        <div id="dumbT">
            <img  src="/sprites/dumbTankSelect.png" style="left: 10%; top: 35%; position: absolute;">
            <p style="left: 25%; top: 28%; position: absolute; width: 70%"> Random Tank : Will randomly move around
                the map, and fire when in line of sight. </p>
        </div>
         <div id="pathT">
             <img  src="/sprites/pathTankSelect.png" style="left: 10%; top: 55%; position: absolute;">
             <p style="left: 25%; top: 48%; position: absolute; width: 70%"> Path Tank : Will move back and forth to the
                 point given on creation, fires when in line of sight with the user.</p>
         </div>
         <div id="homingT">
             <img  src="/sprites/homingTankSelect.png" style="left: 10%; top: 78%; position: absolute;">
             <p style="left: 25%; top: 72%; position: absolute; width: 70%"> Homing Tank : Will follow shortest path to the user
                 , fires when in line of sight with the user.</p>
         </div>
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

 <div id="tutorial">
     <img id="exitTut" src="/images/exit.png" onmouseover="this.src='/images/exit_hover.png';"
          onmouseout="this.src='/images/exit.png';" onclick="exitTutorial();" style="position: absolute; top: 6px; right: 9px;">

     <center><h3>Tutorial:</h3></center>
     <img  src="/images/wasd.png" style="left: 5%; position: absolute;">
     <p id="movementInstruction" style="position: absolute; left: 37%; display: inline-block; max-width: 50%;"> Move your tank forward and backwards using W,
         and S.  Rotate your tank using A, and D. </p>
     <img  src="/images/spacebar.png" style="left: 5%; top: 44%; position: absolute;">
     <p id="movementInstruction" style="position: absolute; top: 40%; left: 37%; display: inline-block; max-width: 50%;"> Pressing space bar or
         clicking the mouse will fire a bullet to where your mouse is aimed.</p>

     <img  src="/images/mouse.png" style="left: 5%; top: 62%; left: 80%; position: absolute;">

     <img  src="/sprites/tank.png" style="left: 5%; top: 80%; left: 15%; position: absolute;">
     <img  src="/sprites/tank_cannon.png" style="left: 5%; top: 80%; transform: rotate(-10deg); left: 15%; position: absolute;">
     <img  src="/sprites/tank_cannon.png" style="left: 5%; top: 80%; transform: rotate(-10deg); left: 15%; position: absolute;">
     <img  src="/sprites/bullet.png" style="left: 30%; top: 78%; transform: rotate(-10deg); position: absolute;">
     <img  src="/sprites/bullet.png" style="left: 40%; top: 76%; transform: rotate(-10deg); position: absolute;">
     <img  src="/sprites/bullet.png" style="left: 50%; top: 74%; transform: rotate(-10deg); position: absolute;">
     <img  src="/sprites/bullet.png" style="left: 60%; top: 72%; transform: rotate(-10deg); position: absolute;">
     <img  src="/sprites/bullet.png" style="left: 70%; top: 70%; transform: rotate(-10deg); position: absolute;">
     <img  src="/sprites/bullet.png" style="left: 80%; top: 68%; transform: rotate(-10deg); position: absolute;">

 </div>

<style>

    body {
        background-image: url("/sprites/menu.png");
        background-repeat: repeat;
    }

    #tankInformation {
        border-radius: 25px;
        position: absolute;
        bottom: 0;
        right: 0;
        left: 1080px;
        height: 50%;
        background-color: #F2F2F2;
        z-index: 100;
        display: none;
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

    #tutorial {
        border-radius: 25px;
        position: absolute;
        top: 15%;
        left: 20%;
        width: 50%;
        height: 50%;
        background-color: #F2F2F2;
        display: none;
        z-index: 100;
    }

    #endGame {
        border-radius: 25px;
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