import {EventEmitter, Injectable} from '@angular/core';

@Injectable()
export class SharedService {
  navEvent =new EventEmitter();
  constructor() { }

}
