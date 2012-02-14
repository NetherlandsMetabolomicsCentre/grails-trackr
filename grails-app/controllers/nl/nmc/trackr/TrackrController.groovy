package nl.nmc.trackr

import org.codehaus.groovy.grails.commons.*

class TrackrController {
	
	//inject config
	def config = ConfigurationHolder.config
	
	//inject service(s)
	def TrackrService
	
	//set trackr directory for class
	def trackrDirectory = new File(config.trackr.path)

	/**
	 * Demo page to test the TrackR
	 * @return
	 */
    def index = { 
		//view the index.gsp on how to use it!
	}
	
	/**
	 * Page to list the log files
	 * @return
	 */
	def list = {
		
		//init variables
		def trackrDirectoryFiles = []

		//read file(s) in the directory that match with the trackr prefix
		if (trackrDirectory.isDirectory()){
			trackrDirectory.eachFile { f ->
				if (f.isFile() && f.name.contains('trackr')) trackrDirectoryFiles.add(f)
			}
		} else {
			flash.message = 'Unable to read the logs directory (' + trackrDirectory + ') of trackR.'
		}
		
		//return variables to the template
		[	trackrDirectory:trackrDirectory, 
			trackrDirectoryFiles:trackrDirectoryFiles
		]
		
	}
	
	/**
	* Page to view a log file
	* @return
	*/
	def view = {
		
		//init variables
		def trackrFile = null
		
		//check if a file has been selected
		if (params.trackrFile){
			trackrFile = new File("${trackrDirectory}/${params.trackrFile}")
		}
		
		[ trackrFile:trackrFile ]
	}
	
	/**
	 * Analyze a log file
	 * @return
	 */
	def analyze = {
		
		//init variables
		def trackrFile = null
		def trackrFileSummary = null
		
		//check if a file has been selected
		if (params.trackrFile){
			trackrFile = new File("${trackrDirectory}/${params.trackrFile}")
			
			//analyze
			trackrFileSummary = TrackrService.analyze(trackrFile.name)
		}
		
		[ trackrFile:trackrFile, trackrFileSummary:trackrFileSummary ]
	}
}
