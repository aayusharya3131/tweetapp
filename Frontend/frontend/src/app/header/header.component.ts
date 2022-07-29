import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import {NgbModal, ModalDismissReasons} from '@ng-bootstrap/ng-bootstrap';
import { UserService } from '../user.service';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {
  closeResult = '';
  status:string = null;
  userList='';
  resetForm:FormGroup;
  passwordError: boolean;
  twtflag='';
  constructor(private router:Router,private modalService: NgbModal,private userService:UserService) { }

  ngOnInit(): void {
    this.status = sessionStorage.getItem('userName');
    this.twtflag = sessionStorage.getItem('twtflag');
    this.userService.getAllUsers().subscribe((response:any)=>{
    this.userList=response;
    })

    this.resetForm = new FormGroup({
      'userName' : new FormControl(sessionStorage.getItem('userName'),[Validators.required]),
      'password' : new FormControl('',[Validators.required]),
      'confirmPassword' : new FormControl('',[Validators.required])
    })
  }

  logout(){
    sessionStorage.removeItem('userName');
    this.router.navigate([''])
  }

  open(content) {
    this.modalService.open(content, {ariaLabelledBy: 'modal-basic-title'}).result.then((result) => {
      this.closeResult = `Closed with: ${result}`;
    }, (reason) => {
      this.closeResult = `Dismissed ${this.getDismissReason(reason)}`;
    });
  }

  private getDismissReason(reason: any): string {
    if (reason === ModalDismissReasons.ESC) {
      return 'by pressing ESC';
    } else if (reason === ModalDismissReasons.BACKDROP_CLICK) {
      return 'by clicking on a backdrop';
    } else {
      return `with: ${reason}`;
    }
  }

  resetPassword(resetForm:any){
    this.passwordError=false;
    if (resetForm.value.password != resetForm.value.confirmPassword) {
      this.passwordError = true;
    }
    else{
      this.userService.resetPassword(resetForm).subscribe(
        (response:any)=>{
         this.router.navigate(['/tweet'])
      })
    }
  }
}
