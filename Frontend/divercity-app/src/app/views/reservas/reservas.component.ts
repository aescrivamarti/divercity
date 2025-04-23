import { Component, inject } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { ClienteService } from '../../services/cliente.service';
import { ReservaService } from '../../services/reserva.service';

@Component({
  selector: 'app-reservas',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './reservas.component.html',
  styleUrls: ['./reservas.component.css']
})
export class ReservasComponent {
  private clienteService = inject(ClienteService);
  private reservaService = inject(ReservaService);

  clientes: any[] = [];
  reservas: any[] = [];

  reserva = {
    clienteId: '',
    fecha: ''
  };

  constructor() {
    this.cargarClientes();
    this.cargarReservas();
  }

  cargarClientes() {
    this.clienteService.getClientes().subscribe({
      next: (data) => this.clientes = data,
      error: (err) => console.error('Error al cargar clientes:', err)
    });
  }

  cargarReservas() {
    this.reservaService.getReservas().subscribe({
      next: (data) => this.reservas = data,
      error: (err) => console.error('Error al cargar reservas:', err)
    });
  }

  hacerReserva() {
    if (!this.reserva.clienteId || !this.reserva.fecha) {
      alert('Todos los campos son obligatorios.');
      return;
    }

    this.reservaService.crearReserva(this.reserva).subscribe({
      next: (res) => {
        alert('Reserva realizada con éxito.');
        this.reserva = { clienteId: '', fecha: '' };
        this.cargarReservas();
      },
      error: (err) => {
        console.error('Error al hacer la reserva:', err);
        alert('Error al hacer la reserva.');
      }
    });
  }
}
