import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

export interface Reserva {
  id?: number;                  // Se genera automáticamente
  fecha: string;
  hora: string;
  numero_personas: number;
  clienteId: number | string;  // Viene del localStorage (como string)
}

@Injectable({
  providedIn: 'root'
})
export class ReservaService {
  private apiUrl = 'http://localhost:8080/api/reservas';

  constructor(private http: HttpClient) {}

  // Obtener todas las reservas (con credenciales)
  getReservas(): Observable<Reserva[]> {
    return this.http.get<Reserva[]>(this.apiUrl, {
      withCredentials: true
    });
  }

  // Crear nueva reserva (con credenciales)
  crearReserva(reserva: Reserva): Observable<Reserva> {
    return this.http.post<Reserva>(this.apiUrl, reserva, {
      withCredentials: true
    });
  }
}
