import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';


import { AppComponent } from './app.component';
import { TaskListComponent } from './task-list/task-list.component';
import { NavbarTopComponent } from './navbar-top/navbar-top.component';
import { CreateNewTaskComponent } from './create-new-task/create-new-task.component';
import { AppRoutingModule } from './/app-routing.module';
import {FormsModule} from '@angular/forms';
import {HttpClientModule} from '@angular/common/http';
import { AppViewComponent } from './app-view/app-view.component';
import {SharedService} from './shared/shared.service';


@NgModule({
  declarations: [
    AppComponent,
    TaskListComponent,
    NavbarTopComponent,
    CreateNewTaskComponent,
    AppViewComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    AppRoutingModule,
    HttpClientModule
  ],
  providers: [SharedService],
  bootstrap: [AppComponent]
})
export class AppModule { }
