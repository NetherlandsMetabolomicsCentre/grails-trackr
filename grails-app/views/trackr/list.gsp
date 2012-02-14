<g:if test="${flash.message}">
	<p><font color="red">${flash.message}</font></p>
</g:if>

<h1>${trackrDirectory}</h1>
<g:each in="${trackrDirectoryFiles}" var="f">
	- <g:link action="view" params="['trackrFile':f.name]">${f.name}</g:link><br />
</g:each>