import { Component, OnInit } from '@angular/core';
import {Observable} from 'rxjs/Observable';
import {App} from '../object/App';
import {AppService} from '../create-new-task/app.service';
import {SharedService} from '../shared/shared.service';
import {ActivatedRoute} from '@angular/router';

declare var layer: any;

@Component({
  selector: 'app-task-list',
  templateUrl: './task-list.component.html',
  styleUrls: ['./task-list.component.css'],
  providers: [ AppService ]
})
export class TaskListComponent implements OnInit {

  apps: Array<App>;

  constructor(private  routerInfo: ActivatedRoute, private appService: AppService, private sharedService: SharedService) {

  }

  ngOnInit() {
    this.sharedService.navEvent.emit(1);
    this.appService.getAllApps().subscribe(apps=>this.apps=apps);
  }

  deleteApp(app: App){
    layer.confirm('确定要删除吗', {
      btn: ['确定','取消']
    }, ()=>{
      this.appService.deleteApp(app.id).subscribe(row=>{
        if(row!=null && row>0){
          layer.msg('删除成功', {icon: 1});
          let index=this.apps.indexOf(app);
          this.apps.splice(index,1);
        }else {
          layer.msg('删除失败', {icon: 2});
        }
      });

    });
  }

  runApp( app: App){
    this.appService.runApp(app.id).subscribe(res=>{
      layer.msg('运行完成', {icon: 1});
    });
  }
}
