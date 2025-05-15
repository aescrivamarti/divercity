import { Component } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Router, RouterLink } from '@angular/router';
import { FormsModule } from '@angular/forms';
import { firstValueFrom } from 'rxjs';

@Component({
  selector: 'app-registro',
  templateUrl: './registro.component.html',
  standalone: true,
  imports: [FormsModule, RouterLink],
  styleUrls: ['./registro.component.css']
})
export class RegistroComponent {
  usuario = {
    nombre: '',
    apellidos: '',
    email: '',
    telefono: '',
    password: ''
  };
  registroError = '';  // ← Aquí guardamos el mensaje de error

  constructor(private http: HttpClient, private router: Router) {}

  async registrarUsuario() {
    this.registroError = ''; // limpia el mensaje previo
    try {
      // Llamada al backend
      const response = await firstValueFrom(
        this.http.post('http://localhost:8080/api/auth/registrar', this.usuario)
      );
      console.log('Usuario registrado exitosamente:', response);

      // Redirigir al login después del registro
      await this.router.navigate(['/login']);
      console.log('Redirigido al login');
    } catch (error: any) {
      console.error('Error al registrar el usuario:', error);

      // Extrae mensaje del backend o usa uno genérico
      this.registroError = error.error?.message
        || error.error
        || 'Error al registrar el usuario. Por favor, inténtelo de nuevo.';
    }
  }
}
