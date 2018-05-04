<#assign content>
    
<div id="main" >

<img id="profileBut" src = "images/friendsIcon.png" onmouseover="this.src='/images/friendsIcon_selected.png';"
     onmouseout="this.src='/images/friendsIcon.png';">

<center>    
<img id="logo" src="images/logo1.png" >
</center>

<br />
<br />


<img id="campBut" src='/images/campaign.png' onmouseover="this.src='/images/campaign_hover.png';"
     onmouseout="this.src='/images/campaign.png';" style="position: absolute; right: 10%; top: 10%;" />
<br />
<img id="survivalBut" src='/images/survival.png' onmouseover="this.src='/images/survival_hover.png';"
     onmouseout="this.src='/images/survival.png';" style="position: absolute; right: 10%; top: 27%;"/>
<br />
<img id="mapBuild" src='/images/mapBuilder.png' onmouseover="this.src='/images/mapBuilder_hover.png';"
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
         onmouseout="this.src='/images/exit.png';" style="position: absolute; top: 6px; right: 9px;">
    <center>
        <h2> Select level </h2>
    </center>
    <div id="levels" style="top: 15%; left: 25%; position: absolute; height: 75%; width: 50%;">
    </div>
</div>

<div id="signin">
    <img id="exitProf" src="/images/exit.png" onmouseover="this.src='/images/exit_hover.png';"
         onmouseout="this.src='/images/exit.png';" style="position: absolute; top: 6px; right: 9px;">
    <center>
        <h2> Sign In </h2>
        <p> Username: <input type="text" id="username"> </p>
	    <p> Password: <input type="password" id="password"></p>
	    <br>
	    <p>
	    <button id="signIn"> Sign In </button> 
	    <button id="createAccount"> Create Account </button> </p>
    </center>
</div>

<div id="profile">
	<img id="exitProfile" src="/images/exit.png" onmouseover="this.src='/images/exit_hover.png';"
         onmouseout="this.src='/images/exit.png';" style="position: absolute; top: 6px; right: 9px;">

	<h2 id="user-heading" style="position: absolute; left: 45%; top: 0;"> Profile: </h2>

    <h2 id="user-heading" style="position: absolute; left: 12.5%; top: 5%;"> Friends: </h2>
    <div id="friends">
    </div>
    <h2 id="user-heading" style="position: absolute; left: 45%; top: 5%;"> Stats: </h2>
    <div id="stats">
        <h4 id="statsOp"> Total Kills       : 0 </h4>
        <h4 id="statsOp"> Total Time Played : 0 </h4>
        <h4 id="statsOp"> Campaign          : 0 </h4>
        <h4 id="statsOp"> Survival          : 0 </h4>
    </div>
    <h2 id="user-heading" style="position: absolute; left: 77.5%; top: 5%;"> Maps: </h2>
    <div id="maps">

    </div>
    <button id="logout" style="position: absolute; top: 6px; left: 9px;"> Logout </button>

</div>

<div id="login" style="display: none">
    <img id="exitLogin" src="/images/exit.png" onmouseover="this.src='/images/exit_hover.png';"
         onmouseout="this.src='/images/exit.png';" style="position: absolute; top: 6px; right: 9px;">
    <center>
        <h2> You must login first </h2>
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
    border-radius: 25px;
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

#signin {
    border-radius: 25px;
    position: absolute;
    top: 12.5%;
    left: 30%;
    width: 40%;
    height: 37%;
    background-image: url("/sprites/menu.png");
    background-repeat: repeat;
    display: none;
    z-index: 99;
}

#profile {
    border-radius: 25px;
	position: absolute;
    top: 12.5%;
    left: 10%;
    width: 75%;
    height: 75%;
    background-color: #EDE024;
    border: solid;
    border-color: silver;
    display: none;
    z-index: 99;
}

#profile div {
    position: absolute;
    width: 30%;
    height: 80%;
    top: 15%;
    border-radius: 10px;
}

#friends{
    flex: 1;
    left: 2.5%;
    background-image: url("/sprites/menu.png");
    background-repeat: repeat;
    border: solid;
    border-color: silver;
}


#stats {
    flex: 1;
    left: 35%;
    background-image: url("/sprites/menu.png");
    background-repeat: repeat;
    border: solid;
    border-color: silver;
}

#stats h4 {
    position: relative;
    left: 5%;
}

#maps {
    flex: 1;
    left: 67.5%;
    background-image: url("/sprites/menu.png");
    background-repeat: repeat;
    border: solid;
    border-color: silver;
}

#login {
    position: absolute;
    top: 36%;
    left: 40%;
    width: 20%;
    height: 8%;
    background-color: indianred;
    display: none;
    z-index: 99;
}

#logo {
    border-radius: 25px;
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