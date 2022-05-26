package BarracksWarsTheCommandsStrikeBack.barracksWars.core.factories;

import barracksWars.interfaces.Unit;
import barracksWars.interfaces.UnitFactory;
import barracksWars.units.*;

import java.lang.reflect.InvocationTargetException;

public class UnitFactoryImpl implements UnitFactory {

	private static final String UNITS_PACKAGE_NAME =
			"barracksWars.units.";

	@Override
	public Unit createUnit(String unitType)  {
		Unit unit;
		try {
			Class<?> clazz = Class.forName(UNITS_PACKAGE_NAME + unitType);
			unit =(Unit)clazz.getDeclaredConstructor().newInstance();
		} catch (ClassNotFoundException | InstantiationException | InvocationTargetException | NoSuchMethodException | IllegalAccessException e) {
			throw  new IllegalStateException(e);
		}
		return unit;
	}
}
