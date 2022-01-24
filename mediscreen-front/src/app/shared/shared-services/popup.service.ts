import {Injectable} from '@angular/core';
import {Subject} from 'rxjs';
import {Popup} from './popup';


@Injectable({
  providedIn: 'root',
})
// @ts-ignore
export class PopupService {

  public newPopup: Subject<Popup> = new Subject<Popup>();

  constructor() {
  }

  createPopup(type: string, message: string) {
    this.newPopup.next(new Popup(type, message));
  }
}
