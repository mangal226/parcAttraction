import { UpdateAttractionComponent } from './update-attraction/update-attraction.component';
import { PlanComponent } from './plan/plan.component';
import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { AppComponent } from './app.component';
import { HeaderComponent } from './header/header.component';
import { AccueilComponent } from './accueil/accueil.component';
import { RestaurationComponent } from './restauration/restauration.component';
import { BoutiqueComponent } from './boutique/boutique.component';
import { RouterModule } from '@angular/router';
import { routes } from './routes';
import { FooterComponent } from './footer/footer.component';
import { ProposComponent } from './propos/propos.component';
import { ContactComponent } from './contact/contact.component';
import { AccueilAdminComponent } from './admin/accueil-admin/accueil-admin.component';
import { HeaderAdminComponent } from './admin/header-admin/header-admin.component';
import { FichePersoComponent } from './admin/fiche-perso/fiche-perso.component';
import { SimulationComponent } from './admin/simulation/simulation.component';
import { HeaderOperateurComponent } from './operateur/header-operateur/header-operateur.component';
import { FichePaieOperateurComponent } from './operateur/fiche-paie-operateur/fiche-paie-operateur.component';
import { FichePersoOperateurComponent } from './operateur/fiche-perso-operateur/fiche-perso-operateur.component';
import { FichePersoCaissierComponent } from './caissier/fiche-perso-caissier/fiche-perso-caissier.component';
import { FichePaieCaissierComponent } from './caissier/fiche-paie-caissier/fiche-paie-caissier.component';
import { AccueilCaissierComponent } from './caissier/accueil-caissier/accueil-caissier.component';
import { HeaderCaissierComponent } from './caissier/header-caissier/header-caissier.component';
import { PlanModifComponent } from './admin/plan-modif/plan-modif.component';
import { FichePaieComponent } from './admin/fiche-paie/fiche-paie.component';
import { AccueilOperateurComponent } from './operateur/accueil-operateur/accueil-operateur.component';
import { ConnexionComponent } from './connexion/connexion.component';
import { InscriptionComponent } from './inscription/inscription.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { AttractionComponent } from './attraction/attraction.component';
import { UpdateRestaurationComponent } from './update-restauration/update-restauration.component';
import { UpdateBoutiqueComponent } from './update-boutique/update-boutique.component';
import { PlatComponent } from './plat/plat.component';
//import { AdminComponent } from './model/admin/admin.component';


@NgModule({
  declarations: [
    //AttractionComponent,
    AppComponent,
    HeaderComponent,
    AccueilComponent,
    RestaurationComponent,
    BoutiqueComponent,
    FooterComponent,
    ProposComponent,
    ContactComponent,
    AccueilAdminComponent,
    HeaderAdminComponent,
    FichePersoComponent,
    SimulationComponent,
    HeaderOperateurComponent,
    FichePaieOperateurComponent,
    FichePersoOperateurComponent,
    FichePersoCaissierComponent,
    FichePaieCaissierComponent,
    AccueilCaissierComponent,
    HeaderCaissierComponent,
    PlanModifComponent,
    FichePaieComponent,
    AccueilOperateurComponent,
    ConnexionComponent,
    InscriptionComponent,
    PlanComponent,
    UpdateAttractionComponent,
    UpdateRestaurationComponent,
    UpdateBoutiqueComponent,
    PlatComponent,
    //AdminComponent,
  ],
  imports: [
    BrowserModule,
    FormsModule,
    ReactiveFormsModule,
    RouterModule.forRoot(routes),
    HttpClientModule,
    BrowserAnimationsModule

  ],
  providers: [],
  bootstrap: [AppComponent],
})
export class AppModule {}
