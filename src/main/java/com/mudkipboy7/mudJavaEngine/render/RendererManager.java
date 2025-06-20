package com.mudkipboy7.mudJavaEngine.render;

import java.lang.System.Logger.Level;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import org.lwjgl.opengl.GL46;

import com.mudkipboy7.mudJavaEngine.Main;

public class RendererManager {
	public Main main;
	// The solid color background

	public ArrayList<AbstractRendererInstance<?>> stuffBeingRendered = new ArrayList<AbstractRendererInstance<?>>();

	public RendererManager() {
	}

	public RendererManager(Main main) {
		this.main = main;

	}

	@SuppressWarnings("rawtypes")
	public void renderStuff() {

		stuffBeingRendered.clear();
		// Renderers.charRenderer.render('E', 0.7F, 0.9F);
	}

}
