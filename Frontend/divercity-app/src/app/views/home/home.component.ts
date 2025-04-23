import { Component } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { CommonModule } from '@angular/common';
import {RouterLink} from "@angular/router";

@Component({
  selector: 'app-home',
  standalone: true,
  imports: [CommonModule, RouterLink],
  templateUrl: './home.component.html',
  styleUrl: './home.component.css'
})
export class HomeComponent {
  clientes: any[] = [];

  constructor(private http: HttpClient) {
    this.http.get<any[]>('/api/clientes').subscribe({
      next: (data) => {
        this.clientes = data;
        console.log('Clientes cargados:', data);
      },
      error: (err) => {
        console.error('Error al cargar clientes:', err);
      }
    });
  }
}
