import { Component, signal, computed, inject, ChangeDetectionStrategy, effect } from '@angular/core';
import { ProductService, Product } from '../../../../services/product.service';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { ProductCardComponent } from '../../../../shared/product-card/product-card.component';
@Component({
  selector: 'app-product-list',
  imports: [CommonModule, FormsModule, ProductCardComponent],
  templateUrl: './product-list.component.html',
  styleUrls: ['./product-list.component.css'],
  changeDetection: ChangeDetectionStrategy.OnPush
})
export class ProductListComponent {
  private readonly productService = inject(ProductService);
  protected readonly products = signal<Product[]>([]);
  protected readonly categories = ['All', 'Kitchen', 'Laundry', 'Living Room', 'Smart Home'];
  protected readonly selectedCategory = signal<string>('All');
  protected readonly sortBy = signal<string>('newest');
  protected readonly priceRange = signal<number>(5000);
  protected readonly filteredProducts = computed(() => {
    let temp = [...this.products()];
    // Category Filter
    if (this.selectedCategory() !== 'All') {
      // Assuming product has a category field, if not we might need to mock or adjust
      // For now, let's filter if the mock data has categories, otherwise return all
      // temp = temp.filter(p => p.category === this.selectedCategory());
    }
    // Price Filter
    temp = temp.filter(p => p.price <= this.priceRange());
    // Sort
    const sortValue = this.sortBy();
    if (sortValue === 'low-high') {
      temp.sort((a, b) => a.price - b.price);
    } else if (sortValue === 'high-low') {
      temp.sort((a, b) => b.price - a.price);
    } else if (sortValue === 'newest') {
      temp.sort((a, b) => b.id - a.id); // Assuming higher ID is newer
    }
    return temp;
  });
  constructor() {
    // Load products on initialization
    this.productService.getAllProduct().subscribe(data => {
      this.products.set(data);
    });
  }
  protected onCategoryChange(category: string): void {
    this.selectedCategory.set(category);
  }
  protected onSortChange(event: Event): void {
    const target = event.target as HTMLSelectElement;
    this.sortBy.set(target.value);
  }
  protected onPriceChange(): void {
    // Price range is already bound via ngModel, no action needed
    // The computed signal will automatically recalculate
  }
}
