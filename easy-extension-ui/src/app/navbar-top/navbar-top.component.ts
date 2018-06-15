import {Component, EventEmitter, OnInit} from '@angular/core';
import {Router} from '@angular/router';
import {AppService} from '../create-new-task/app.service';
import {SharedService} from '../shared/shared.service';

@Component({
  selector: 'app-navbar-top',
  templateUrl: './navbar-top.component.html',
  styleUrls: ['./navbar-top.component.css'],
  providers: [ AppService ]
})
export class NavbarTopComponent implements OnInit {

  navStatus: Array<boolean>=new Array();

  constructor(private router: Router, private  sharedService: SharedService) {

  }

  ngOnInit() {

    this.sharedService.navEvent.subscribe((navIndex: number) => {
      for(let i=0; i<this.navStatus.length;i++){
        this.navStatus[i]=false;
      }
      this.navStatus[navIndex]=true;

    });
  }

  createApp(){
    this.router.navigate(['/newTask']);
  }

}
