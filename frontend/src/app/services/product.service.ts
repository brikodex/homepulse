import { Injectable, inject } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from '../../environments/environment';
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
  private readonly apiUrl: string = `${environment.API_BASE_URL}/products`;
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
