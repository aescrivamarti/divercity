import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterLink } from '@angular/router';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-atracciones',
  standalone: true,
  imports: [CommonModule, RouterLink],
  templateUrl: './atracciones.component.html',
  styleUrls: ['./atracciones.component.css']
})
export class AtraccionesComponent implements OnInit {
  atracciones: any[] = [];
  carouselImages: string[] = [];

  constructor(private http: HttpClient) {}

  ngOnInit() {
    this.atracciones = [
      { id: 1, nombre: 'Montaña Rusa', image: 'montana.png', descripcion: 'Emoción extrema.' },
      { id: 2, nombre: 'Tiovivo', image: 'tiovivo.jpg', descripcion: 'Ideal para niños.' },
      { id: 3, nombre: 'Noria', image: 'noria.jpg', descripcion: 'Vistas panorámicas.' },
      { id: 4, nombre: 'Casa del Terror', image: 'terror.jpg', descripcion: '¿Te atreves?' },
      { id: 5, nombre: 'Splash', image: 'splash.jpg', descripcion: 'Diversión acuática.' },
      { id: 6, nombre: 'Circuito de Karts', image: 'karts.jpg', descripcion: 'Velocidad y control.' },
      { id: 7, nombre: 'Zona infantil', image: 'infantil.jpg', descripcion: 'Diversión segura.' },
      { id: 8, nombre: 'Barcos Vikingos', image: 'vikingos.jpg', descripcion: 'Aventura en el mar.' }
    ];

    // Define aquí tus imágenes de carrusel (pon los nombres que luego subirás a assets/carousel/)
    this.carouselImages = [
      'slide1.jpg',
      'slide2.jpg',
      'slide3.jpg'
    ];
  }
}
