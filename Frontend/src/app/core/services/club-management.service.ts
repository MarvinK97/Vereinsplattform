import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Observable} from 'rxjs';

const API_URL = 'http://localhost:8080/api/club/';
const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};

@Injectable()
export class ClubManagementService {

  constructor(private http: HttpClient) { }

  getAllClubs(): Observable<any> {
    return this.http.get(API_URL + 'all', { responseType: 'text' });
  }

  createClub(club: Club): Observable<any> {
    return this.http.post(API_URL + 'createClub', {
      name: club.name,
      street: club.street,
      zipcode: club.zipcode,
      city: club.city,
    }, httpOptions);
  }

  updateClub(club: Club): Observable<any> {
    return this.http.put(API_URL + 'update', {
      id: club.id,
      name: club.name,
      street: club.street,
      zipcode: club.zipcode,
      city: club.city,
    }, httpOptions);
  }

  deleteClub(club: Club): Observable<any> {
    return this.http.put(API_URL + 'delete', {
      id: club.id,
      name: club.name,
      street: club.street,
      zipcode: club.zipcode,
      city: club.city,
    }, httpOptions);
  }



}

export interface Club {
  id: number;
  name: string;
  street: string;
  zipcode: string;
  city: string;
}