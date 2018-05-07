<#assign content>

<button id="submitLevel"> Add tanks </button>

<button id="submitLevelFinal"> Submit </button>

<button id="home" onclick="visitPage('Main')"> Home </button>
<#--<div id="backgroundImg">-->
    <#--<img id="img" src="/images/mapBuildLogo.png">-->
<#--</div>-->


<style>

    body {
        background-image: url("/sprites/menu.png");
        background-repeat: repeat;
    }

    #submitLevel {
        position: absolute;
        z-index: 101;
        left: 1160px;
        top: 600px;
        /*display: block;*/
        /*width: 45px;*/
        /*height: 45px;*/
        width: 90px;
        min-height: 45px;
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