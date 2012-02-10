<g:if test="${flash.message}">
	<p><font color="red">${flash.message}</font></p>
</g:if>

<g:if test="${trackrFile}">
	<h1>${trackrFile.name}</h1>
	<g:link action="view" params="['trackrFile':'']">close</g:link><br />
	<pre>${trackrFile.text}</pre>
</g:if>
<g:else>
	<h1>${trackrDirectory}</h1>
	<g:each in="${trackrDirectoryFiles}" var="f">
		- <g:link action="view" params="['trackrFile':f.name]">${f.name}</g:link><br />
	</g:each>
</g:else>