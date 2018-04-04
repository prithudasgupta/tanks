<#assign content>
<style>
    body {
        background: gainsboro;
    }
</style>

<center>
<pre style="background: whitesmoke">
|       ___         __                                       __     |
|      /   | __  __/ /_____  _________  _____________  _____/ /_    |
|     / /| |/ / / / __/ __ \/ ___/ __ \/ ___/ ___/ _ \/ ___/ __/    |
|    / ___ / /_/ / /_/ /_/ / /__/ /_/ / /  / /  /  __/ /__/ /_      |
|   /_/  |_\__,_/\__/\____/\___/\____/_/  /_/   \___/\___/\__/      |
</pre>
<label >
    This is an autocorrected text editor. Turn on the different suggestion generators and/or smart ranking to
    receive suggestions based off the words typed into the text box below. <strong>Remember</strong>, a corpus of
    words to base suggestions off of must be added via the command line, using the "corpus /file/path" command.
    The 'Default Setting' button will turn on default auto-correcting suggestions. The 'Add Dictionary' button will add a
    dictionary but it only has one entry of each word which is not optimal for producing suggestions.
    <a href="/home" > <strong>Go Home</strong></a>
</label>
<br>
<label>Whitespace: <select id="ws" name="wsoptions">
    <option value="off" name="off">off</option>
    <option value="on" name="on">on</option>
</select>
</label>

<label>Prefix: <select id="pre" name="preoptions">
    <option value="off" name="off">off </option>
    <option value="on" name="on">on</option>
</select>
</label>
<label>Edit Distance: <select class="0-15" id="led">
</select> </label>

<label>Smart: <select id="smart" name="smartoptions">
    <option value="off" name="off">off</option>
    <option value="on" name="on">on</option>
</select>
</label>

<br>
<textarea id="tbox" style="height:250px;width:80%;font-size:14pt;" >
</textarea>
<br>

<ul id="suggestions" style="border: 2px solid black; list-style-type: none;"> Suggestions
</ul>

<button id="def" onclick="setDefaults()">
    Default Setting
</button>
<button id="adddict" onclick="addDict()">
    Add Dictionary
</button>
<label>
    Click on the suggestions to replace the text in the text box!
</label>
</center>





</#assign>
<#include "main.ftl">