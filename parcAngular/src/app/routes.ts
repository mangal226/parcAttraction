import { FichePersoComponent } from './admin/fiche-perso/fiche-perso.component';
import { AccueilAdminComponent } from './admin/accueil-admin/accueil-admin.component';
import { PlanComponent } from './plan/plan.component';
import { ContactComponent } from './contact/contact.component';
import { Routes } from '@angular/router';
import { AccueilComponent } from './accueil/accueil.component';
import { AttractionComponent } from './attraction/attraction.component';
import { BoutiqueComponent } from './boutique/boutique.component';
import { ProposComponent } from './propos/propos.component';
import { RestaurationComponent } from './restauration/restauration.component';
import { SimulationComponent } from './admin/simulation/simulation.component';


export const routes: Routes = [
  { path: '', component: AccueilComponent },
  { path: 'attraction', component: AttractionComponent },
  { path: 'boutique', component: BoutiqueComponent },
  { path: 'restauration', component: RestaurationComponent },
  {path: 'propos', component: ProposComponent},
  {path: 'contact', component: ContactComponent},
  {path: 'plan', component: PlanComponent},
  {path: 'admin/accueil-admin', component: AccueilAdminComponent},
  {path: 'admin/fiche-perso', component: FichePersoComponent},
  {path: 'admin/simulation', component: SimulationComponent}
];
