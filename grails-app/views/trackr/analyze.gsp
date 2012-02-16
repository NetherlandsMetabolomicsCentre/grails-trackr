<g:if test="${flash.message}">
	<p><font color="red">${flash.message}</font></p>
</g:if>

<h1>${trackrFile.name}</h1>
<g:link action="view" params="['trackrFile': trackrFile.name]">view</g:link> :: <g:link action="list">close</g:link><br />

<!-- Browsers used -->
<h1>Browsers</h1>
<g:each in="${trackrFileSummary['browsersUsed'].sort { a,b -> b.value<=>a.value}}" var="browserScore">
	${browserScore.value} ${browserScore.key}<br />
</g:each>

<!-- Urls called -->
<h1>URLs</h1>
<g:each in="${trackrFileSummary['urlsCalled'].sort { a,b -> b.value<=>a.value}}" var="urlScore">
	${urlScore.value} ${urlScore.key}<br />
</g:each>

<!-- References used -->
<h1>References</h1>
<g:each in="${trackrFileSummary['referenceCount'].sort { a,b -> b.value<=>a.value}}" var="referenceScore">
	${referenceScore.value} ${referenceScore.key}<br />
</g:each>

