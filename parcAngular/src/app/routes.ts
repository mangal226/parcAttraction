import { ResultSimuComponent } from './admin/result-simu/result-simu.component';
import { InscriptionComponent } from './inscription/inscription.component';
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
import { FichePaieComponent } from './admin/fiche-paie/fiche-paie.component';
import { PlanModifComponent } from './admin/plan-modif/plan-modif.component';
import { AccueilOperateurComponent } from './operateur/accueil-operateur/accueil-operateur.component';
import { FichePaieOperateurComponent } from './operateur/fiche-paie-operateur/fiche-paie-operateur.component';
import { FichePersoOperateurComponent } from './operateur/fiche-perso-operateur/fiche-perso-operateur.component';
import { AccueilCaissierComponent } from './caissier/accueil-caissier/accueil-caissier.component';
import { FichePersoCaissierComponent } from './caissier/fiche-perso-caissier/fiche-perso-caissier.component';
import { FichePaieCaissierComponent } from './caissier/fiche-paie-caissier/fiche-paie-caissier.component';
import { ConnexionComponent } from './connexion/connexion.component';
import { UpdateAttractionComponent } from './update-attraction/update-attraction.component';
import { UpdateBoutiqueComponent } from './update-boutique/update-boutique.component';
import { UpdateRestaurationComponent } from './update-restauration/update-restauration.component';

export const routes: Routes = [
  { path: '', component: AccueilComponent },
  { path: 'attraction', component: AttractionComponent },
  { path: 'boutique', component: BoutiqueComponent },
  { path: 'boutique/update', component: UpdateBoutiqueComponent },
  { path: 'boutique/update/:id', component: UpdateBoutiqueComponent },
  { path: 'restauration', component: RestaurationComponent },
  { path: 'restauration/update', component: UpdateRestaurationComponent },
  { path: 'restauration/update/:id', component: UpdateRestaurationComponent },
  { path: 'attraction/update', component: UpdateAttractionComponent },
  { path: 'attraction/update/:id', component: UpdateAttractionComponent },
  { path: 'propos', component: ProposComponent },
  { path: 'contact', component: ContactComponent },
  { path: 'plan', component: PlanComponent },
  { path: 'admin/accueil-admin', component: AccueilAdminComponent },
  { path: 'admin/fiche-perso', component: FichePersoComponent },
  { path: 'admin/simulation', component: SimulationComponent },
  { path: 'admin/fiche-paie', component: FichePaieComponent },
  { path: 'admin/plan-modif', component: PlanModifComponent },
  { path: 'operateur/accueil-operateur', component: AccueilOperateurComponent },
  {
    path: 'operateur/fiche-paie-operateur',
    component: FichePaieOperateurComponent,
  },
  {
    path: 'operateur/fiche-perso-operateur',
    component: FichePersoOperateurComponent,
  },
  { path: 'caissier/accueil-caissier', component: AccueilCaissierComponent },
  {
    path: 'caissier/fiche-paie-caissier',
    component: FichePaieCaissierComponent,
  },
  {
    path: 'caissier/fiche-perso-caissier',
    component: FichePersoCaissierComponent,
  },
  { path: 'connexion', component: ConnexionComponent },
  { path: 'inscription', component: InscriptionComponent },
  { path: 'home', redirectTo: '', pathMatch: 'full' },
  {path: 'admin/result-simu', component: ResultSimuComponent }
];
