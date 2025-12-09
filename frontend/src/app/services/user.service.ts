import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from '../../environments/environment';

@Injectable({
    providedIn: 'root'
})
export class UserService {
    private apiUrl = `${environment.API_BASE_URL}/users`;

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
