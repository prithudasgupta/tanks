<#assign content>


<div class="actorSearch">
    <a class="home" href="/home""> Home</a>
    <button class="instructions" id="instructions" onclick="instructions()"> Instructions </button>
    <input class="act1" id="act1" onfocus="dropdown1()"
           type="text" placeholder="From Actor" >

    <button class="connect" id="connect"> connect </button>

    <input class="act2" id="act2" onfocus="dropdown2()"
           type="text" placeholder="To Actor" >
</div>
<div id="myDropdown1" class="dropdown-content1">
    <ul>
    </ul>
</div>
<div id="myDropdown2" class="dropdown-content2">
    <ul>
    </ul>
</div>

<center>

    <br>



<div class="column" id="actorList1">
    <header id="StarHeader"> FROM </header>
    <ul id="actFrom" style=" list-style-type: none;"> </ul>
</div>
<div class="column" id="actorList2">
    <header id="StarHeader"> TO </header>
    <ul id="actTo" style=" list-style-type: none;"> </ul>
</div>
<div class="column" id="movieList">
    <header id="StarHeader"> MOVIE </header>
    <ul id="movies" style=" list-style-type: none;"> </ul>
</div>
<br>
<div class="loading" id="loading">
    <img src="/images/loading.gif">
</div>

</center>
<br>
<br>
<center>
    <p id="warning" style="margin-top: 20%"></p>
</center>

<p style="position: fixed; bottom: 0;"><a href="https://www.fg-a.com">Free Gifs and Animations</a></p>
</#assign>
<#include "main.ftl">