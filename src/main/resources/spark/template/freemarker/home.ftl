<#assign content>
    
<center>    
<h1>DESKTOP TANKS</h1>
</center>

<br />
<br />

<#--<img id = "tank" src = "images/homepagetank.png" style="width:30%;height:30%; margin-left: 10%;" ></img>-->

<div class="menuoption" onmouseover="this.style.color='red';" onmouseout="this.style.color='blacḱ';" style="position: absolute; width:25%;height:25%; right:30%;bottom: 55%;">
<img id = "img1" src = "images/stickynote.png" style="width:90%;height:90%;"></img>

<div class="centeredOption">
<a href= "/campaign">Play Campaign</a>
</div>

</div>

<div class="menuoption" style="position: absolute; width:25%;height:25%; right:5%;bottom: 55%;">
<img id = "img2" src = "images/stickynote.png" style="width:90%;height:90%; right: 0px;bottom: 50%;" ></img>
<div class="centeredOption">
<a href= "/survival">Survival Mode</a>
</div>
</div>


<br />
<div class="menuoption" style="position: absolute; width:25%;height:25%; right:15%;bottom: 35%;">
<img id = "img3" src = "images/stickynote.png" style="width:90%;height:90%;" ></img>
<div class="centeredOption">
<a href= "/mapbuilder">Map Builder</a>
</div>
</div>

<br />
<div class="menuoption" style="position: absolute; width:25%;height:25%; right:30%;bottom: 15%;">
<img id = "img4" src = "images/stickynote.png" style="width:90%;height:90%;" ></img>
<div class="centeredOption">
<a href= "/multiplayer">Multiplayer</a>
</div>
</div>

<div class="menuoption" style="position: absolute; width:25%;height:25%; right:5%;bottom: 15%;">
<img id = "img5" src = "images/stickynote.png" style="width:90%;height:90%;" ></img>
<div class="centeredOption">
<a href= "/leaderboards">Leaderboards</a>
</div>
</div>


<style>
body {
   background-image: url("images/homebg.jpg");
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

#sjs0 {
    position: absolute;
    top: 25%;
    left: 5%;
}

</style>

</#assign>
<#include "main.ftl">
<script src="/js/home.js"></script>