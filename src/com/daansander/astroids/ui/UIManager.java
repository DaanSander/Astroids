package com.daansander.astroids.ui;

import java.util.ArrayList;
import java.util.List;

import com.daansander.astroids.graphics.Screen;

public class UIManager {

	private List<UIPanel> uiPanels = new ArrayList<UIPanel>();

	public UIManager()	{
		
	}
	
	public void addPanel(UIPanel panel) {
		uiPanels.add(panel);
	}
	
	public void tick() {
		for(UIPanel panel : uiPanels) {
			panel.tick();
		}
	}

	public void renderUI(Screen screen) {
		for (UIPanel panel : uiPanels) {
			panel.render(screen);
		}
	}

}