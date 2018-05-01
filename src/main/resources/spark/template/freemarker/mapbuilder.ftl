<#assign content>

<button id="submitLevel"> Add tanks </button>

<button id="submitLevelFinal"> Submit </button>

<style>

    #submitLevel {
         position: absolute;
         z-index: 100;
         left: 1150px;
         top: 450px;
         /*display: block;*/
         /*width: 45px;*/
         /*height: 45px;*/
         min-width: 90px;
         min-height: 45px;
     }

    #submitLevelFinal {
        position: absolute;
        z-index: 101;
        left: 1160px;
        top: 635px;
        /*display: block;*/
        /*width: 45px;*/
        /*height: 45px;*/
        min-width: 90px;
        min-height: 45px;
        display: none;
    }

</style>

</#assign>
<#include "main.ftl">
<script src="/js/mapbuilder.js"></script>