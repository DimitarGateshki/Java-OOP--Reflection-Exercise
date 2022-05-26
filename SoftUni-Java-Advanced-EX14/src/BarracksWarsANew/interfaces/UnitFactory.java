package barracksWars.interfaces;

import jdk.jshell.spi.ExecutionControl;

public interface UnitFactory {

    barracksWars.interfaces.Unit createUnit(String unitType) throws ExecutionControl.NotImplementedException;
}