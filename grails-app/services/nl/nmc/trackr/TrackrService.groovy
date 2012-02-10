package nl.nmc.trackr

import java.text.SimpleDateFormat
import org.codehaus.groovy.grails.commons.*

class TrackrService {
	
	static transactional = true

	def track(reference, request, params) {

		def config = ConfigurationHolder.config
		
		def trackrFile = null

		// init file to log to
		try {
			trackrFile = new File("${config.trackr.path}${config.trackr.prefix}trackr.${new SimpleDateFormat('yyyyMMdd').format(new Date())}.log".toLowerCase())
		} catch (e) {
			//unable to access the file to log to
			log.error("TrackR is not able to access the file. Make sure 'trackr.path' is set in the config. ${e}")
		}

		if (trackrFile != null){
			try {
				def callerProperties = [:]
		
				// start with a timestamp
				callerProperties['timestamp'] = System.currentTimeMillis()
		
				// allow users to add a reference
				callerProperties['reference'] = reference ?: '-'
				
				// start with a timestamp
				callerProperties['datetime'] = new Date()
		
				// GET/POST
				callerProperties['method'] = request.getMethod() ?: "-"
		
				// build the url as it was called by the user
				callerProperties['url'] = ""
				callerProperties['url'] += request.getContextPath()
				callerProperties['url'] += params.controller ? "/" + params.controller : ""
				callerProperties['url'] += params.action ? "/" + params.action : ""
				callerProperties['url'] += params.id ? "/" + params.id : ""
				callerProperties['url'] += request.getQueryString() ? "/?${request.getQueryString()}" : ""
		
				// see if there is a 'signed in' user
				callerProperties['user'] = request.getUserPrincipal() ?: "-"
		
				// try to resolve the users IP
				callerProperties['ip'] = request.getHeader("Client-IP")?: "-"
		
				// do a browser detection
				callerProperties['browser'] = request.getHeader('user-agent') ?: "-"
		
				// aggregate all cookies available
				callerProperties['cookies'] = request.getCookies().collect { "${it.name}::${it.value}" }.join(", ") ?: "-"
		
				trackrFile << "${callerProperties.collect { it.value }.join('\t')}\n"
			} catch (e) {
				//loggin failed
				log.error("TrackR is was not able to log any data. ${e}")
			}
		}
	}
}
