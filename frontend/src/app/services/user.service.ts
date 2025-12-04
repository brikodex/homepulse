import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
    providedIn: 'root'
})
export class UserService {
    private apiUrl = 'http://localhost:8080/backend-1.0.0/api/v1/users';

    constructor(private http: HttpClient) { }

    getAllUsers(): Observable<any[]> {
        return this.http.get<any[]>(this.apiUrl);
    }

    getUserById(id: number): Observable<any> {
        return this.http.get<any>(`${this.apiUrl}/${id}`);
    }

    updateUser(id: number, userData: any): Observable<void> {
        return this.http.put<void>(`${this.apiUrl}/${id}`, userData);
    }

    deleteUser(id: number): Observable<void> {
        return this.http.delete<void>(`${this.apiUrl}/${id}`);
    }
}
