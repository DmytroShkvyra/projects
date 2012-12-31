package com.mycompany.guicetest.controllers;

import org.duelengine.duel.mvc.Apply;
import org.duelengine.duel.mvc.DuelController;

import com.mycompany.guicetest.aspects.CustomResponseHeaders;
import com.mycompany.guicetest.aspects.LatencyTimer;

/**
 * Base for all controllers in app
 */
@Apply({ CustomResponseHeaders.class, LatencyTimer.class })
public abstract class BaseController extends DuelController {

}
