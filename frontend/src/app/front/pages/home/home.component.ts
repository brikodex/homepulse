import { ProductCardComponent } from '../../../shared/product-card/product-card.component';
import { Component, OnInit, Inject, PLATFORM_ID } from '@angular/core';
import { CommonModule, isPlatformBrowser } from '@angular/common';
import { RouterModule } from '@angular/router';
import { ProductService, Product } from '../../../services/product.service';
import { HeroSliderComponent } from '../../components/hero-slider/hero-slider.component';
import { BrandSliderComponent } from '../../components/brand-slider/brand-slider.component';
@Component({
  selector: 'app-home',
  standalone: true,
  imports: [CommonModule, RouterModule, ProductCardComponent, HeroSliderComponent, BrandSliderComponent],
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css'],
})
export class HomeComponent implements OnInit {
  products: Product[] = [];
  constructor(
    private productService: ProductService

  ) { }
  ngOnInit(): void {

    this.productService.getAllProduct().subscribe((data) => {
      this.products = data;
    });
  }

}