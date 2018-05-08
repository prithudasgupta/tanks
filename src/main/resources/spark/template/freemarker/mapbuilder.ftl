<#assign content>

<img id="information" src="/images/information.png" onclick="information()">

<button id="submitLevelFinal"> Submit </button>

<button id="home" onclick="visitPage('Main')"> Home </button>

 <div id="tankInformation">
     <img id="exitTut" src="/images/exit.png" onmouseover="this.src='/images/exit_hover.png';"
          onmouseout="this.src='/images/exit.png';" onclick="informationOff();" style="position: absolute; top: 6px; right: 9px;">
     <center><h3>Information:</h3></center>
     <img  src="/sprites/userTankSelect.png" style="left: 10%; top: 15%; position: absolute;">
     <p style="left: 25%; top: 15%; position: absolute;"> User Tank </p>
     <img  src="/sprites/statTankSelect.png" style="left: 10%; top: 30%; position: absolute;">
     <p style="left: 25%; top: 27%; position: absolute; width: 70%"> Stationary Tank : Will fire when in line of sight of the user. </p>
     <img  src="/sprites/dumbTankSelect.png" style="left: 10%; top: 45%; position: absolute;">
     <p style="left: 25%; top: 42%; position: absolute; width: 70%"> Random Tank : Will randomly move around
         the map, and fire when in line of sight. </p>
     <img  src="/sprites/pathTankSelect.png" style="left: 10%; top: 63%; position: absolute;">
     <p style="left: 25%; top: 57%; position: absolute; width: 70%"> Path Tank : Will move back and forth to the
     point given on creation, fires when in line of sight with the user.</p>
     <img  src="/sprites/homingTankSelect.png" style="left: 10%; top: 82%; position: absolute;">
     <p style="left: 25%; top: 78%; position: absolute; width: 70%"> Homing Tank : Will follow shortest path to the user, fires when in line of sight with the user.</p>
 </div>


<style>

    body {
        background-image: url("/sprites/menu.png");
        background-repeat: repeat;
    }

    #tankInformation {
        border-radius: 25px;
        position: absolute;
        top: 15%;
        left: 30%;
        width: 35%;
        height: 50%;
        background-color: #F2F2F2;
        z-index: 100;
        display: none;
    }

    #submitLevel {
        position: absolute;
        z-index: 101;
        left: 1160px;
        top: 600px;
        width: 90px;
        min-height: 45px;
      }

    #information {
        position: absolute;
        z-index: 95;
        top: 500px;
        left: 1215px;
        height: auto;
        width: auto;

        max-width: 45px;
        max-height: 45px;
    }

    #home {
        position: absolute;
        z-index: 101;
        left: 1160px;
        top: 630px;
        /*display: block;*/
        /*width: 45px;*/
        /*height: 45px;*/
        width: 90px;
        min-height: 45px;
    }

    #idEntry {
        position: absolute;
        z-index: 100;
        left: 1175px;
        top: 450px;
        /*display: block;*/
        /*width: 45px;*/
        /*height: 45px;*/
        width: 30px;
        height: 15px;
    }

    #submitLevelFinal {
        position: absolute;
        z-index: 101;
        left: 1160px;
        top: 600px;
        /*display: block;*/
        /*width: 45px;*/
        /*height: 45px;*/
        min-width: 90px;
        min-height: 45px;
        display: none;
    }

    #backgroundImg {
        z-index: 99;
        position: absolute;
        top: 25%;
        left: 15%;
    }

    #img {
        user-drag: none;
        user-select: none;
        -moz-user-select: none;
        -webkit-user-drag: none;
        -webkit-user-select: none;
        -ms-user-select: none;
    }

</style>

</#assign>
<#include "main.ftl">
<script src="/js/mapbuilder.js"></script>