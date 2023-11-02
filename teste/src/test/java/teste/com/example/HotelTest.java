package teste;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyBoolean;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class HotelTest {
    private Hotel hotel;

@Mock
private Quarto quartoMock;

@Mock
private Hospede hospedeMock;

@BeforeEach
public void setUp() {
    MockitoAnnotations.openMocks(this);
    hotel = new Hotel();
    hotel.adicionarQuarto(quartoMock);
}

@Test
public void fazerReservaQuartoDisponivel() {
    when(quartoMock.isDisponivel()).thenReturn(true);
    when(quartoMock.getNumero()).thenReturn(101);

    Reserva reserva = hotel.fazerReserva(hospedeMock, 101, LocalDate.now(), LocalDate.now().plusDays(3));

    assertNotNull(reserva, "A reserva deve ser bem-sucedida para um quarto disponível.");
    verify(quartoMock).setDisponivel(false);
}

@Test
public void fazerReservaQuartoIndisponivel() {
    when(quartoMock.isDisponivel()).thenReturn(false);
    when(quartoMock.getNumero()).thenReturn(101);

    Reserva reserva = hotel.fazerReserva(hospedeMock, 101, LocalDate.now(), LocalDate.now().plusDays(3));

    assertNull(reserva, "A reserva deve falhar para um quarto indisponível.");
    verify(quartoMock, never()).setDisponivel(anyBoolean());
}

@Test
public void tentarFazerReservaQuartoInexistente() {
    Reserva reserva = hotel.fazerReserva(hospedeMock, 999, LocalDate.now(), LocalDate.now().plusDays(3));

    assertNull(reserva, "A reserva deve falhar para um quarto inexistente.");
}

@Test
public void adicionarQuartoAoHotel() {
    Quarto novoQuarto = mock(Quarto.class);
    when(novoQuarto.getNumero()).thenReturn(102);

    hotel.adicionarQuarto(novoQuarto);
    Reserva reserva = hotel.fazerReserva(hospedeMock, 102, LocalDate.now(), LocalDate.now().plusDays(3));

    assertNotNull(reserva, "A reserva deve ser bem-sucedida após adicionar um novo quarto.");
}

@Test
public void verificarQuartosDisponiveisAposReserva() {
    when(quartoMock.isDisponivel()).thenReturn(true);
    when(quartoMock.getNumero()).thenReturn(101);

    hotel.fazerReserva(hospedeMock, 101, LocalDate.now(), LocalDate.now().plusDays(3));

    assertFalse(quartoMock.isDisponivel(), "O quarto não deve estar disponível após ser reservado.");
}
   
}