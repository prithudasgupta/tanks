

<#assign content>
    <canvas id="canvas"></canvas>

    <div id="sideMenu">
        side menu
        <header id="timer"> 0:00 </header>
        <button id="menu"> menu </button>
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
</style>
</#assign>
<#include "main.ftl">
<script src="/js/game.js"></script>