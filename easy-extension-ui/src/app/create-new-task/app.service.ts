import {EventEmitter, Injectable} from '@angular/core';
import {HttpClient, HttpHeaders, HttpParams} from '@angular/common/http';
import {App} from '../object/App';
import {Observable} from 'rxjs/Observable';
import {catchError} from 'rxjs/operators';

const httpOptions = {
  headers: new HttpHeaders({
    'Content-Type': 'application/json', //'application/x-www-form-urlencoded',
    'Authorization': 'my-auth-token'
  }),
  params: null
};

@Injectable()
export class AppService {

  urlSaveApp='saveApp2';
  urlApps='apps';
  urlApp='app/';
  urlDeleteApp='deleteapp/';
  urlRunApp='runapp/';

  constructor(private  http: HttpClient) {

  }

  saveApp(app: App): Observable<App>{
    const httpParams=this.encodePostParameters(app);
    console.log("formDAt:"+httpParams.toString());
    return this.http.post<App>(this.urlSaveApp,httpParams.toString(),httpOptions).pipe();
  }

  saveApp2(app: App): Observable<App>{
    return this.http.post<App>(this.urlSaveApp,app,httpOptions).pipe();
  }

  getAllApps(): Observable<Array<App>>{
    return this.http.get<Array<App>>(this.urlApps).pipe();
  }

  getAppById(id:number): Observable<App>{
    return this.http.get<App>(this.urlApp+id).pipe();
  }

  deleteApp(id: number): Observable<number>{
    return this.http.get<number>(this.urlDeleteApp+id).pipe();
  }
  runApp(id: number): Observable<number>{
    return this.http.get<number>(this.urlRunApp+id).pipe();
  }

  encodePostParameters(params: any) :HttpParams{
    let formData: HttpParams = new HttpParams();
    formData=Object.keys(params).filter(key=>params[key])
      .reduce((postData: HttpParams, key: string)=>{
        console.log(key+','+params[key]);
        return postData.set(key, params[key]);

      },new HttpParams());
    // let keys=Object.keys(params).filter(key=>params[key]);
    // for (let entry of keys) {
    //   console.log('dddd:'+entry);
    //   formData=formData.set(entry, params[entry]);
    // }
    // console.log('dddd:'+formData.toString());
    return formData;
  }

}
