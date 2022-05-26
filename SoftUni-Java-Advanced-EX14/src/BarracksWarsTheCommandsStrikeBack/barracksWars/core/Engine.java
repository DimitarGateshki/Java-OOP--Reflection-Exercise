package BarracksWarsTheCommandsStrikeBack.barracksWars.core;

import barracksWars.interfaces.*;
import barracksWars.interfaces.Runnable;
import jdk.jshell.spi.ExecutionControl;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class Engine implements Runnable {

	private Repository repository;
	private UnitFactory unitFactory;

	public Engine(Repository repository, UnitFactory unitFactory) {
		this.repository = repository;
		this.unitFactory = unitFactory;
	}

	@Override
	public void run() {
		BufferedReader reader = new BufferedReader(
				new InputStreamReader(System.in));
		while (true) {
			try {
				String input = reader.readLine();
				String[] data = input.split("\\s+");
				String commandName = data[0];
				String result = interpretCommand(data, commandName);
				if (result.equals("fight")) {
					break;
				}
				System.out.println(result);
			} catch (RuntimeException e) {
				System.out.println(e.getMessage());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}


	private String interpretCommand(String[] data, String commandName)  {
		Executable executable;
		String name=Character.toUpperCase(commandName.charAt(0))+commandName.substring(1);
		try {
			Class<? extends Executable> clazz = (Class<? extends Executable>) Class.forName("barracksWars.core.commands." + name);
			Constructor<? extends Executable> construct = clazz.getDeclaredConstructor(String[].class, Repository.class, UnitFactory.class);
			executable=construct.newInstance(data,this.repository,this.unitFactory);

		} catch (NoSuchMethodException | InstantiationException | IllegalAccessException | InvocationTargetException | ClassNotFoundException e) {
			throw  new IllegalStateException(e);
		}

		return executable.execute();
	}




	

}
