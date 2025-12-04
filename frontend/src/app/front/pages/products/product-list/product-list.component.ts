import { Component, OnInit } from '@angular/core';
import { ProductService, Product } from '../../../../services/product.service';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { ProductCardComponent } from '../../../../shared/product-card/product-card.component';

@Component({
  selector: 'app-product-list',
  standalone: true,
  imports: [CommonModule, FormsModule, ProductCardComponent],
  templateUrl: './product-list.component.html',
  styleUrls: ['./product-list.component.css']
})
export class ProductListComponent implements OnInit {
  products: Product[] = [];
  filteredProducts: Product[] = [];
  categories: string[] = ['All', 'Kitchen', 'Laundry', 'Living Room', 'Smart Home'];
  selectedCategory: string = 'All';
  sortBy: string = 'newest';
  priceRange: number = 5000;

  constructor(private productService: ProductService) { }

  ngOnInit(): void {
    this.productService.getAllProduct().subscribe(data => {
      this.products = data;
      this.applyFilters();
    });
  }

  applyFilters(): void {
    let temp = [...this.products];

    // Category Filter
    if (this.selectedCategory !== 'All') {
      // Assuming product has a category field, if not we might need to mock or adjust
      // For now, let's filter if the mock data has categories, otherwise return all
      // temp = temp.filter(p => p.category === this.selectedCategory);
    }

    // Price Filter
    temp = temp.filter(p => p.price <= this.priceRange);

    // Sort
    if (this.sortBy === 'low-high') {
      temp.sort((a, b) => a.price - b.price);
    } else if (this.sortBy === 'high-low') {
      temp.sort((a, b) => b.price - a.price);
    } else if (this.sortBy === 'newest') {
      temp.sort((a, b) => b.id - a.id); // Assuming higher ID is newer
    }

    this.filteredProducts = temp;
  }

  onCategoryChange(category: string): void {
    this.selectedCategory = category;
    this.applyFilters();
  }

  onSortChange(event: any): void {
    this.sortBy = event.target.value;
    this.applyFilters();
  }

  onPriceChange(): void {
    this.applyFilters();
  }
}
