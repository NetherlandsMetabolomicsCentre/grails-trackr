package nl.nmc.trackr

import org.codehaus.groovy.grails.commons.*

class TrackrController {

	/**
	 * Demo page to test the TrackR
	 * @return
	 */
    def index = { 
		//view the index.gsp on how to use it!
	}
	
	/**
	 * Page to view the log files
	 * @return
	 */
	def view = {
		
		//inject config
		def config = ConfigurationHolder.config
		
		//init variables
		def trackrFile = null
		def trackrDirectory = new File(config.trackr.path)
		def trackrDirectoryFiles = []

		//read file(s) in the directory that match with the trackr prefix
		if (trackrDirectory.isDirectory()){
			trackrDirectory.eachFile { f ->
				if (f.isFile() && f.name.contains('trackr')) trackrDirectoryFiles.add(f)
			}
		} else {
			flash.message = 'Unable to read the logs directory (' + trackrDirectory + ') of trackR.'
		}
		
		//check if a file has been selected
		if (params.trackrFile){
			trackrFile = new File("${trackrDirectory}/${params.trackrFile}")
		}
		
		//return variables to the template
		[	trackrFile:trackrFile,
			trackrDirectory:trackrDirectory, 
			trackrDirectoryFiles:trackrDirectoryFiles
		]
		
	}
}
