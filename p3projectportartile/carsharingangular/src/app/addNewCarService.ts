import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Parcheggio } from './parcheggio';
import { Car } from './car';

@Injectable({
  providedIn: 'root'
})
export class addNewCarService {

  constructor(private http: HttpClient) { }

  getAllParksWCars(){
    return this.http.get<Parcheggio[]>("http://localhost:8080/controller/findAllParks");
  }

  addNewCar(car: Car, codiceFiscale: string, id_parcheggio: string){
    const requestOptions: Object = {
      /* other options here */
      responseType: 'text'
    }
    return this.http.post<string>("http://localhost:8080/controller/addNewCar/" + codiceFiscale + "/" +  id_parcheggio, car, requestOptions);
  }

  getNumCarsForParks(id_parcheggio: string){
    const requestOptions: Object = {
      /* other options here */
      responseType: 'text'
    }
    return this.http.get<number>("http://localhost:8080/controller/getNumCarsByParkId/" + id_parcheggio);
  }

}
