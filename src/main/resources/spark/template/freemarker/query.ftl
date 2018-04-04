<#assign content>
<style>
    body {
        background: white;
    }
</style>

<center>
<pre id="titleHead">
  *                   *    ███████╗████████╗ █████╗ ██████╗ ███████╗     *                          *
  *        *               ██╔════╝╚══██╔══╝██╔══██╗██╔══██╗██╔════╝               *           *    *
  *                        ███████╗   ██║   ███████║██████╔╝███████╗           *                    *
  *                *       ╚════██║   ██║   ██╔══██║██╔══██╗╚════██║                                *
  *     *                  ███████║   ██║   ██║  ██║██║  ██║███████║ *                   *          *
  *                        ╚══════╝   ╚═╝   ╚═╝  ╚═╝╚═╝  ╚═╝╚══════╝           *                    *
</pre>

<hr size="5" noshade>

<pre id="Information">
    <strong>Query</strong> the stars to find <strong>the nearest neighbors</strong> to a given point or a specific star in space.
    You can also using the drop-down menu find all stars in a given <strong>radius</strong> to a point or star in space.
    Enjoy your <strong>stars searching</strong>! <a href="/home" > <strong>Go Home</strong></a>.
</pre>

<hr size="4" noshade>

<form method="POST" action="/results" id="searches">
    <select name="type" id="typeSearch">
        <option name="type" value="neighbors">Number of Neighbors</option>
        <option name="type" value="radius">Length of radius</option>
    </select>
    <input name="RorK" type="text" class="small" size="4" placeholder="Enter Value">
  <br>
    <p id="Information" > <STRONG>Select search either by point or by name.</STRONG></p>

    <input type="radio" name="cornam" id="radio" value="coordinate" checked="checked"> By Coordinate</input>
    <textarea name="X" class="leftcord" placeholder="Enter X Value"></textarea>
    <textarea name="Y" class="cord" placeholder="Enter Y Value"></textarea>
    <textarea name="Z" class="endcord" placeholder="Enter Z Value"></textarea>
    <input type="radio" name="cornam" id="radio2" value="name"> By Name </input>
    <textarea name="name" class="Name" placeholder="Enter Name of Star"></textarea>
  <br>
  <br>
  <input type="submit" class="button">
</form>
</center>
</#assign>
<#include "main.ftl">
