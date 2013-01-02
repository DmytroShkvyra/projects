package com.mycompany.guicetest;

import org.duelengine.duel.FormatPrefs;
import org.duelengine.duel.IncClientIDStrategy;
import org.duelengine.duel.LinkInterceptor;
import org.duelengine.duel.mvc.DuelMvcContext;

import com.google.inject.Inject;
import com.google.inject.Injector;
import com.google.inject.Stage;
import com.google.inject.servlet.RequestScoped;
import com.sun.jersey.api.core.ExtendedUriInfo;

@RequestScoped
public class AppContext extends DuelMvcContext {

	@Inject
	public AppContext(
		Injector injector,
		Stage stage,
		ExtendedUriInfo uriInfo,
		LinkInterceptor interceptor) {

		super(injector, stage, uriInfo);

		this.setLinkInterceptor(interceptor)
			.setClientID(new IncClientIDStrategy("_"));

		if (stage == Stage.DEVELOPMENT) {
			// setup pretty-print formatting
			// add ambient client-side data
			this.setFormat(new FormatPrefs().setIndent("\t").setNewline("\n"))
				.putExtra("App.isDebug", true);
		}
	}

	// TODO: add app-specific context data properties
}
