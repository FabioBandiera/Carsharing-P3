import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { LoginpageutenteComponent } from './loginpageutente/loginpageutente.component';
import { LoginpageerogatoreComponent } from './loginpageerogatore/loginpageerogatore.component';
import { RegisterutenteComponent } from './registerutente/registerutente.component';
import { HttpClientModule } from '@angular/common/http';
import { AreaerogatoreComponent } from './areaerogatore/areaerogatore.component';
import { AreautenteComponent } from './areautente/areautente.component';
import { AddCarDetailComponent } from './add-car-detail/add-car-detail.component';
import { ParkSettleDetailComponent } from './park-settle-detail/park-settle-detail.component';
import { PrenotazioniUtentiComponent } from './prenotazioni-utenti/prenotazioni-utenti.component';
import { InfoRiconsegneUtentiNotDoneComponent } from './info-riconsegne-utenti-not-done/info-riconsegne-utenti-not-done.component';
import { RiconsegneUtentiComponent } from './riconsegne-utenti/riconsegne-utenti.component';
import { MainpageComponent } from './mainpage/mainpage.component';

@NgModule({
  declarations: [
    AppComponent,
    LoginpageutenteComponent,
    LoginpageerogatoreComponent,
    RegisterutenteComponent,
    AreaerogatoreComponent,
    AreautenteComponent,
    AddCarDetailComponent,
    ParkSettleDetailComponent,
    PrenotazioniUtentiComponent,
    InfoRiconsegneUtentiNotDoneComponent,
    RiconsegneUtentiComponent,
    MainpageComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
