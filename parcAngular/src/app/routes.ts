import { Routes } from '@angular/router';
import { AccueilComponent } from './accueil/accueil.component';
import { AttractionComponent } from './attraction/attraction.component';
import { BoutiqueComponent } from './boutique/boutique.component';
import { ProposComponent } from './propos/propos.component';
import { RestaurationComponent } from './restauration/restauration.component';

export const routes: Routes = [
  { path: '', component: AccueilComponent },
  { path: 'attraction', component: AttractionComponent },
  { path: 'boutique', component: BoutiqueComponent },
  { path: 'restauration', component: RestaurationComponent },
  {path: 'propos', component: ProposComponent},
];
