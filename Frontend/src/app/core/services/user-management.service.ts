import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Observable} from 'rxjs';

const API_URL = 'http://localhost:8080/api/users/';
const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};

@Injectable()
export class UserManagementService {

  constructor(private http: HttpClient) { }

  getAllUsers(): Observable<any> {
    return this.http.get(API_URL + '', { responseType: 'text' });
  }

  updateUser(user: User): Observable<any> {
    return this.http.put(API_URL + '', {
      username: user.email,
      id: user.id,
      email: user.username
    }, httpOptions)
  };

  deleteUser(user: User): Observable<any> {
    return this.http.delete(API_URL + user.id);
  }

}


export interface User {
  username: string;
  id: number;
  email: string;
}
