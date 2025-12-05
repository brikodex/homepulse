import { Injectable, inject } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
export interface Product {
  id: number;
  name: string;
  description: string;
  price: number;
  images: string;
}
@Injectable({ providedIn: 'root' })
export class ProductService {
  private readonly http = inject(HttpClient);
  private readonly apiUrl = 'http://localhost:8080/backend-1.0.0/api/v1/products';
  getAllProduct(): Observable<Product[]> {
    return this.http.get<Product[]>(this.apiUrl);
  }
  getProductById(id: number): Observable<Product> {
    return this.http.get<Product>(`${this.apiUrl}/${id}`);
  }
  createProduct(product: any): Observable<void> {
    return this.http.post<void>(this.apiUrl, product);
  }
  updateProduct(id: number, product: any): Observable<void> {
    return this.http.put<void>(`${this.apiUrl}/${id}`, product);
  }
  deleteProduct(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/${id}`);
  }
}
