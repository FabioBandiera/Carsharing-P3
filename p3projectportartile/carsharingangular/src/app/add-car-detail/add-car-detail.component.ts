import { Component , OnInit } from '@angular/core';
import { Parcheggio } from '../parcheggio';
import { addNewCarService } from '../addNewCarService';
import { Car } from '../car';
import { Router } from '@angular/router';


@Component({
  selector: 'app-add-car-detail',
  templateUrl: './add-car-detail.component.html',
  styleUrls: ['./add-car-detail.component.css']
})
export class AddCarDetailComponent implements OnInit {

  arrayNumCar: number[] = [];
  codicefiscale: string="mm";
  idpar: string = "000";
  car: Car =  {
    "targa": "az190ib",
    "cfutente_associato": "null",
    "id_parcheggio": "null"
} ;
  parcheggi: Parcheggio[] = [];

  constructor(private addANewCarservice:addNewCarService, private router: Router) { }
  ngOnInit(): void {

      this.addANewCarservice.getAllParksWCars().subscribe(resp => {
      this.parcheggi=resp;
      for(let i=0; i<this.parcheggi.length; i++){
        this.addANewCarservice.getNumCarsForParks(this.parcheggi[i].idParcheggio).subscribe(resp => {
          this.arrayNumCar.push(resp);
        });
     }
      });
    }   

     addANewCar(){
      this.car.targa = ((document.getElementById("sceltaTarga") as HTMLInputElement).value);
      this.idpar = ((document.getElementById("ImmettiId") as HTMLInputElement).value);
      this.codicefiscale = ((document.getElementById("cf") as HTMLInputElement).value);
       this.addANewCarservice.addNewCar(this.car, this.codicefiscale, this.idpar).subscribe(resp => {
         if(resp == "Operazione completata con successo!"){
           alert(resp);
           window.location.reload();
         } else if(resp.startsWith("could")){
          alert("Qualcosa è andato storto, ricontrolla i tuoi dati.")
         } else alert(resp);
       });
     }

  }
