<g:if test="${flash.message}">
	<p><font color="red">${flash.message}</font></p>
</g:if>

<h1>${trackrFile.name}</h1>
<g:link action="analyze" params="['trackrFile': trackrFile.name]">analyze</g:link> :: <g:link action="list">close</g:link><br />
<pre>${trackrFile.text}</pre>