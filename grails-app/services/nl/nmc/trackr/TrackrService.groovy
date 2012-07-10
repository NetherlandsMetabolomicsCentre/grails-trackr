package nl.nmc.trackr

import java.text.SimpleDateFormat
import org.codehaus.groovy.grails.commons.*

class TrackrService {
	
	static transactional = false
	
	def config = ConfigurationHolder.config

	def track(reference, request, params) {
		
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
				reference = reference.toString().tokenize().join(' ') // force to String and remove any \t\n\r\f
				
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
				callerProperties['ip'] = InetAddress.localHost.hostAddress ?: "-"
		
				// do a browser detection
				callerProperties['browser'] = request.getHeader('user-agent') ?: "-"
				
				trackrFile << "${callerProperties.collect { it.value }.join('\t')}\n"
			} catch (e) {
				//loggin failed
				log.error("TrackR is was not able to log any data. ${e}")
			}
		}
	}
	
	def analyze(trackrFile = null) {
		
		//init
		def report 			= [:]
		def browsersUsed 	= [:]
		def urlsCalled 		= [:]
		def referenceCount	= [:]
		
		//see if we can analyze a file
		if (trackrFile != null){
			
			//parse
			new File("${config.trackr.path}${trackrFile}").eachLine{ trackrEntry ->
				
				//split parts of the trackrEntry
				def trackrEntryParts = trackrEntry.split("\t")
				
				def timestamp 	= trackrEntryParts[0] //timestamp
				def reference 	= trackrEntryParts[1] //reference
				def date 		= trackrEntryParts[2] //date
				def method 		= trackrEntryParts[3] //method
				def url 		= trackrEntryParts[4] //url
				def user 		= trackrEntryParts[5] //user
				def ip 			= trackrEntryParts[6] //ip
				def browser 	= trackrEntryParts[7] //browser
				
				//browsers used
				if (browsersUsed["${browser}"]) { browsersUsed["${browser}"]++ } else { browsersUsed["${browser}"] = 1 }
				
				//urls called
				if (urlsCalled["${url}"]) { urlsCalled["${url}"]++ } else { urlsCalled["${url}"] = 1 }
				
				//reference cound
				if (referenceCount["${reference}"]) { referenceCount["${reference}"]++ } else { referenceCount["${reference}"] = 1 }
			}
			
			//merge information
			report['browsersUsed'] 	= browsersUsed
			report['urlsCalled'] 	= urlsCalled
			report['referenceCount'] = referenceCount
		}
		
		return report
	}
}
