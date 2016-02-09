package com.daansander.astroids.ui;

import com.daansander.astroids.graphics.Screen;

public abstract class UIComponent {
	
	public int x, y;
	public int xOffset, yOffset;
	
	protected UIComponent(int xOffset,int yOffset) {
		this.xOffset = xOffset;
		this.yOffset = yOffset;
	}
	
	public abstract void tick();

	public abstract void render(Screen screen);
		
}