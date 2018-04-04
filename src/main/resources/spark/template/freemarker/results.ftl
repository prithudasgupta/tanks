<#assign content>
<style>
    body {
        background: white;
    }
</style>
<pre id="titleHead">
                                                 *    ███████╗████████╗ █████╗ ██████╗ ███████╗     *
                                      *               ██╔════╝╚══██╔══╝██╔══██╗██╔══██╗██╔════╝               *           *
                              *                       ███████╗   ██║   ███████║██████╔╝███████╗           *
                                              *       ╚════██║   ██║   ██╔══██║██╔══██╗╚════██║
                                   *                  ███████║   ██║   ██║  ██║██║  ██║███████║ *                   *
                                                      ╚══════╝   ╚═╝   ╚═╝  ╚═╝╚═╝  ╚═╝╚══════╝           *
</pre>

<hr size="5" noshade>

<pre id="Information">
    <strong>Query</strong> the stars to find <strong>the nearest neighbors</strong> to a given point or a specific star in space.
    You can also using the drop-down menu find all stars in a given <strong>radius</strong> to a point or star in space.
    Enjoy your <strong>stars searching</strong>! <a href="/stars" id="refresh"> <strong>Fresh Search?</strong></a>
</pre>

<hr size="4" noshade>

<pre id="results">
    The Query: ${NorR} : ${RorK} from point with coordinates x:${Xval} y:${Yval} z:${Zval}
</pre>

<div class="row">
    <div class="column" id="firstcol">
        <h1>IDs</h1>
        <#if 0 < ID?size >
            <ul id="goodwords">
            <#list  ID as word>
            <li>${word}
            </#list>
            </ul>
        </#if>
    </div>
    <div class="column">
        <h1>Names</h1>
        <#if 0 < names?size >
            <ul id="goodwords">
            <#list  names as word>
            <li>${word}
            </#list>
            </ul>
        </#if>
    </div>
    <div class="column">
        <h1>Distance from Point</h1>
        <#if 0 < dist?size >
            <ul id="goodwords">
            <#list  dist as word>
            <li>${word}
            </#list>
            </ul>
        </#if>
    </div>
</div>



</#assign>
<#include "main.ftl">