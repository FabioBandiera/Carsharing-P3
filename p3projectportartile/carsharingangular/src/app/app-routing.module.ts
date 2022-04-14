import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AddCarDetailComponent } from './add-car-detail/add-car-detail.component';
import { AreaerogatoreComponent } from './areaerogatore/areaerogatore.component';
import { AreautenteComponent } from './areautente/areautente.component';
import { InfoRiconsegneUtentiNotDoneComponent } from './info-riconsegne-utenti-not-done/info-riconsegne-utenti-not-done.component';
import { LoginpageerogatoreComponent } from './loginpageerogatore/loginpageerogatore.component';
import { LoginpageutenteComponent } from './loginpageutente/loginpageutente.component';
import { MainpageComponent } from './mainpage/mainpage.component';
import { ParkSettleDetailComponent } from './park-settle-detail/park-settle-detail.component';
import { PrenotazioniUtentiComponent } from './prenotazioni-utenti/prenotazioni-utenti.component';
import { RegisterutenteComponent } from './registerutente/registerutente.component';
import { RiconsegneUtentiComponent } from './riconsegne-utenti/riconsegne-utenti.component';

const routes: Routes = [
  { path: 'loginpageutente', component: LoginpageutenteComponent},
  { path: 'loginpageerogatore', component: LoginpageerogatoreComponent},
  { path: 'registerutente', component: RegisterutenteComponent},
  { path: 'areaerogatore', component: AreaerogatoreComponent},
  { path: 'areautente', component: AreautenteComponent},
  { path: 'areaerogatore/addcardetail', component: AddCarDetailComponent},
  { path: 'areaerogatore/park-settle', component: ParkSettleDetailComponent},
  { path: 'areaerogatore/info-riconsegne-utenti-not-done', component: InfoRiconsegneUtentiNotDoneComponent},
  { path: 'areautente/prenotazioni-utenti', component: PrenotazioniUtentiComponent},
  { path: 'areautente/riconsegne-utenti', component: RiconsegneUtentiComponent},
  { path: 'mainpage', component: MainpageComponent}
  

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
