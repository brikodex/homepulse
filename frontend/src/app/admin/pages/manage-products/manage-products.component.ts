import { Component, OnInit } from '@angular/core';
import { ProductService, Product } from './../../../services/product.service';
import { Router, RouterModule } from '@angular/router';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-manage-products',
  standalone: true,
  imports: [CommonModule, RouterModule],
  templateUrl: './manage-products.component.html',
  styleUrl: './manage-products.component.css',
})
export class ManageProductsComponent implements OnInit {
  products: Product[] = [];

  constructor(private productService: ProductService, private router: Router) { }

  ngOnInit(): void {
    this.loadProducts();
  }

  loadProducts(): void {
    this.productService.getAllProduct().subscribe((data) => {
      this.products = data;
    });
  }

  addProduct(): void {
    this.router.navigate(['/admin/products/new']);
  }

  editProduct(id: number): void {
    this.router.navigate(['/admin/products/edit', id]);
  }

  deleteProduct(id: number): void {
    if (confirm('Are you sure you want to delete this product?')) {
      this.productService.deleteProduct(id).subscribe(() => {
        this.loadProducts(); // Reload list
      });
    }
  }
}
