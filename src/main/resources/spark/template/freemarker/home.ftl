<#assign content>
    
<div id="main" >

<img id="profileBut" src = "images/friendsIcon.png" onmouseover="this.src='/images/friendsIcon_selected.png';"
     onmouseout="this.src='/images/friendsIcon.png';">

<center>    
<img id="logo" src="images/logo.png" >
</center>

<br />
<br />


<img id="campBut" src='/images/campaign.png' onmouseover="this.src='/images/campaign_hover.png';"
     onmouseout="this.src='/images/campaign.png';" style="position: absolute; right: 10%; top: 10%;" />
<br />
<img id="menuOp" src='/images/survival.png' onmouseover="this.src='/images/survival_hover.png';"
     onmouseout="this.src='/images/survival.png';" style="position: absolute; right: 10%; top: 27%;"/>
<br />
<img id="menuOp" src='/images/mapBuilder.png' onmouseover="this.src='/images/mapBuilder_hover.png';"
     onmouseout="this.src='/images/mapBuilder.png';" style="position: absolute; right: 10%; top: 44%;" />
<br />
<img id="menuOp" src='/images/multiplayer.png' onmouseover="this.src='/images/multiplayer_hover.png';"
     onmouseout="this.src='/images/multiplayer.png';" style="position: absolute; right: 10%; top: 61%;" />
<br />
<img id="menuOp" src='/images/leaderboards.png' onmouseover="this.src='/images/leaderboards_hover.png';"
     onmouseout="this.src='/images/leaderboards.png';" style="position: absolute; right: 10%; top: 78%;" />
<br />
<br />

</div>

<div id="campaign">
    <img id="exitCamp" src="/images/exit.png" onmouseover="this.src='/images/exit_hover.png';"
         onmouseout="this.src='/images/exit.png';" style="position: absolute; top: 2px; right: 2px;">
    <center>
        <h2> Select level </h2>
    </center>
    <div id="levels" style="top: 15%; left: 25%; position: absolute; height: 75%; width: 50%;">
    </div>
</div>

<div id="profile">
    <img id="exitProf" src="/images/exit.png" onmouseover="this.src='/images/exit_hover.png';"
         onmouseout="this.src='/images/exit.png';" style="position: absolute; top: 2px; right: 2px;">
    <center>
        <h2> Profile </h2>
    </center>
</div>


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
    z-index: 100;
}

#campaign {
    position: absolute;
    top: 12.5%;
    left: 10%;
    width: 75%;
    height: 75%;
    background-image: url("/sprites/menu.png");
    background-repeat: repeat;
    display: none;
    z-index: 99;
 }

#profile {
    position: absolute;
    top: 12.5%;
    left: 10%;
    width: 75%;
    height: 75%;
    background-color: limegreen;
    display: none;
    z-index: 99;
}

#logo {
    float: left;
    top: 0;
    left: 0;
    padding: none;
    position: absolute;
}

a{
color: black;
}

a:hover{
color: red;
}

#profileBut {
    position: absolute;
    top: 0;
    right: 5px;
    padding: none;
}

#level {
    position: relative;
    padding: 18px;
}


</style>

</#assign>
<#include "main.ftl">
<script src="/js/home.js"></script>