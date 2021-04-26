import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';

const API_URL = 'http://localhost:8080/api/users/';

@Injectable()
export class UserManagementService {

  constructor(private http: HttpClient) { }

  getAllUsersExtended(): Observable<any> {
    return this.http.get(API_URL + '', { responseType: 'text' });
  }


}
