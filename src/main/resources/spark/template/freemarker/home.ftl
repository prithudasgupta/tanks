<#assign content>
    
<div id="main" >

<img id="friends" src = "images/friendsIcon.png" onmouseover="this.src='/images/friendsIcon_selected.png';"
     onmouseout="this.src='/images/friendsIcon.png';">

<center>    
<img id="logo" src="images/logo.png" >
</center>

<br />
<br />


<img id="campBut" src='/images/campaign.png' onmouseover="this.src='/images/campaign_hover.png';"
     onmouseout="this.src='/images/campaign.png';" style="position: absolute; right: 10%; top: 25%;" />
<br />
<img id="menuOp" src='/images/survival.png' onmouseover="this.src='/images/survival_hover.png';"
     onmouseout="this.src='/images/survival.png';" style="position: absolute; right: 10%; top: 40%;"/>
<br />
<img id="menuOp" src='/images/mapBuilder.png' onmouseover="this.src='/images/mapBuilder_hover.png';"
     onmouseout="this.src='/images/mapBuilder.png';" style="position: absolute; right: 10%; top: 55%;" />
<br />
<br />

</div>

<div id="campaign">
    <img id="exit" src="/images/exit.png" onmouseover="this.src='/images/exit_hover.png';"
         onmouseout="this.src='/images/exit.png';" style="position: absolute; top: 2px; right: 2px;">
    <center>
        <h2> Select level </h2>
    </center>
    <div id="levels" style="top: 15%; left: 25%; position: absolute; height: 75%; width: 50%;">
    </div>
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
    top: 20%;
    left: 10%;
    width: 75%;
    height: 75%;
    background-image: url("/sprites/menu.png");
    background-repeat: repeat;
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

#friends {
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