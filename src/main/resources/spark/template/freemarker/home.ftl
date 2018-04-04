<#assign content>
<style>
    body {
        background: white;
    }
</style>
<center>
    <p id="hometitle" style="background-color: dimgrey">
        Projects
    </p>
    <br>
    <p id="home" style="background-color: gainsboro; width: 100%;">
        <a href="/stars" id="homelinks" style="color: blue"> * * * <strong>Stars</strong> * * *</a>
        <br>
        This is stars, where you are able to query a database of stars and find the nearest k stars to a point. It also allows the user to
        find all the stars within a radius of a point.
    </p>
    <br>
    <p id="home" style="background-color: azure; width: 100%;">
        <a href="/autocorrect" id="homelinks" style="color: blue"> <strong><s>Autorrect</s> Autocorrect</strong></a>
        <br>
        This is autocorrect. It presents the user with a text box with auto correction ability. The user can type into the
        box and recieve suggestions depending on what corpus is loaded into the auto correct suggestion generator.
    </p>
    <br>
    <p id="home" style="background-color: azure; width: 100%;">
        <a href="/connect" id="homelinks" style="color: blue"> <strong>Bacon</strong></a>
        <br>
        This is bacon, a program which allows a user to generate the shortest "bacon path" between two actors. The database
        must be loaded into the command line and then the interface will generate suggestions for actors as your type in
        their names. Have fun!
    </p>



</center>

</#assign>
<#include "main.ftl">