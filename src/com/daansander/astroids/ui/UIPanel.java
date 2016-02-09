package com.daansander.astroids.ui;

import java.util.ArrayList;
import java.util.List;

import com.daansander.astroids.graphics.Screen;

public class UIPanel {

	public int backgroundColor;
	private int x, y;
	private int xSize, ySize;
	private List<UIComponent> uiComponents = new ArrayList<>();
	
	public UIPanel(int x, int y, int xSize, int ySize, int backgroundColor) {
		this.x = x;
		this.y = y;
		this.xSize = xSize;
		this.ySize = ySize;
		this.backgroundColor = backgroundColor;
	}
	
	public UIPanel(int x, int y, int xSize, int ySize) {
		this.x = x;
		this.y = y;
		this.xSize = xSize;
		this.ySize = ySize;
		this.backgroundColor = 0xff00ff;
	}

	public void addComponent(UIComponent component) {
		component.x = x;
		component.y = y;
		uiComponents.add(component);
	}
	
	public void tick() {
		for(UIComponent component : uiComponents) {
			component.tick();
		}
	}
	
	public void render(Screen screen) {
		screen.fillRectangle(x, y, xSize, ySize, backgroundColor);
		for(UIComponent component : uiComponents) {
			component.render(screen);
		}
	}
	
}