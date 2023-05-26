package hu;

import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import hu.bme.mit.spaceship.FiringMode;
import hu.bme.mit.spaceship.GT4500;
import hu.bme.mit.spaceship.TorpedoStore;

public class JUnit5Test {

private GT4500 ship;
private TorpedoStore primaryTorpedoStore;
private TorpedoStore secondaryTorpedoStore;

@BeforeEach
public void init(){
    this.primaryTorpedoStore = mock(TorpedoStore.class);
    this.secondaryTorpedoStore = mock(TorpedoStore.class);
    this.ship = new GT4500(primaryTorpedoStore, secondaryTorpedoStore);
}

@Test
public void fireTorpedo_Single_Primary_Fired_Succes(){
    when(primaryTorpedoStore.fire(1)).thenReturn(true);
    ship.fireTorpedo(FiringMode.SINGLE);
    verify(primaryTorpedoStore, times(1)).fire(1);
}

@Test
public void fireTorpedo_Single_Secondary_Fired_Succes(){
    when(secondaryTorpedoStore.fire(1)).thenReturn(true);
    when(secondaryTorpedoStore.isEmpty()).thenReturn(false);
    ship.fireTorpedo(FiringMode.SINGLE);
    ship.fireTorpedo(FiringMode.SINGLE);
    verify(secondaryTorpedoStore, times(1)).fire(1);
}


@Test
public void fireTorpedo_Single_Secondary_Fired_Primary_Empty_Success(){
    when(primaryTorpedoStore.isEmpty()).thenReturn(true);
    when(secondaryTorpedoStore.fire(1)).thenReturn(true);
    ship.fireTorpedo(FiringMode.SINGLE);
    verify(secondaryTorpedoStore, times(1)).fire(1);
    verify(primaryTorpedoStore, times(0)).fire(1);
}
@Test
public void fireTorpedo_All_Only_Primary_Has_Torpedo(){
    when(primaryTorpedoStore.getTorpedoCount()).thenReturn(0);
    when(primaryTorpedoStore.fire(primaryTorpedoStore.getTorpedoCount())).thenReturn(false);
    ship.fireTorpedo(FiringMode.ALL);
    verify(primaryTorpedoStore, times(2)).fire(primaryTorpedoStore.getTorpedoCount());
}








}
