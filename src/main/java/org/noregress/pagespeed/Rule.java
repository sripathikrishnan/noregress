package org.noregress.pagespeed;

enum Rule {

	AvoidBadRequests,
	AvoidCssImport,
	AvoidExcessSerialization,
	AvoidLongRunningScripts,
	DeferParsingJavaScript,
	EliminateUnnecessaryReflows,
	EnableGzipCompression,
	InlineSmallCss,
	InlineSmallJavaScript,
	LeverageBrowserCaching,
	MakeLandingPageRedirectsCacheable,
	MinifyCss,
	MinifyHTML,
	MinifyJavaScript,
	MinimizeRedirects,
	MinimizeRequestSize,
	OptimizeImages,
	OptimizeTheOrderOfStylesAndScripts,
	PreferAsyncResources,
	PutCssInTheDocumentHead,
	RemoveQueryStringsFromStaticResources,
	ServeResourcesFromAConsistentUrl,
	ServeScaledImages,
	SpecifyACacheValidator,
	SpecifyAVaryAcceptEncodingHeader,
	SpecifyCharsetEarly,
	SpecifyImageDimensions,
	SpriteImages
}
