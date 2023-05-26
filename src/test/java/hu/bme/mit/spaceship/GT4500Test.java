package hu.bme.mit.spaceship;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

public class GT4500Test {

  private GT4500 ship;
  private TorpedoStore primaryTorpedoStore;
  private TorpedoStore secondaryTorpedoStore;

  @BeforeEach
  public void init(){
    primaryTorpedoStore = mock(TorpedoStore.class);
    secondaryTorpedoStore = mock(TorpedoStore.class);
    this.ship = new GT4500(primaryTorpedoStore, secondaryTorpedoStore);
  }

  @Test
  public void fireTorpedo_Single_Success(){
    // Arrange
    when(primaryTorpedoStore.fire(1)).thenReturn(true);
    when(secondaryTorpedoStore.fire(1)).thenReturn(true);
    
    // Act
    ship.fireTorpedo(FiringMode.SINGLE);

    // Assert
    verify(primaryTorpedoStore, times(1)).fire(1);
    verify(secondaryTorpedoStore, times(0)).fire(1);
  }

  @Test
  public void fireTorpedo_All_Success(){
    // Arrange
    when(primaryTorpedoStore.getTorpedoCount()).thenReturn(10);
    when(primaryTorpedoStore.fire(primaryTorpedoStore.getTorpedoCount())).thenReturn(true);
    when(secondaryTorpedoStore.getTorpedoCount()).thenReturn(10);
    when(secondaryTorpedoStore.fire(secondaryTorpedoStore.getTorpedoCount())).thenReturn(true);
   
    // Act
    ship.fireTorpedo(FiringMode.ALL);

    // Assert
    verify(primaryTorpedoStore, times(2)).getTorpedoCount();
    verify(primaryTorpedoStore, times(2)).fire(primaryTorpedoStore.getTorpedoCount());
    verify(secondaryTorpedoStore, times(2)).getTorpedoCount();
    verify(secondaryTorpedoStore, times(2)).fire(secondaryTorpedoStore.getTorpedoCount());
  }
}
