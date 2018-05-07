<#assign content>
    
<div id="main" >

<img id="profileBut" src = "images/friendsIcon.png" onmouseover="this.src='/images/friendsIcon_selected.png';"
     onmouseout="this.src='/images/friendsIcon.png';">

<img id="information" src="/images/information.png" >

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
<img id="multiBut" src='/images/multiplayer.png' onmouseover="this.src='/images/multiplayer_hover.png';"
     onmouseout="this.src='/images/multiplayer.png';" style="position: absolute; right: 10%; top: 61%;" />
<br />
<img id="leader" src='/images/leaderboards.png' onmouseover="this.src='/images/leaderboards_hover.png';"
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

<div id="leaderboard">
    <img id="exitLeader" src="/images/exit.png" onmouseover="this.src='/images/exit_hover.png';"
         onmouseout="this.src='/images/exit.png';" style="position: absolute; top: 6px; right: 9px;">
    <center>
        <h2> Leaderboard </h2>
    </center>
    <div class="tab">
        <button class="tablinks" onclick="openTab(event, 'survival')">Survival</button>
        <button class="tablinks" onclick="openTab(event, 'kills')">Kills</button>
        <button class="tablinks" onclick="openTab(event, 'time')">Time</button>
    </div>
    <div id="survival" class="tabcontent">
        <button class="tablinks" onclick="switchFilter('survivalFriends','survivalGlobal', 'Survival: Global', 'survivalTitle')">Global</button>
        <button class="tablinks" onclick="switchFilter('survivalGlobal','survivalFriends', 'Survival: Friends', 'survivalTitle')">Friends</button>
        <h3 id = "survivalTitle">Survival: Global</h3>
        <table class="leaderboardTab" id="survivalGlobal"></table>
        <table class="leaderboardTab" id="survivalFriends" style="display: none;"></table>
    </div>

    <div id="kills"  class="tabcontent">
        <button class="tablinks" onclick="switchFilter('killsFriends','killsGlobal', 'Kills: Global', 'killsTitle')">Global</button>
        <button class="tablinks" onclick="switchFilter('killsGlobal','killsFriends', 'Kills: Friends', 'killsTitle')">Friends</button>
        <h3 id = "killsTitle">Kills: Global</h3>
        <table class="leaderboardTab" id="killsGlobal"></table>
        <table class="leaderboardTab" id="killsFriends" style="display: none;"></table>
    </div>

    <div id="time" class="tabcontent">
        <button class="tablinks" onclick="switchFilter('timeFriends','timeGlobal', 'Time: Global', 'timeTitle')">Global</button>
        <button class="tablinks" onclick="switchFilter('timeGlobal','timeFriends', 'Time: Friends', 'timeTitle')">Friends</button>
        <h3 id = "timeTitle">Time: Global</h3>
        <table class="leaderboardTab" id="timeGlobal"></table>
        <table class="leaderboardTab" id="timeFriends" style="display: none;"></table>
    </div>
</div>

<div id="multiplayer">
<img id="exitMulti" src="/images/exit.png" onmouseover="this.src='/images/exit_hover.png';"
         onmouseout="this.src='/images/exit.png';" style="position: absolute; top: 6px; right: 9px;">
         <center>
         <h2 style="position: absolute; left: 45%; top: 0;"> Multiplayer: </h2>
         </center>
         <h2 id="user-heading" style="position: absolute; left: 12.5%; top: 5%;"> Friends: </h2>
     <div id="friendsMult">
         <center>
             <table id="friendsTable2"></table>
         </center>
    </div>

	    <h2 id="user-heading" style="position: absolute; left: 45%; top: 5%;"> Games: </h2>

    <div id="games">
        <center>
            <table id="gamesTable"></table>
        </center>
    </div>
	    
	    <h2 id="user-heading" style="position: absolute; left: 77.5%; top: 5%;"> Inbox: </h2>
    <div id="inbox">
    <center>
        <table id="inboxTable"></table>
    </center>
    </div>
    <button id="matchmake" onclick="matchmaker()" style="position: absolute; top: 9px; left: 12px;"> Matchmaker </button>
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

	<h2 id="user-heading1" style="position: absolute; left: 45%; top: 0;"> Profile: </h2>

    <h2 id="user-heading" style="position: absolute; left: 12.5%; top: 5%;"> Friends: </h2>
    <div id="friends">
        <center>
        		<p>
        		<input type="text" placeholder="Add friend" id="friendUse">
  			<input type="submit" value="Submit" id="sub" onclick="addFriend()"></p>
            <table id="friendsTable"></table>
        </center>
    </div>
    <h2 id="user-heading" style="position: absolute; left: 45%; top: 5%;"> Stats: </h2>
    <div id="stats">
        <h4 id="killstat"> Total Kills       : 0 </h4>
        <h4 id="timestat"> Total Time Played : 0 </h4>
        <h4 id="campstat"> Campaign          : 0 </h4>
        <h4 id="survivalstat"> Survival          : 0 </h4>
    </div>
    <h2 id="user-heading" style="position: absolute; left: 77.5%; top: 5%;"> Maps: </h2>
    <div id="maps">
        <center>
            <table id="mapsTable"></table>
        </center>
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

#friendsTable {
    border-collapse: collapse;
    width: 100%;
}

#mapsTable {
    /*position: absolute;*/
    border-collapse: collapse;
    width: 100%;
    /*margin-left: 15%;*/
    /*overflow: none;*/
    overflow-y: scroll;
    /*display: block;*/
}

#gamesTable {
    /*position: absolute;*/
    border-collapse: collapse;
    width: 100%;
    /*margin-left: 15%;*/
    /*overflow: none;*/
    overflow-y: scroll;
    /*display: block;*/
}

.leaderboardTab {
    width: 100%;
    border-collapse: collapse;
}

#killsGlobal {
    width: 100%;
    border-collapse: collapse;
}

#inboxTable {
    border-collapse: collapse;
    width: 100%;
    overflow-y: scroll;
}

#friendsTable2 {
    border-collapse: collapse;
    width: 100%;
}

th, td {
    text-align: center;
    padding: 8px;
}


tr:nth-child(even) {background-color: #f2f2f2;}




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

#leaderboard {
    border-radius: 25px;
    position: absolute;
    top: 12.5%;
    left: 30%;
    width: 25%;
    height: 75%;
    background-image: url("/sprites/menu.png");
    background-repeat: repeat;
    display: none;
    z-index: 99;
    overflow-y: scroll;
}

#multiplayer {
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

#information {
    position: absolute;
    top: 5px;
    right: 6%;
    padding: none;
}

.marked{
	background-color:#FF4500;color:#000000;font-weight:500;
}

#profile div {
    position: absolute;
    width: 30%;
    height: 80%;
    top: 15%;
    border-radius: 10px;
}

#multiplayer div {
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


/*Multiplayer page maps subcontent*/
#games {
    flex: 1;
    left: 35%;
    background-image: url("/sprites/menu.png");
    background-repeat: repeat;
    border: solid;
    border-color: silver;
    overflow-y: scroll;
    display:block;
}

#friendsMult {
    left: 2.5%;
    flex: 1;
    background-image: url("/sprites/menu.png");
    background-repeat: repeat;
    border: solid;
    border-color: silver;
}

#inbox {
    flex: 1;
    left: 67.5%;
    background-image: url("/sprites/menu.png");
    background-repeat: repeat;
    border: solid;
    border-color: silver;
    overflow-y: scroll;
}

#games h4 {
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
    overflow-y: scroll;
    display:block;
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




/* Style the tab */
.tab {
    overflow: hidden;
    border: 1px solid #ccc;
    background-color: #f1f1f1;
}

/* Style the buttons that are used to open the tab content */
.tab button {
    background-color: inherit;
    float: left;
    border: none;
    outline: none;
    cursor: pointer;
    padding: 14px 16px;
    transition: 0.3s;
}

/* Change background color of buttons on hover */
.tab button:hover {
    background-color: #ddd;
}

/* Create an active/current tablink class */
.tab button.active {
    background-color: #ccc;
}

/* Style the tab content */
.tabcontent {
    display: none;
    padding: 6px 12px;
    border: 1px solid #ccc;
    border-top: none;
}

</style>

</#assign>
<#include "main.ftl">
<script src="/js/home.js"></script>