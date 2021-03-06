
import { AdminService } from 'src/app/services/admin.service';
import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { Admin } from 'src/app/model/admin';
import { Observable } from 'rxjs';


@Component({
  selector: 'app-fiche-perso',
  templateUrl: './fiche-perso.component.html',
  styleUrls: ['./fiche-perso.component.css']
})
export class FichePersoComponent implements OnInit {
  source : string []=[];

  admin: Observable<Admin[]> | null=null;

  constructor( private adminService: AdminService) { }


  ngOnInit(): void {
    this.admin=this.adminService.getAll();
    this.source[0]="../assets/img/le-chalet.jpg";
    this.source[1]='../assets/img/mcfat.png';
    this.source[2]="../assets/img/boutique2.jpg";
    this.source[3]="../assets/img/boutique1.jpg";
    this.source[4]="../assets/img/boutique2.jpg";
    this.source[5]="../assets/img/boutique.jpg";
    this.source[6]="../assets/img/boutique.jpg";
    this.source[7]='../assets/img/boutique1.jpg';
    this.source[8]="../assets/img/boutique2.jpg";
    this.source[9]="../assets/img/boutique1.jpg";
    this.source[10]="../assets/img/boutique2.jpg";
    this.source[11]="../assets/img/boutique.jpg";
  }

  delete(id: number){
    this.adminService.delete(id);
    this.admin=this.adminService.getAll();
  }

 /* onFormSubmit(userForm:NgForm){
   console.log(userForm);

  }
resetUserForm(UserForm:NgForm){
  UserForm.resetForm;
}*/


}
