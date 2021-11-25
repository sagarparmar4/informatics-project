import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { CURRENCY_EXCHANGE_API_TOKEN } from '../utils/constants';

@Injectable({
  providedIn: 'root'
})
export class MasterService {

  constructor(private http: HttpClient) { }

  getProducts(searchQuery: string, filters: { [key: string]: string; }): Observable<any> {
    let params: HttpParams = new HttpParams();
    params = params.set('searchQuery', searchQuery);

    return this.http.post('/getProducts', filters, { params });
  }

  submitFeedback(data: any): Observable<any> {
    return this.http.post('/submitFeedback', data);
  }

  convertCurrency(from: string, to: string) {
    // let params: HttpParams = new HttpParams();
    // params = params.set('access_key', CURRENCY_EXCHANGE_API_TOKEN);
    // params = params.set('from', from);
    // params = params.set('to', to);
    // params = params.set('amount', amount.toString());

    // return this.http.get('http://api.exchangeratesapi.io/v1/convert', { params });

    let params: HttpParams = new HttpParams();
    params = params.set('access_key', CURRENCY_EXCHANGE_API_TOKEN);
    params = params.set('base', from);
    params = params.set('symbols', to);

    let url: string = 'http://api.exchangeratesapi.io/v1/' + new Date().toISOString().slice(0, 10);
    return this.http.get(url, { params });
  }

  getAllCurrencies(): Observable<any> {
    let params: HttpParams = new HttpParams();
    params = params.set('access_key', CURRENCY_EXCHANGE_API_TOKEN);

    return this.http.get('http://api.exchangeratesapi.io/v1/symbols', { params });
  }

}
