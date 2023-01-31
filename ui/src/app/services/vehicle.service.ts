import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Vehicle } from '../classes/vehicle';

@Injectable({
  providedIn: 'root'
})
export class VehicleService {

    private serverUrl:string;

    constructor(private http: HttpClient) { 
      this.serverUrl = "http://localhost:8080/api/cardealership";
    }

    getVehicleById(id: number) : Observable<Vehicle> {
      return this.http.get<Vehicle>(this.serverUrl + '/inventory/details/' + id);
    }

    getFeaturedVehicles() : Observable<Vehicle[]>{
      return this.http.get<Vehicle[]>(this.serverUrl + '/home/featured');
    }

    addVehicle(vehicle: Vehicle): Observable<Vehicle> {
      return this.http.post<Vehicle>(this.serverUrl + '/admin/addvehicle', vehicle);
    }

    editVehicle(vehicle: Vehicle): Observable<Vehicle> {
      return this.http.put<Vehicle>(this.serverUrl + '/admin/editvehicle', vehicle);
    }

    editPictureById(id: number, picture: Blob): Observable<boolean> {
      let pictureHeaders = new HttpHeaders();
      pictureHeaders = pictureHeaders.append('Content-Type', 'application/octect-stream');
      pictureHeaders = pictureHeaders.append('Access-Control-Allow-Origin', '*');

      return this.http.post<boolean>(this.serverUrl + '/admin/editvehicle/editpicture/' + id , picture, {headers: pictureHeaders});

      
    }

    deleteVehicleById(id: number): Observable<boolean> {
      return this.http.delete<boolean>(this.serverUrl + '/admin/deletevehicle/' + id);
    }
    
    uploadPicture(vehicleId: number, picture: Blob): Observable<Blob> {
      let pictureHeaders = new HttpHeaders();
      pictureHeaders = pictureHeaders.append('Content-Type', 'application/octect-stream');

      return this.http.post<Blob>(this.serverUrl + '/admin/addvehicle/uploadpicture/' + vehicleId, picture, {headers: pictureHeaders});
    }

    searchVehicles( elements: any, vehicleType: string, userRole: string): Observable<Vehicle[]> {
      let queryParams = new HttpParams();

      queryParams = queryParams.append('value', elements['value']);
      queryParams = queryParams.append('minPrice', elements['minPrice']);
      queryParams = queryParams.append('maxPrice', elements['maxPrice']);
      queryParams = queryParams.append('minYear', elements['minYear']);
      queryParams = queryParams.append('maxYear', elements['maxYear']);
      queryParams = queryParams.append('userRole', userRole);

      if (vehicleType === 'New') {
        return this.http.get<Vehicle[]>(this.serverUrl + '/inventory/new', {params: queryParams});
      }

      if (vehicleType === 'Used') {
        return this.http.get<Vehicle[]>(this.serverUrl + '/inventory/used', {params: queryParams});
      }

      if (userRole === 'Sales') {
        return this.http.get<Vehicle[]>(this.serverUrl + '/sales', {params: queryParams});
      }

      return this.http.get<Vehicle[]>(this.serverUrl + '/admin/vehicles', {params: queryParams});
    }
  }