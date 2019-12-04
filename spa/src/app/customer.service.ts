import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from './../environments/environment';

@Injectable({
  providedIn: 'root'
})
export class CustomerService {

  private baseUrl = environment.apiUrl;

  constructor(private http: HttpClient) { }

  getCustomer(id: number): Observable<any> {
    return this.http.get(`${this.baseUrl}/${id}`);
  }

  getCustomersList(): Observable<any> {
    return this.http.get(`${this.baseUrl}`);
  }

  getCustomersListByCountry(name: String): Observable<any> {
    return this.http.get(`${this.baseUrl}/search/findByCountry?name=${name}`);
  }

  getCustomersListByPhoneState(state: String): Observable<any> {
    return this.http.get(`${this.baseUrl}/search/findByPhoneState?state=${state}`);
  }

}
