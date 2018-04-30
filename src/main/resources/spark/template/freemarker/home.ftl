<#assign content>
    
    
<img src = "images/friendsIcon.png" onmouseover="this.src='/images/friendsIcon_selected.png';" onmouseout="this.src='/images/friendsIcon.png';">
<center>    
<h1>DESKTOP TANKS</h1>
</center>

<br />
<br />

<#--<img id = "tank" src = "images/homepagetank.png" style="width:30%;height:30%; margin-left: 10%;" ></img>-->

<#--<img id = "img1" src = "/images/campaign.png">-->
<#--<br />-->
<#--<br />-->
<img id="menuOp" src='/images/campaign.png' onmouseover="this.src='/images/campaign_hover.png';" onmouseout="this.src='/images/campaign.png';" style="position: absolute; right: 10%; top: 25%;" />
<br />
<img id="menuOp" src='/images/survival.png' onmouseover="this.src='/images/survival_hover.png';" onmouseout="this.src='/images/survival.png';" style="position: absolute; right: 10%; top: 40%;"/>
<br />
<img id="menuOp" src='/images/mapBuilder.png' onmouseover="this.src='/images/mapBuilder_hover.png';" onmouseout="this.src='/images/mapBuilder.png';" style="position: absolute; right: 10%; top: 55%;" />
<#--<img id = "img2" src = "/images/survival.png" ></img>-->
<br />
<br />




<#--<div class="menuoption" style="position: absolute; width:25%;height:25%; right:30%;bottom: 15%;">-->
<#--<img id = "img4" src = "images/stickynote.png" style="width:90%;height:90%;" ></img>-->
<#--<div class="centeredOption">-->
<#--<a >Multiplayer</a>-->
<#--</div>-->
<#--</div>-->

<#--<div class="menuoption" style="position: absolute; width:25%;height:25%; right:5%;bottom: 15%;">-->
<#--<img id = "img5" src = "images/stickynote.png" style="width:90%;height:90%;" ></img>-->
<#--<div class="centeredOption">-->
<#--<a >Leaderboards</a>-->
<#--</div>-->
<#--</div>-->


<style>
body {
   background-image: url("sprites/freeSpace.png");
   background-repeat: repeat;
}

h1 {
	font-family: Impact, Haettenschweiler, "Franklin Gothic Bold", Charcoal, "Helvetica Inserat", "Bitstream Vera Sans Bold", "Arial Black", "sans serif";
	font-size: 75px;
	font-style: normal;
	font-variant: normal;
	font-weight: 500;
}

.menuoption {
    text-align: center;
    color: white;
}



.centeredOption {
    position: absolute;
    top: 50%;
    left: 50%;
    transform: translate(-50%, -50%);
    font-family: Impact, Haettenschweiler, "Franklin Gothic Bold", Charcoal, "Helvetica Inserat", "Bitstream Vera Sans Bold", "Arial Black", "sans serif";
    font-size: 25px;
    color: black;
    font-style: italic;
}

a{
color: black;
}

a:hover{
color: red;
}

#menuOp {
    
}



#img1 {
    background-image: url("/images/campaign.png");
}

#img1:hover {
    background-image: url("/images/campaign_hover.png");
}
#img2:hover {
    background-image: url("/images/survival_hover.png");
}
#img3:hover {
    background-image: url("/images/mapBuilder_hover.png");
}

#sjs0 {
    position: absolute;
    top: 0%;
    left: 5%;
}

</style>

</#assign>
<#include "main.ftl">
<script src="/js/home.js"></script>