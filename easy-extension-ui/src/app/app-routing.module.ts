import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import {RouterModule, Routes} from '@angular/router';
import {CreateNewTaskComponent} from './create-new-task/create-new-task.component';
import {TaskListComponent} from './task-list/task-list.component';
import {AppViewComponent} from './app-view/app-view.component';

const routes: Routes = [
  {path: '', redirectTo: '/apps', pathMatch: 'full'},
  { path: 'newTask', component: CreateNewTaskComponent },
  { path: 'appView', component: AppViewComponent },
  { path: 'app/:appId', component: CreateNewTaskComponent },
  { path: 'apps', component: TaskListComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes,{useHash:true})],
  exports: [RouterModule],
  declarations: []
})
export class AppRoutingModule { }
