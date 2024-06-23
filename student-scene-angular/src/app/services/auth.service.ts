import { Injectable } from '@angular/core';
import {HttpClient, HttpResponse} from "@angular/common/http";
import {Observable, tap} from "rxjs";
import {User} from "../interfaces/user";

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  constructor(private http: HttpClient) { }

  login(body: any): Observable<HttpResponse<any>> {
    return this.http.post<User>("http://localhost:8080/api/v1/auth/login",body, {observe: 'response', withCredentials: false})
      .pipe(tap(res => this.setSession(res)))
  }

  private setSession(authResult: HttpResponse<User>) {
    const token = authResult.headers.get('Authorization')?.substring(7);
    if(token) { localStorage.setItem('token', token); }
    else console.error('Token not found in response')

  }
  logout() {
    localStorage.removeItem("token");
  }

  public isLoggedIn() {
    return localStorage.getItem('token')!=null;
  }

  isLoggedOut() {
    return !this.isLoggedIn();
  }

}
