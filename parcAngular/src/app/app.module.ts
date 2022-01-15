import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { AppComponent } from './app.component';
import { HeaderComponent } from './header/header.component';
import { AccueilComponent } from './accueil/accueil.component';
import { AttractionComponent } from './attraction/attraction.component';
import { RestaurationComponent } from './restauration/restauration.component';
import { BoutiqueComponent } from './boutique/boutique.component';
import { RouterModule } from '@angular/router';
import { routes } from './routes';
import { FooterComponent } from './footer/footer.component';
import { ProposComponent } from './propos/propos.component';


@NgModule({
  declarations: [
    AppComponent,
    HeaderComponent,
    AccueilComponent,
    AttractionComponent,
    RestaurationComponent,
    BoutiqueComponent,
    FooterComponent,
    ProposComponent
  ],
  imports: [
    BrowserModule,
    RouterModule.forRoot(routes)

  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
