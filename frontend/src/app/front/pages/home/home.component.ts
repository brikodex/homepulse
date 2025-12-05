import { ProductCardComponent } from '../../../shared/product-card/product-card.component';
import { Component, signal, inject, ChangeDetectionStrategy } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';
import { ProductService, Product } from '../../../services/product.service';
import { HeroSliderComponent } from '../../components/hero-slider/hero-slider.component';
import { BrandSliderComponent } from '../../components/brand-slider/brand-slider.component';
@Component({
  selector: 'app-home',
  imports: [CommonModule, RouterModule, ProductCardComponent, HeroSliderComponent, BrandSliderComponent],
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css'],
  changeDetection: ChangeDetectionStrategy.OnPush
})
export class HomeComponent {
  private readonly productService = inject(ProductService);
  protected readonly products = signal<Product[]>([]);
  constructor() {
    this.productService.getAllProduct().subscribe((data) => {
      this.products.set(data);
    });
  }
}