import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from '../../environments/environment';

@Injectable({
    providedIn: 'root'
})
export class OrderService {
    private apiUrl = `${environment.API_BASE_URL}/orders`;

    constructor(private http: HttpClient) { }

    createOrder(orderData: any): Observable<any> {
        return this.http.post<any>(this.apiUrl, orderData);
    }

    getAllOrders(): Observable<any[]> {
        return this.http.get<any[]>(this.apiUrl);
    }

    getOrderById(id: number): Observable<any> {
        return this.http.get<any>(`${this.apiUrl}/${id}`);
    }

    updateOrderStatus(id: number, status: string): Observable<void> {
        return this.http.put<void>(`${this.apiUrl}/${id}/status`, { status });
    }

    getUserOrders(userId: number): Observable<any[]> {
        return this.http.get<any[]>(`${this.apiUrl}/user/${userId}`);
    }
}
