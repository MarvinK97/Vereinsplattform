import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Observable} from "rxjs";


const API_URL = 'http://localhost:8080/api/club/';
const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};

@Injectable({
  providedIn: 'root'
})
export class ClubService {

  constructor(private http: HttpClient) { }

  getClub(): Observable<any>  {
    return this.http.get(API_URL + 'get')
  }

  joinClub(id: number): Observable<any> {
    return this.http.post(API_URL + 'join', {
      clubId: id
    }, httpOptions);
  }

  leaveClub(id: number): Observable<any> {
    return  this.http.post(API_URL + 'leave', {
      clubId: id
    }, httpOptions)
  }

}
