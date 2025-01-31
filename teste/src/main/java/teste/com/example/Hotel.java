package teste;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Hotel {
    private List<Quarto> quartos;
    private List<Reserva> reservas;

    public Hotel() {
        this.quartos = new ArrayList<>();
        this.reservas = new ArrayList<>();
    }

    public void adicionarQuarto(Quarto quarto) {
        quartos.add(quarto);
    }

    public Reserva fazerReserva(Hospede hospede, int numeroQuarto, LocalDate checkIn, LocalDate checkOut) {
        Quarto quarto = buscarQuarto(numeroQuarto);
        if (quarto != null && quarto.isDisponivel()) {
            Reserva reserva = new Reserva(hospede, quarto, checkIn, checkOut);
            reservas.add(reserva);
            quarto.setDisponivel(false); // Atualiza a disponibilidade do quarto
            return reserva;
        }
        return null; // Retornar null se o quarto não estiver disponível ou não existir
    }

    private Quarto buscarQuarto(int numeroQuarto) {
        for (Quarto quarto : quartos) {
            if (quarto.getNumero() == numeroQuarto) {
                return quarto;
            }
        }
        return null;
    }

    public boolean reservarQuarto(Quarto quarto) {
        if (quarto.isDisponivel()) {
            quarto.setDisponivel(false);
            return true;
        }
        return false;
    }

    // Getters e Setters para quartos e reservas
    public List<Quarto> getQuartos() {
        return new ArrayList<>(quartos); // Retorna uma cópia para evitar modificações externas
    }

    public void setQuartos(List<Quarto> quartos) {
        this.quartos = new ArrayList<>(quartos); // Cria uma cópia para evitar modificações externas
    }

    public List<Reserva> getReservas() {
        return new ArrayList<>(reservas); // Retorna uma cópia para evitar modificações externas
    }

    public void setReservas(List<Reserva> reservas) {
        this.reservas = new ArrayList<>(reservas); // Cria uma cópia para evitar modificações externas
    }

}