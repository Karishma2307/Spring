package com.example.demo;

import org.springframework.stereotype.Component;

@Component
public class Car {
	private Engine engine;
		public Car(Engine engine) {
			this.engine=engine;
			
		}
		public void drive(){
			System.out.println(engine.start());
			System.out.println("Car is running");
			
	}

}
