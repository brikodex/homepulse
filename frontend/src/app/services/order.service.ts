import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
    providedIn: 'root'
})
export class OrderService {
    private apiUrl = 'http://localhost:8080/backend-1.0.0/api/v1/orders';

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
