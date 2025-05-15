import { Component, inject } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { ReservaService } from '../../services/reserva.service';
import { RouterLink } from '@angular/router';

@Component({
  selector: 'app-reservas',
  standalone: true,
  imports: [CommonModule, FormsModule, RouterLink],
  templateUrl: './reservas.component.html',
  styleUrls: ['./reservas.component.css']
})
export class ReservasComponent {
  private reservaService = inject(ReservaService);

  reservas: any[] = [];

  reserva = {
    fecha: '',
    hora: '',
    numero_personas: 1
  };

  constructor() {
    this.cargarReservas();
  }

  cargarReservas() {
    this.reservaService.getReservas().subscribe({
      next: (data) => this.reservas = data,
      error: (err) => console.error('Error al cargar reservas:', err)
    });
  }

  hacerReserva() {
    const userId = localStorage.getItem('userId');

    if (!userId) {
      alert('No se ha podido identificar al usuario. Por favor, vuelve a iniciar sesión.');
      return;
    }

    // Validaciones con parseo explícito
    const numeroPersonas = Number(this.reserva.numero_personas);

    if (!this.reserva.fecha || !this.reserva.hora || !numeroPersonas || numeroPersonas < 1) {
      alert('Todos los campos son obligatorios.');
      return;
    }

    const nuevaReserva = {
      fecha: this.reserva.fecha,
      hora: this.reserva.hora,
      numero_personas: numeroPersonas,
      clienteId: Number(userId)
    };

    this.reservaService.crearReserva(nuevaReserva).subscribe({
      next: () => {
        alert('Reserva realizada con éxito.');
        this.reserva = { fecha: '', hora: '', numero_personas: 1 };
        this.cargarReservas();
      },
      error: (err) => {
        console.error('Error al hacer la reserva:', err);
        alert('Error al hacer la reserva.');
      }
    });
  }

}
