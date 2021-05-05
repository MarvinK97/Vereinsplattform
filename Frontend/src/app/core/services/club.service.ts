import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Observable} from "rxjs";


const API_URL = 'http://localhost:8080/api/clubs/';
const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};

@Injectable({
  providedIn: 'root'
})
export class ClubService {

  constructor(private http: HttpClient) { }

  getClub(): Observable<any>  {
    return this.http.get(API_URL + 'users');
  }

  joinClub(id: number): Observable<any> {
    return this.http.put(API_URL + 'users/' + id, httpOptions);
  }

  leaveClub(id: number): Observable<any> {
    return  this.http.delete(API_URL + 'users/' + id, httpOptions);
  }

}
