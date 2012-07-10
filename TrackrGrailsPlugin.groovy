class TrackrGrailsPlugin {
    // the plugin version
    def version = "0.7.3"
    // the version or versions of Grails the plugin is designed for
    def grailsVersion = "1.3.7 > *"
    // the other plugins this plugin depends on
    def dependsOn = [:]
    // resources that are excluded from plugin packaging
    def pluginExcludes = [
            "grails-app/views/error.gsp",
			"grails-app/conf/DataSource.groovy",
			"grails-app/views/index.gsp"
    ]

// TODO Fill in these fields
    def author = "Michael van Vliet"
    def authorEmail = "m.s.vanvliet@lacdr.leidenuniv.nl"
    def title = "Usage tracker for Grails projects"
    def description = '''Usage tracker for Grails projects'''

    // URL to the plugin's documentation
    def documentation = "https://github.com/NetherlandsMetabolomicsCentre/grails-trackr"
	def license = "GPL3"
	def issueManagement = [system: "github", url: "https://github.com/NetherlandsMetabolomicsCentre/grails-trackr/issues"]
	def scm = [url: "https://github.com/NetherlandsMetabolomicsCentre/grails-trackr"]
	def organization = [ name: "Netherlands Metabolomics Centre", url: "http://www.metabolomicscentre.nl/" ]
	def developers = [
		[ name: "Michael van Vliet", email: "m.s.vanvliet@lacdr.leidenuniv.nl" ]
	]

    def doWithWebDescriptor = { xml ->
        // TODO Implement additions to web.xml (optional), this event occurs before 
    }

    def doWithSpring = {
        // TODO Implement runtime spring config (optional)
    }

    def doWithDynamicMethods = { ctx ->
        // TODO Implement registering dynamic methods to classes (optional)
    }

    def doWithApplicationContext = { applicationContext ->
        // TODO Implement post initialization spring config (optional)
    }

    def onChange = { event ->
        // TODO Implement code that is executed when any artefact that this plugin is
        // watching is modified and reloaded. The event contains: event.source,
        // event.application, event.manager, event.ctx, and event.plugin.
    }

    def onConfigChange = { event ->
        // TODO Implement code that is executed when the project configuration changes.
        // The event is the same as for 'onChange'.
    }
}
