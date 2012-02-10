package nl.nmc.trackr

class TrackrTagLib {
	
	def TrackrService
	
	static namespace = 'trackr'
	
	def track = { attrs ->
		TrackrService.track(attrs.reference, request, params)
	}

}
