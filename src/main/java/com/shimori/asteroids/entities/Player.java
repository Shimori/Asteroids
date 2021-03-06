package com.shimori.asteroids.entities;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.MathUtils;
import com.shimori.asteroids.main.Game;

public class Player extends SpaceObject{

	private boolean left;
	private boolean right;
	private boolean up;
	
	private float maxSpeed;
	private float acceleration;
	private float deceleration;
	
	public Player(){
		x = Game.WIDTH / 2;
		y = Game.HEIGHT / 2;
		
		maxSpeed = 300;
		acceleration = 200;
		deceleration = 10;
		
		shapex = new float[4];
		shapey = new float[4];
		
		radians = 3.1415f / 2;
		rotationSpeed = 3;
		
	}
	
	private void setShape(){
		this.shapex[0] = x + MathUtils.cos(radians) * 8;
		this.shapey[0] = y + MathUtils.sin(radians) * 8;
		
		shapex[1] = x + MathUtils.cos(radians - 4 * 3.1415f / 5) * 8;
		this.shapey[1] = y + MathUtils.sin(radians - 4 * 3.1415f / 5) * 8;
		
		this.shapex[2] = x + MathUtils.cos(radians + 3.1415f) * 5;
		this.shapey[2] = y + MathUtils.sin(radians + 3.1415f) * 5;
		
		this.shapex[3] = x + MathUtils.cos(radians + 4 * 3.1415f / 5) * 8;
		this.shapey[3] = y + MathUtils.sin(radians + 4 * 3.1415f / 5) * 8;
	}
	
	public void setLeft(boolean b){
		this.left = b;
	}
	
	public void setRight(boolean b){
		this.right = b;
	}
	
	public void setUp(boolean b){
		this.up = b;
	}
	
	public void update(float dt){
		
		// turning
		if (left) {
			radians += rotationSpeed * dt;
		}else if (right) {
			radians -= rotationSpeed * dt;
		}
		
		// acceleration
		if(up){
			dx += MathUtils.cos(radians) * acceleration * dt;
			dy += MathUtils.sin(radians) * acceleration * dt;
		}
		
		// deceleration
		float vec = (float)Math.sqrt(dx * dx + dy * dy);
		if (vec > 0) {
			dx -= (dx / vec) * deceleration * dt;
			dy -= (dy / vec) * deceleration * dt;
		}
		if (vec > maxSpeed) {
			dx = (dx / vec) * maxSpeed;
			dy = (dy / vec) * maxSpeed;
		}
		
		//set position
		x += dx * dt;
		y += dy * dt;
	
		// set shape
		setShape();
		
		// screen wrap
		wrap();
	}
	
	public void draw(ShapeRenderer sr){
		sr.setColor(1, 1, 1, 1);
		
		sr.begin(ShapeType.Line);
		
		for(int i = 0, j = shapey.length - 1; i < shapex.length; j = i++){
			sr.line(shapex[i], shapey[i], shapex[j], shapey[j]);
		}
		
		sr.end();
	}
	
}
